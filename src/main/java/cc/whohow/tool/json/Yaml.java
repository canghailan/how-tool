package cc.whohow.tool.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.ByteBuffer;

public class Yaml {
    private static final ObjectMapper OBJECT_MAPPER = buildObjectMapper();

    private static ObjectMapper buildObjectMapper() {
        YAMLFactory yamlFactory = new YAMLFactory();
        yamlFactory.configure(YAMLGenerator.Feature.WRITE_DOC_START_MARKER, false);
        yamlFactory.configure(YAMLGenerator.Feature.MINIMIZE_QUOTES, true);
        return new ObjectMapper(yamlFactory);
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
    public static JsonNode parse(InputStream stream) {
        return OBJECT_MAPPER.readTree(stream);
    }

    @SneakyThrows
    public static JsonNode parse(Reader reader) {
        return OBJECT_MAPPER.readTree(reader);
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
}
