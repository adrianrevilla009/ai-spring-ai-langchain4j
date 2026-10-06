package lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.TokenUsage;
import java.util.List;
import org.junit.jupiter.api.Test;

class CompareTest {
    @Test
    void costIsComputedFromUsage() {
        var model = new ChatModel() {
            @Override
            public ChatResponse doChat(ChatRequest r) {
                return ChatResponse.builder().aiMessage(AiMessage.from("ok")).tokenUsage(new TokenUsage(1000, 500)).build();
            }
        };
        var row = Compare.measure("fake", model, "hi", 1.0, 2.0);
        assertEquals(0.002, row.usd(), 1e-9);
        assertTrue(Compare.table(List.of(row)).contains("| fake |"));
    }
}
