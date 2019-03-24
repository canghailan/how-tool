package cc.whohow.tool.docker.conf;

import cc.whohow.tool.app.conf.YamlConfiguration;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.nio.file.Paths;

public class DockerConfiguration extends YamlConfiguration {
    @Override
    public void write(String path, ObjectNode data) {
        String certPath = data.path("certPath").asText(null);
        if (certPath != null) {
            certPath = Paths.get(path).toAbsolutePath().relativize(Paths.get(certPath).toAbsolutePath()).toString();
            data.put("certPath", certPath);
        }
        super.write(path, data);
    }

    @Override
    public ObjectNode read(String path) {
        ObjectNode data = super.read(path);
        String certPath = data.path("certPath").asText("cert");
        certPath = Paths.get(path).resolve(certPath).normalize().toAbsolutePath().toString();
        data.put("certPath", certPath);
        return data;
    }
}
