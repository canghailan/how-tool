package cc.whohow.tool.engine.component;

import cc.whohow.tool.engine.Component;
import cc.whohow.tool.engine.ImmutableObservableValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.w3c.dom.Element;

public class ComponentCellFactory implements Callback<TableColumn.CellDataFeatures<JsonNode, Node>, ObservableValue<Node>> {
    protected final Component<?> component;
    protected final Element element;
    protected final ObjectNode data;

    public ComponentCellFactory(Component<?> component, Element element, ObjectNode data) {
        this.component = component;
        this.element = element;
        this.data = data;
    }

    @Override
    public ObservableValue<Node> call(TableColumn.CellDataFeatures<JsonNode, Node> cellDataFeatures) {
        return new ImmutableObservableValue<>(component.apply(element, data));
    }
}
