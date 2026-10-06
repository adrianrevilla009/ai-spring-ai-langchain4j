package lab;

import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.model.bedrock.BedrockChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import java.util.ArrayList;

public class Main {
    static final String PROMPT = "In two sentences, explain what an order management system does.";

    public static void main(String[] args) {
        var rows = new ArrayList<Compare.Row>();
        if (System.getenv("ANTHROPIC_API_KEY") != null) {
            var m = AnthropicChatModel.builder().apiKey(System.getenv("ANTHROPIC_API_KEY"))
                    .modelName("claude-haiku-4-5-20251001").build();
            rows.add(Compare.measure("claude", m, PROMPT, 1.0, 5.0));
        }
        if (System.getenv("OPENAI_API_KEY") != null) {
            var m = OpenAiChatModel.builder().apiKey(System.getenv("OPENAI_API_KEY")).modelName("gpt-4o-mini").build();
            rows.add(Compare.measure("openai", m, PROMPT, 0.15, 0.60));
        }
        if (System.getenv("AWS_ACCESS_KEY_ID") != null) {
            var m = BedrockChatModel.builder().modelId("anthropic.claude-3-haiku-20240307-v1:0").build();
            rows.add(Compare.measure("bedrock", m, PROMPT, 0.25, 1.25));
        }
        System.out.println(rows.isEmpty() ? "Set at least one provider key (see .env.example)." : Compare.table(rows));
    }
}
