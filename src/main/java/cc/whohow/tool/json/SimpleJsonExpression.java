package cc.whohow.tool.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;

public class SimpleJsonExpression implements JsonExpression {
    private final String expression;

    public SimpleJsonExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public JsonNode evaluate(JsonNode context) {
        if (context == null || expression == null || expression.isEmpty()) {
            return NullNode.getInstance();
        }
        if (expression.startsWith("/")) {
            return context.at(expression);
        }
        return context.path(expression);
    }
}
