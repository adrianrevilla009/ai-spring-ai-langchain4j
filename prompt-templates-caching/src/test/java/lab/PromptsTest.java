package lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PromptsTest {
    @Test
    void templateFillsVariables() {
        assertEquals("Order o-1 (lamp): why?", Prompts.question("o-1", "lamp", "why?"));
    }

    @Test
    void policyIsLongEnoughToCache() {
        assertTrue(Prompts.POLICY.length() > 4000);
    }
}
