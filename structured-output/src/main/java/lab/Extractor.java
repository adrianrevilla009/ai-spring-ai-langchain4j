package lab;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface Extractor {
    @UserMessage("Extract the order from this text: {{text}}")
    OrderSummary extract(@V("text") String text);
}
