package cc.whohow.tool.engine;

import cc.whohow.tool.json.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.scene.Parent;
import javafx.stage.WindowEvent;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class SimpleViewModel<V extends Parent> implements ViewModel<V> {
    protected Component<V> component;
    protected ObjectNode model;
    protected V view;

    @Override
    public Component<V> getComponent() {
        return component;
    }

    @Override
    public void setComponent(Component<V> component) {
        this.component = component;
    }

    @Override
    public CompletableFuture<JsonNode> get(String expression) {
        return CompletableFuture.completedFuture(Json.evaluate(model, expression));
    }

    @Override
    public Function<JsonNode, JsonNode> getMethod(String name) {
        return null;
    }

    @Override
    public Consumer<? extends WindowEvent> getAction(String name) {
        return null;
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

    @Override
    public void addListener(ChangeListener<? super ObjectNode> listener) {

    }

    @Override
    public void removeListener(ChangeListener<? super ObjectNode> listener) {

    }

    @Override
    public ObjectNode getValue() {
        return null;
    }

    @Override
    public void setValue(ObjectNode model) {
        this.model = model;
        this.view = null;
    }

    @Override
    public String toString() {
        return model.toString();
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public V get() {
        if (view == null) {
            view = component.apply(null, this);
        }
        return view;
    }
}
