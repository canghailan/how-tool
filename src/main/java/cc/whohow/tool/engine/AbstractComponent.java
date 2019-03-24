package cc.whohow.tool.engine;

import javafx.scene.Parent;

import java.util.function.Function;

public abstract class AbstractComponent<V extends Parent> implements Component<V> {
    protected Function<String, Component<? extends Parent>> componentFactory = Components.getDefault();

    @Override
    public Function<String, Component<? extends Parent>> getComponentFactory() {
        return componentFactory;
    }

    @Override
    public void setComponentFactory(Function<String, Component<? extends Parent>> componentFactory) {
        this.componentFactory = componentFactory;
    }
}
