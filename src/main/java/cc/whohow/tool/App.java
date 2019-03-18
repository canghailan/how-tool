package cc.whohow.tool;

import cc.whohow.tool.docker.view.DockerContainerComponent;
import cc.whohow.tool.json.Json;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    @SneakyThrows
    public void start(Stage stage) {
        DockerContainerComponent dockerComponent = new DockerContainerComponent();
        dockerComponent.setValue(Json.newObject());
        stage.setScene(new Scene(dockerComponent.get()));
        stage.setOnCloseRequest((e) -> {
            try {
                dockerComponent.close();
            } catch (Throwable ignore) {
            }
        });
        stage.show();
    }
}
