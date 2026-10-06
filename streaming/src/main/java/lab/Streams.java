package lab;

import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public final class Streams {
    private Streams() {}

    /** Calls onToken for each partial and completes with the full text. */
    public static CompletableFuture<String> collect(StreamingChatModel model, String prompt, Consumer<String> onToken) {
        var done = new CompletableFuture<String>();
        var text = new StringBuilder();
        model.chat(prompt, new StreamingChatResponseHandler() {
            @Override
            public void onPartialResponse(String partial) {
                text.append(partial);
                onToken.accept(partial);
            }

            @Override
            public void onCompleteResponse(ChatResponse response) {
                done.complete(text.toString());
            }

            @Override
            public void onError(Throwable error) {
                done.completeExceptionally(error);
            }
        });
        return done;
    }
}
