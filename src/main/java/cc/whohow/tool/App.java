package cc.whohow.tool;

import cc.whohow.tool.docker.view.ContainersComponent;
import cc.whohow.tool.json.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@Log4j2
public class App extends Application {
    private ScriptEngine javascriptEngine = new ScriptEngineManager().getEngineByName("javascript");

    public static void main(String[] args) {
        launch();
    }

    @Override
    @SneakyThrows
    public void start(Stage stage) {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("")
                .withDockerTlsVerify(true)
                .withDockerCertPath("private")
                .build();
        DockerClient docker = DockerClientBuilder.getInstance(config).build();

        JsonNode containers = Json.from(docker.listContainersCmd().exec());
        ObjectNode data = Json.newObject();
        data.set("containers", containers);

        Scene scene = new ContainersComponent().apply(data);
        stage.setScene(scene);
        stage.show();
    }
}
