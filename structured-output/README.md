# structured-output

A LangChain4j `Extractor` interface that returns an `OrderSummary` Java record instead of a string.

## Goal
Show how an AI service maps free text into a typed record, so callers never parse model output by hand.

## Run it
```bash
mvn test
ANTHROPIC_API_KEY=... mvn -q compile exec:java
```

Expected from `mvn test`: `ExtractorTest` passes (1 test, 0 failures). `exec:java` prints an `OrderSummary[...]` for the sample text about 3 desk lamps on order `o-1001`.

## What it proves
- `Extractor.java` declares `OrderSummary extract(String text)` with a `{{text}}` prompt template; `AiServices.create` implements it.
- With a fake model returning `{"id":"o-1001","item":"desk lamp","quantity":3,"urgent":true}`, `ExtractorTest` gets `new OrderSummary("o-1001", "desk lamp", 3, true)`.
- `OrderSummary.java` is a plain record with `String`, `int` and `boolean` fields.

## Trade-offs
- The test feeds the JSON itself, so it checks the mapping and not whether a real model produces that JSON.
- A model can return wrong or missing fields; the record does not validate values.
- Each extraction is a paid model call.

## When not to use it
- When a regex or parser can pull out the fields reliably; it is cheaper and deterministic.
- When the input is untrusted and you need strict validation beyond type conversion.

Not run end to end: `exec:java` needs an Anthropic key. Only the fake-model test was run.
