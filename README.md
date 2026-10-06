# ai-spring-ai-langchain4j

Seven small Java examples that show how Spring AI and LangChain4j wrap an LLM provider: one interface over several vendors, typed output, tool calling, prompt caching, streaming, chat memory and a cost and latency table. The shared example domain is Orders.

## What is inside

| Folder | What it shows | Run |
| --- | --- | --- |
| [`provider-abstraction`](./provider-abstraction) | Spring AI `ChatClient` code that does not depend on a vendor class; the provider is chosen by a property | `mvn test` |
| [`structured-output`](./structured-output) | LangChain4j service that turns free text into an `OrderSummary` record | `mvn test` |
| [`tool-calling`](./tool-calling) | LangChain4j `@Tool` method that answers order status questions | `mvn test` |
| [`prompt-templates-caching`](./prompt-templates-caching) | Prompt template plus a long stable system prompt marked for Anthropic prompt caching | `mvn test` |
| [`streaming`](./streaming) | Streaming handler that forwards partial tokens and returns the full text | `mvn test` |
| [`chat-memory`](./chat-memory) | Sliding-window chat memory around an assistant | `mvn test` |
| [`claude-openai-bedrock-compare`](./claude-openai-bedrock-compare) | Same prompt on Claude, OpenAI and Bedrock, printed as a latency and cost table | `mvn test` |

## Prerequisites

- Java 21 and Maven 3.9 or newer.
- For live runs only: `ANTHROPIC_API_KEY`, and optionally `OPENAI_API_KEY` or AWS credentials. The tests use fake models and need no keys or network access.

## How to read it

Start with `provider-abstraction`, then read the LangChain4j folders in any order. The tests pass offline in every folder; the live-provider runs (`Main` classes) were not run, because they need paid API keys.
