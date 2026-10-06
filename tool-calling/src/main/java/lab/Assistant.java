package lab;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {
    @SystemMessage("Use the tools to answer; never guess an order status.")
    String chat(String message);
}
