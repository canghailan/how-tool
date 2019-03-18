package cc.whohow.tool.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;

public class Json {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(new YAMLFactory());

    public static ArrayNode newArray() {
        return OBJECT_MAPPER.createArrayNode();
    }

    public static ObjectNode newObject() {
        return OBJECT_MAPPER.createObjectNode();
    }

    @SneakyThrows
    public static JsonNode parse(String json) {
        return OBJECT_MAPPER.readTree(json);
    }

    @SneakyThrows
    public static String stringify(Object json) {
        return OBJECT_MAPPER.writeValueAsString(json);
    }

    public static JsonNode from(Object json) {
        return OBJECT_MAPPER.valueToTree(json);
    }

    public static JsonExpression compile(String expression) {
        return new SimpleJsonExpression(expression);
    }

    public static JsonNode evaluate(JsonNode json, String expression) {
        return compile(expression).evaluate(json);
    }
}
