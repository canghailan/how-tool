package cc.whohow.tool;

import cc.whohow.tool.app.conf.ConfigurationHandler;
import cc.whohow.tool.app.conf.ConfigurationHandlerFactory;
import cc.whohow.tool.app.engine.CloseRunnable;
import cc.whohow.tool.app.engine.ViewModel;
import cc.whohow.tool.docker.conf.DockerConfigurationHandler;
import cc.whohow.tool.json.Json;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class App extends Application {
    private static final Pattern TAB_TEXT = Pattern.compile("(?<short>.{1,17})(\\.hot)?$");
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
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();

        stage.setScene(new Scene(tabPane, bounds.getWidth() / 4 * 3, bounds.getHeight() / 4 * 3));
        stage.setOnCloseRequest(this::onClose);
        stage.show();

        open("private/dev.docker.hot");
    }

    private void onClose(WindowEvent e) {
        for (Tab tab : tabPane.getTabs()) {
            tab.getOnCloseRequest().handle(e);
        }
    }

    private void open(String uri) {
        ConfigurationHandler configurationHandler = configurationHandlerFactory.apply(uri);
        if (configurationHandler == null) {
            throw new IllegalArgumentException(uri);
        }

        ViewModel<?> vm = configurationHandler.getIndexViewModel(uri);

        String tabText = uri;
        if (tabText.length() > 16) {
            Matcher matcher = TAB_TEXT.matcher(tabText);
            if (matcher.find()) {
                tabText = matcher.group("short");
            }
            if (tabText.length() > 16) {
                tabText = "..." + tabText.substring(4);
            }
        }

        ObjectNode options = Json.newObject();
        options.put("text", tabText);

        open(vm, options);
    }

    private void open(ViewModel<?> vm, ObjectNode options) {
        Tab tab = new Tab();
        tab.setText(options.path("text").asText(vm.getClass().toString()));
        tab.setContent(vm.get());
        tab.setOnCloseRequest(new CloseRunnable<>(vm));

        tabPane.getTabs().add(tab);
    }
}
