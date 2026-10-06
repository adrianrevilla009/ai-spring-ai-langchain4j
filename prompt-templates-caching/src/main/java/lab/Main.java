package lab;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.anthropic.AnthropicChatModel;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var model = AnthropicChatModel.builder()
                .apiKey(System.getenv("ANTHROPIC_API_KEY"))
                .modelName("claude-haiku-4-5-20251001")
                .cacheSystemMessages(true)
                .build();
        for (String q : List.of("Can I return a lamp after 20 days?", "What if it arrived broken?")) {
            var r = model.chat(SystemMessage.from(Prompts.POLICY), UserMessage.from(Prompts.question("o-1001", "desk lamp", q)));
            System.out.println(r.aiMessage().text() + "\n  usage: " + r.tokenUsage());
        }
    }
}
