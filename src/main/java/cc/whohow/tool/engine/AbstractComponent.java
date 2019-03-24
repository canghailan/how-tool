package cc.whohow.tool.engine;

import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.scene.Parent;
import org.w3c.dom.Element;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public abstract class AbstractComponent<V extends Parent> implements Component<V> {
    protected Element element;
    protected ObjectNode data;
    protected CompletableFuture<V> view;
    protected Function<String, Component<? extends Parent>> componentFactory = Components.getDefault();

    @Override
    public void close() throws Exception {

    }

    @Override
    public V get() {
        return view.join();
    }

    @Override
    public void addListener(ChangeListener<? super ObjectNode> listener) {

    }

    @Override
    public void removeListener(ChangeListener<? super ObjectNode> listener) {

    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

    @Override
    public ObjectNode getValue() {
        return data;
    }

    @Override
    public void setValue(ObjectNode value) {
        this.data = value;
        this.view = CompletableFuture.completedFuture(apply(element, data));
    }

    @Override
    public void setComponentFactory(Function<String, Component<? extends Parent>> componentFactory) {
        this.componentFactory = componentFactory;
    }

    @Override
    public Function<String, Component<? extends Parent>> getComponentFactory() {
        return componentFactory;
    }
}
