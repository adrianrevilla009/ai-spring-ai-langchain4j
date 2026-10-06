package lab;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;

public final class Assistants {
    private Assistants() {}

    /** Keeps the last maxMessages messages and replays them on every call. */
    public static Assistant withMemory(ChatModel model, int maxMessages) {
        return AiServices.builder(Assistant.class)
                .chatModel(model)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(maxMessages))
                .build();
    }
}
