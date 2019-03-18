package cc.whohow.tool.vue;

import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.scene.Parent;
import org.w3c.dom.Element;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public interface Component<V extends Parent> extends
        WritableValue<ObjectNode>,
        ObservableValue<ObjectNode>,
        Supplier<V>,
        BiFunction<Element, ObjectNode, V>,
        AutoCloseable {

}
