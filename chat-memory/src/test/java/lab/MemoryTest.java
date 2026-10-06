package lab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.junit.jupiter.api.Test;

class MemoryTest {
    static ChatModel fake(java.util.function.Function<ChatRequest, String> f) {
        return new ChatModel() {
            @Override
            public ChatResponse doChat(ChatRequest r) {
                return ChatResponse.builder().aiMessage(AiMessage.from(f.apply(r))).build();
            }
        };
    }

    @Test
    void historyIsReplayedAndWindowed() {
        var a = Assistants.withMemory(fake(r -> "seen " + r.messages().size()), 4);
        assertEquals("seen 1", a.chat("one"));
        assertEquals("seen 3", a.chat("two"));
        assertEquals("seen 4", a.chat("three")); // window of 4 evicts the oldest
    }
}
