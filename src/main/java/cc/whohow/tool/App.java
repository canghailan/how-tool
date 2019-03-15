package cc.whohow.tool;

import cc.whohow.tool.docker.view.DockerComponent;
import cc.whohow.tool.json.Json;
import cc.whohow.tool.vue.Component;
import cc.whohow.tool.xml.Xml;
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
import org.w3c.dom.Document;

@Log4j2
public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    @SneakyThrows
    public void start(Stage stage) {
        Document dom = Xml.load("cc/whohow/tool/docker/view/Containers.xml");
        DockerComponent dockerComponent = new DockerComponent(dom, Json.newObject());



        Component component = new Component(dom, data);
        Scene scene = component.get();

        stage.setScene(component.get());
        stage.show();
    }
}
