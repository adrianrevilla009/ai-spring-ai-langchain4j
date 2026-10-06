package lab;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /** Only one ChatModel bean exists (spring.ai.model.chat), so the same code runs on any provider. */
    @Bean
    CommandLineRunner ask(ChatClient.Builder builder) {
        return args -> System.out.println(new OrderAssistant(builder.build()).describe("o-1001"));
    }
}
