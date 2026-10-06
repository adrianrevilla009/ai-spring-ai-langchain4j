package lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.langchain4j.agent.tool.Tool;
import org.junit.jupiter.api.Test;

class OrderToolsTest {
    @Test
    void toolLogic() {
        assertEquals("PACKING", new OrderTools().orderStatus("o-1002"));
        assertEquals("UNKNOWN", new OrderTools().orderStatus("nope"));
    }

    @Test
    void toolIsAnnotated() throws Exception {
        assertTrue(OrderTools.class.getMethod("orderStatus", String.class).isAnnotationPresent(Tool.class));
    }
}
