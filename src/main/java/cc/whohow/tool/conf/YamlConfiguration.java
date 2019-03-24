package cc.whohow.tool.conf;

import cc.whohow.tool.json.Yaml;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class YamlConfiguration implements Configuration {
    @Override
    @SneakyThrows
    public void write(String path, ObjectNode data) {
        Yaml.stringify(data, Files.newOutputStream(Paths.get(path), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
    }

    @Override
    @SneakyThrows
    public ObjectNode read(String path) {
        return (ObjectNode) Yaml.parse(Files.newInputStream(Paths.get(path)));
    }
}
