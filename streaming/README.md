# streaming

A small `Streams.collect` helper around a LangChain4j `StreamingChatModel` that passes each token to a callback and returns the full text.

## Goal
Show how to consume a streamed model answer token by token and still get a single `CompletableFuture<String>` at the end.

## Run it
```bash
mvn test
ANTHROPIC_API_KEY=... mvn -q compile exec:java
```

Expected from `mvn test`: `StreamsTest` passes (1 test, 0 failures). `exec:java` prints two sentences as they arrive, then a line like `[123 chars]`.

## What it proves
- In `StreamsTest`, a fake model emits `Hel` and `lo`; the callback sees them in that order and the future completes with `Hello`.
- `Streams.java` appends each partial response to a `StringBuilder` and completes the future in `onCompleteResponse`.
- `onError` completes the future exceptionally, so `join()` throws instead of hanging.

## Trade-offs
- The future only completes with text; token usage and the final response object are dropped.
- The callback runs on the model's thread, so slow callbacks slow the stream.
- No cancellation or timeout is implemented.

## When not to use it
- For short answers or batch jobs where nobody reads partial output.
- When you need a web-facing stream; use Server-Sent Events or Spring's reactive types on top.

Not run end to end: the live stream needs an Anthropic key. Only the fake-model test was run.
