package lab;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import java.util.Map;

public class OrderTools {
    private final Map<String, String> status = Map.of("o-1001", "SHIPPED", "o-1002", "PACKING");

    @Tool("Returns the fulfilment status of an order")
    public String orderStatus(@P("order id such as o-1001") String orderId) {
        return status.getOrDefault(orderId, "UNKNOWN");
    }
}
