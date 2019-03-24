package cc.whohow.tool;

import cc.whohow.tool.docker.conf.DockerConfiguration;
import cc.whohow.tool.docker.view.DockerContainersView;
import cc.whohow.tool.engine.Component;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    private TabPane tabPane = new TabPane();
    private List<Component<?>> componentList = new ArrayList<>();

    @Override
    @SneakyThrows
    public void start(Stage stage) {
        String path = "private/dev.docker.hot";
        DockerContainersView dockerComponent = new DockerContainersView();
        dockerComponent.setValue(new DockerConfiguration().read(path));
        componentList.add(dockerComponent);

        Tab tab = new Tab();
        tab.setText(path);
        tab.setContent(dockerComponent.get());

        tabPane.getTabs().add(tab);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();

        stage.setScene(new Scene(tabPane, bounds.getWidth() / 4 * 3, bounds.getHeight() / 4 * 3));
        stage.setOnCloseRequest(this::onClose);
        stage.show();
    }

    private void onClose(WindowEvent e) {
        for (Component<?> component : componentList) {
            try {
                component.close();
            } catch (Throwable ignore) {
            }
        }
    }
}
