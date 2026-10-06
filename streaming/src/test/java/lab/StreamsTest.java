package lab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class StreamsTest {
    @Test
    void tokensArriveInOrderAndJoin() {
        var model = new StreamingChatModel() {
            @Override
            public void doChat(ChatRequest r, StreamingChatResponseHandler h) {
                h.onPartialResponse("Hel");
                h.onPartialResponse("lo");
                h.onCompleteResponse(ChatResponse.builder().aiMessage(AiMessage.from("Hello")).build());
            }
        };
        List<String> seen = new ArrayList<>();
        assertEquals("Hello", Streams.collect(model, "hi", seen::add).join());
        assertEquals(List.of("Hel", "lo"), seen);
    }
}
