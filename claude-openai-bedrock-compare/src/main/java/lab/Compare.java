package lab;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.output.TokenUsage;
import java.util.List;

public final class Compare {
    public record Row(String provider, long millis, int in, int out, double usd) {}

    private Compare() {}

    /** Prices are USD per 1M tokens (input, output); illustrative, check the vendor pricing pages. */
    public static Row measure(String provider, ChatModel model, String prompt, double inPrice, double outPrice) {
        long t0 = System.nanoTime();
        var response = model.chat(UserMessage.from(prompt));
        long ms = (System.nanoTime() - t0) / 1_000_000;
        TokenUsage u = response.tokenUsage();
        int in = u == null || u.inputTokenCount() == null ? 0 : u.inputTokenCount();
        int out = u == null || u.outputTokenCount() == null ? 0 : u.outputTokenCount();
        return new Row(provider, ms, in, out, (in * inPrice + out * outPrice) / 1_000_000.0);
    }

    public static String table(List<Row> rows) {
        var sb = new StringBuilder("| provider | ms | in | out | usd |\n|---|---|---|---|---|\n");
        for (Row r : rows) {
            sb.append("| %s | %d | %d | %d | %.6f |\n".formatted(r.provider(), r.millis(), r.in(), r.out(), r.usd()));
        }
        return sb.toString();
    }
}
