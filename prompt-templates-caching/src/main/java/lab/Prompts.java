package lab;

import dev.langchain4j.model.input.PromptTemplate;
import java.util.Map;

public final class Prompts {
    /** Long, stable text first (cacheable prefix); variable text last. */
    public static final String POLICY = ("You are the returns desk. Policy: returns within 30 days, "
            + "refund to original payment method, damaged items ship a free replacement. ").repeat(40);

    private static final PromptTemplate QUESTION = PromptTemplate.from("Order {{orderId}} ({{item}}): {{question}}");

    private Prompts() {}

    public static String question(String orderId, String item, String question) {
        return QUESTION.apply(Map.of("orderId", orderId, "item", item, "question", question)).text();
    }
}
