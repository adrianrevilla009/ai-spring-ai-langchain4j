# claude-openai-bedrock-compare

A LangChain4j program that sends one prompt to Claude, OpenAI and Bedrock, whichever have credentials set, and prints a markdown table of latency, tokens and cost.

## Goal
Show how to compare providers on the same prompt with the same `ChatModel` interface, measuring time and estimating cost from token usage.

## Run it
```bash
mvn test
cp .env.example .env   # fill in keys, then export them
mvn -q compile exec:java
```

Expected from `mvn test`: `CompareTest` passes (1 test, 0 failures). `exec:java` prints a table with the header `| provider | ms | in | out | usd |` and one row per configured provider, or "Set at least one provider key" if none is set.

## What it proves
- `Compare.measure` times one `model.chat` call and computes cost as `(in * inPrice + out * outPrice) / 1,000,000`.
- With a fake model reporting 1000 input and 500 output tokens at 1.0 and 2.0 USD per million, `CompareTest` gets 0.002 USD.
- `Main.java` adds a provider only if its environment variable is set (`ANTHROPIC_API_KEY`, `OPENAI_API_KEY`, `AWS_ACCESS_KEY_ID`).

## Trade-offs
- The prices in `Main.java` are fixed illustrative numbers and go out of date.
- One call per provider is a single sample, not a benchmark; latency varies run to run.
- The Bedrock model in `Main.java` is an older Claude 3 Haiku id, so it is not the same model as the Claude row.

## When not to use it
- To make purchasing decisions; use many runs on your own real prompts.
- When you need quality comparison; this measures time and cost only.

Not run end to end: it needs keys for each provider. Only the fake-model test was run.
