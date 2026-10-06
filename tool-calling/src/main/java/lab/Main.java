package lab;

import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.service.AiServices;

public class Main {
    public static void main(String[] args) {
        var model = AnthropicChatModel.builder()
                .apiKey(System.getenv("ANTHROPIC_API_KEY"))
                .modelName("claude-haiku-4-5-20251001")
                .build();
        var assistant = AiServices.builder(Assistant.class).chatModel(model).tools(new OrderTools()).build();
        System.out.println(assistant.chat("Where is order o-1002?"));
    }
}
