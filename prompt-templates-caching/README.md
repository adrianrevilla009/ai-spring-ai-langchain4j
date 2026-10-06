# prompt-templates-caching

A LangChain4j prompt template for the variable part of a question, and a long fixed policy text sent as a cached system message to Anthropic.

## Goal
Show how to put stable text first and variable text last so Anthropic prompt caching can reuse the prefix, and how to build the variable part from a template.

## Run it
```bash
mvn test
ANTHROPIC_API_KEY=... mvn -q compile exec:java
```

Expected from `mvn test`: `PromptsTest` passes (2 tests, 0 failures). `exec:java` asks two questions and prints each answer with its token usage.

## What it proves
- `Prompts.question("o-1", "lamp", "why?")` renders `Order o-1 (lamp): why?` from the `{{orderId}}`, `{{item}}` and `{{question}}` template.
- `Prompts.POLICY` is the same text repeated 40 times, over 4000 characters, so it is long enough to be worth caching.
- `Main.java` builds the model with `cacheSystemMessages(true)` and sends the policy as the system message on both calls.

## Trade-offs
- Whether the second call hits the cache can only be seen in the printed token usage of a live run; the tests do not check it.
- Cache entries expire, and the model has a minimum prompt size before caching applies.
- Any change to the cached prefix, even a space, misses the cache.

## When not to use it
- For short prompts or one-off calls, where caching saves nothing.
- With providers that do not offer prompt caching through this API.

Not run end to end: the cache behaviour needs an Anthropic key. Only the offline tests were run.
