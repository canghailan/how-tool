package cc.whohow.tool.app.engine;

import cc.whohow.tool.json.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.scene.Parent;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import org.w3c.dom.Element;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

@Log4j2
public abstract class AbstractViewModel<V extends Parent> implements ViewModel<V> {
    protected Component<V> component;
    protected Element node;
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

    public Element getNode() {
        return node;
    }

    public void setNode(Element node) {
        this.node = node;
    }

    @Override
    public CompletableFuture<JsonNode> get(String expression) {
        log.debug("get {}", expression);
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
        return model;
    }

    @Override
    public void setValue(ObjectNode model) {
        this.model = model;
        this.view = null;
        log.debug("setValue {}", model);
    }

    @Override
    public String toString() {
        return model.toString();
    }

    @Override
    public void close() throws Exception {
        log.debug("close {}", this);
    }

    @Override
    public V get() {
        if (view == null) {
            view = component.apply(node, this);
        }
        return view;
    }
}
