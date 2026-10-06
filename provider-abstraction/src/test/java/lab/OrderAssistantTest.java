package lab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;

class OrderAssistantTest {
    @Test
    void worksWithAnyChatModel() {
        ChatModel stub = prompt -> new ChatResponse(List.of(new Generation(new AssistantMessage("checked"))));
        assertEquals("checked", new OrderAssistant(ChatClient.create(stub)).describe("o-1"));
    }
}
