package cc.whohow.tool.json;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonExpression {
    JsonNode evaluate(JsonNode context);
}
