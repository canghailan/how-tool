package cc.whohow.tool;

import cc.whohow.tool.docker.conf.DockerConfiguration;
import cc.whohow.tool.docker.view.DockerContainersView;
import cc.whohow.tool.docker.vm.DockerViewModel;
import cc.whohow.tool.engine.ViewModel;
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
    private TabPane tabPane = new TabPane();
    private List<ViewModel<?>> vms = new ArrayList<>();

    public static void main(String[] args) {
        launch();
    }

    @Override
    @SneakyThrows
    public void start(Stage stage) {
        String path = "private/dev.docker.hot";

        DockerViewModel vm = new DockerViewModel();
        vm.setValue(new DockerConfiguration().read(path));
        vm.setComponent(new DockerContainersView());
        vms.add(vm);

        Tab tab = new Tab();
        tab.setText(path);
        tab.setContent(vm.get());

        tabPane.getTabs().add(tab);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();

        stage.setScene(new Scene(tabPane, bounds.getWidth() / 4 * 3, bounds.getHeight() / 4 * 3));
        stage.setOnCloseRequest(this::onClose);
        stage.show();
    }

    private void onClose(WindowEvent e) {
        for (ViewModel<?> vm : vms) {
            try {
                vm.close();
            } catch (Throwable ignore) {
            }
        }
    }
}
