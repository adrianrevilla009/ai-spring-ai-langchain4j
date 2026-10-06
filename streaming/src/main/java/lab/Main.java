package lab;

import dev.langchain4j.model.anthropic.AnthropicStreamingChatModel;

public class Main {
    public static void main(String[] args) {
        var model = AnthropicStreamingChatModel.builder()
                .apiKey(System.getenv("ANTHROPIC_API_KEY"))
                .modelName("claude-haiku-4-5-20251001")
                .build();
        String all = Streams.collect(model, "Write two sentences about order tracking.", System.out::print).join();
        System.out.println("\n[" + all.length() + " chars]");
    }
}
