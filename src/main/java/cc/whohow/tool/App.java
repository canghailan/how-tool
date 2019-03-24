package cc.whohow.tool;

import cc.whohow.tool.conf.ConfigurationHandler;
import cc.whohow.tool.conf.ConfigurationHandlerFactory;
import cc.whohow.tool.docker.conf.DockerConfigurationHandler;
import cc.whohow.tool.engine.CloseRunnable;
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

@Log4j2
public class App extends Application {
    private TabPane tabPane = new TabPane();
    private ConfigurationHandlerFactory configurationHandlerFactory = new ConfigurationHandlerFactory(
            new DockerConfigurationHandler()
    );

    public static void main(String[] args) {
        launch();
    }

    @Override
    @SneakyThrows
    public void start(Stage stage) {
        String path = "private/dev.docker.hot";

        ConfigurationHandler configurationHandler = configurationHandlerFactory.apply(path);
        if (configurationHandler == null) {
            throw new IllegalArgumentException(path);
        }

        ViewModel<?> vm = configurationHandler.getIndexViewModel(path);

        Tab tab = new Tab();
        tab.setText(getTabText(path));
        tab.setContent(vm.get());
        tab.setOnCloseRequest(new CloseRunnable<>(vm));

        tabPane.getTabs().add(tab);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();

        stage.setScene(new Scene(tabPane, bounds.getWidth() / 4 * 3, bounds.getHeight() / 4 * 3));
        stage.setOnCloseRequest(this::onClose);
        stage.show();
    }

    private void onClose(WindowEvent e) {
        for (Tab tab : tabPane.getTabs()) {
            tab.getOnCloseRequest().handle(e);
        }
    }

    private String getTabText(String text) {
        if (text.length() > 16) {
            return "..." + text.substring(text.length() - 16 - 1, text.length() - 4);
        } else {
            return text.substring(0, text.length() - 4);
        }
    }
}
