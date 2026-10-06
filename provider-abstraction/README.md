# provider-abstraction

A Spring Boot 3.4 app using Spring AI 1.0.0 where `OrderAssistant` talks only to `ChatClient`, and a property picks Anthropic, OpenAI or Bedrock.

## Goal
Show that application code can stay vendor-neutral when it depends on Spring AI's `ChatClient`. Switching provider is a configuration change, not a code change.

## Run it
```bash
mvn test
ANTHROPIC_API_KEY=... mvn spring-boot:run
CHAT_PROVIDER=openai OPENAI_API_KEY=... mvn spring-boot:run
```

Expected from `mvn test`: `OrderAssistantTest` passes (1 test, 0 failures) using a stub `ChatModel`. The `spring-boot:run` commands print one sentence about checking order `o-1001`.

## What it proves
- `OrderAssistant.java` imports only `ChatClient`; the test passes a hand-written `ChatModel` lambda and gets `checked` back.
- `application.properties` sets `spring.ai.model.chat=${CHAT_PROVIDER:anthropic}`, so only one provider's `ChatModel` bean is created. Valid values are `anthropic`, `openai` and `bedrock-converse`.
- Model names are pinned in the same file, and keys are read from environment variables (see `.env.example`).

## Trade-offs
- The abstraction hides vendor features such as Anthropic cache control or provider-specific options.
- All three starters are on the classpath, so the dependency tree is larger than any one provider needs.
- Switching models changes answers and cost even when the code is identical.

## When not to use it
- When you depend on one vendor's advanced features; use that vendor's client directly.
- When you only ever call one provider and do not need to swap it.

Not run end to end: the live provider calls need API keys. Only the stub-based test was run.
