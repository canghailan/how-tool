package cc.whohow.tool.app.engine.component;

import cc.whohow.tool.app.engine.Component;
import cc.whohow.tool.app.engine.ImmutableObservableValue;
import cc.whohow.tool.app.engine.ViewModel;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.w3c.dom.Element;

public class ComponentCellFactory implements Callback<TableColumn.CellDataFeatures<JsonNode, Node>, ObservableValue<Node>> {
    protected final Component<?> component;
    protected final Element element;
    protected final ViewModel vm;

    public ComponentCellFactory(Component<?> component, Element element, ViewModel vm) {
        this.component = component;
        this.element = element;
        this.vm = vm;
    }

    @Override
    public ObservableValue<Node> call(TableColumn.CellDataFeatures<JsonNode, Node> cellDataFeatures) {
        return new ImmutableObservableValue<>(component.apply(element, vm));
    }
}
