package cc.whohow.tool.app.engine;

import javafx.scene.Parent;
import org.w3c.dom.Element;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface Component<V extends Parent> extends
        BiFunction<Element, ViewModel<?>, V> {
    Function<String, Component<? extends Parent>> getComponentFactory();

    void setComponentFactory(Function<String, Component<? extends Parent>> componentFactory);
}
