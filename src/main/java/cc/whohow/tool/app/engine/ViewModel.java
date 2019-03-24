package cc.whohow.tool.app.engine;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.scene.Parent;
import javafx.stage.WindowEvent;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface ViewModel<V extends Parent> extends
        WritableValue<ObjectNode>,
        ObservableValue<ObjectNode>,
        Supplier<V>,
        AutoCloseable {
    Component<V> getComponent();

    void setComponent(Component<V> component);

    CompletableFuture<JsonNode> get(String expression);

    Function<JsonNode, JsonNode> getMethod(String name);

    Consumer<? extends WindowEvent> getAction(String name);
}
