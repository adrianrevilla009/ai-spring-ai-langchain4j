# chat-memory

A LangChain4j `Assistant` built with a `MessageWindowChatMemory`, so earlier turns are replayed on each call.

## Goal
Show that a model is stateless and that memory means resending recent messages, capped by a sliding window.

## Run it
```bash
mvn test
ANTHROPIC_API_KEY=... mvn -q compile exec:java
```

Expected from `mvn test`: `MemoryTest` passes (1 test, 0 failures). `exec:java` sends "My order id is o-1001." and then "What was my order id?"; the second answer should mention `o-1001`.

## What it proves
- With a fake model that answers how many messages it received, `MemoryTest` gets `seen 1`, `seen 3`, then `seen 4`.
- The third count is 4 and not 5 because `Assistants.withMemory(model, 4)` evicts the oldest message.
- `Assistant.java` is a one-method interface; `Assistants.java` is the only place memory is configured.

## Trade-offs
- Every call resends the history, so input tokens grow with the window size.
- Old facts are forgotten once they leave the window.
- The memory is one in-process instance with no user or session id, and it is lost on restart.

## When not to use it
- For stateless one-shot calls.
- For a multi-user service, unless you add per-user memory and a persistent store.

Not run end to end: the live conversation needs an Anthropic key. Only the fake-model test was run.
