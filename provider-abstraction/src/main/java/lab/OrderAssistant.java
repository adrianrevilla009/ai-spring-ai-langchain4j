package lab;

import org.springframework.ai.chat.client.ChatClient;

/** Provider-neutral: depends on ChatClient only, never on a vendor class. */
public class OrderAssistant {
    private final ChatClient client;

    public OrderAssistant(ChatClient client) {
        this.client = client;
    }

    public String describe(String orderId) {
        return client.prompt()
                .system("You are a terse order desk assistant. Answer in one sentence.")
                .user("Explain what a typical status check for order " + orderId + " involves.")
                .call()
                .content();
    }
}
