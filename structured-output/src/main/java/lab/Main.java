package lab;

import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.service.AiServices;

public class Main {
    public static void main(String[] args) {
        var model = AnthropicChatModel.builder()
                .apiKey(System.getenv("ANTHROPIC_API_KEY"))
                .modelName("claude-haiku-4-5-20251001")
                .build();
        var extractor = AiServices.create(Extractor.class, model);
        System.out.println(extractor.extract("URGENT: please ship 3 desk lamps for order o-1001 today"));
    }
}
