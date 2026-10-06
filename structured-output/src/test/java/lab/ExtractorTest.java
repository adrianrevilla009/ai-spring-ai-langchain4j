package lab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;

class ExtractorTest {
    static ChatModel fake(java.util.function.Function<ChatRequest, String> f) {
        return new ChatModel() {
            @Override
            public ChatResponse doChat(ChatRequest r) {
                return ChatResponse.builder().aiMessage(AiMessage.from(f.apply(r))).build();
            }
        };
    }

    @Test
    void jsonBecomesRecord() {
        var model = fake(r -> "{\"id\":\"o-1001\",\"item\":\"desk lamp\",\"quantity\":3,\"urgent\":true}");
        OrderSummary s = AiServices.create(Extractor.class, model).extract("anything");
        assertEquals(new OrderSummary("o-1001", "desk lamp", 3, true), s);
    }
}
