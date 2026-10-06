package lab;

import dev.langchain4j.model.anthropic.AnthropicChatModel;

public class Main {
    public static void main(String[] args) {
        var model = AnthropicChatModel.builder()
                .apiKey(System.getenv("ANTHROPIC_API_KEY"))
                .modelName("claude-haiku-4-5-20251001")
                .build();
        var a = Assistants.withMemory(model, 10);
        System.out.println(a.chat("My order id is o-1001."));
        System.out.println(a.chat("What was my order id?"));
    }
}
