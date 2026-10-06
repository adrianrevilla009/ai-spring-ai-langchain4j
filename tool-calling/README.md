# tool-calling

A LangChain4j assistant with one `@Tool` method, `orderStatus`, that looks up an order in an in-memory map.

## Goal
Show how a model is given a Java method as a tool and told to use it instead of guessing.

## Run it
```bash
mvn test
ANTHROPIC_API_KEY=... mvn -q compile exec:java
```

Expected from `mvn test`: `OrderToolsTest` passes (2 tests, 0 failures). `exec:java` asks "Where is order o-1002?", and the answer should say PACKING.

## What it proves
- `OrderTools.java` returns `SHIPPED` for `o-1001`, `PACKING` for `o-1002` and `UNKNOWN` for anything else.
- The method carries `@Tool` and a `@P` parameter description, which `OrderToolsTest` checks by reflection.
- `Assistant.java` has a system message, "Use the tools to answer; never guess an order status", and `Main.java` registers the tools with `AiServices.builder`.

## Trade-offs
- The tests cover the tool logic and annotations only. No test has a model actually choose and call the tool.
- Tool descriptions are part of the prompt and cost tokens on every call.
- The status data is a hard-coded map, not a real order service.

## When not to use it
- When the answer is a fixed lookup you can do before the model call.
- When a tool changes data and you cannot add authorization and confirmation around it.

Not run end to end: the live call needs an Anthropic key. Only the offline tests were run.
