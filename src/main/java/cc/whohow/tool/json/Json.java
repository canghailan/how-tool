package cc.whohow.tool.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.ByteBuffer;

public class Json {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ArrayNode newArray() {
        return OBJECT_MAPPER.createArrayNode();
    }

    public static ObjectNode newObject() {
        return OBJECT_MAPPER.createObjectNode();
    }

    public static JsonNode string(String string) {
        return JsonNodeFactory.instance.textNode(string);
    }

    @SneakyThrows
    public static JsonNode parse(String json) {
        return OBJECT_MAPPER.readTree(json);
    }

    @SneakyThrows
    public static JsonNode parse(ByteBuffer json) {
        return OBJECT_MAPPER.readTree(new ByteBufferBackedInputStream(json));
    }

    @SneakyThrows
    public static JsonNode parse(Reader reader) {
        return OBJECT_MAPPER.readTree(reader);
    }

    @SneakyThrows
    public static JsonNode parse(InputStream stream) {
        return OBJECT_MAPPER.readTree(stream);
    }

    @SneakyThrows
    public static JsonNode parse(URL url) {
        return OBJECT_MAPPER.readTree(url);
    }

    @SneakyThrows
    public static String stringify(Object json) {
        return OBJECT_MAPPER.writeValueAsString(json);
    }

    @SneakyThrows
    public static void stringify(Object json, Writer writer) {
        OBJECT_MAPPER.writeValue(writer, json);
    }

    @SneakyThrows
    public static void stringify(Object json, OutputStream stream) {
        OBJECT_MAPPER.writeValue(stream, json);
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
