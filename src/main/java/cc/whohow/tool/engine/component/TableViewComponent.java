package cc.whohow.tool.engine.component;

import cc.whohow.tool.engine.AbstractComponent;
import cc.whohow.tool.engine.Component;
import cc.whohow.tool.engine.ImmutableObservableJsonValue;
import cc.whohow.tool.engine.ViewModel;
import cc.whohow.tool.xml.Xml;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TableViewComponent extends AbstractComponent<TableView<JsonNode>> {
    protected TableView<JsonNode> tableView;

    @Override
    public TableView<JsonNode> apply(Element element, ViewModel<?> vm) {
        if (tableView == null) {
            tableView = new TableView<>();
            NodeList tableColumnNodeList = Xml.querySelectorAll(element, "TableColumn");
            for (int i = 0; i < tableColumnNodeList.getLength(); i++) {
                Element tableColumn = (Element) tableColumnNodeList.item(i);
                tableView.getColumns().add(applyTableColumn(tableColumn, vm));
            }
            tableView.setTableMenuButtonVisible(
                    vm.get("tableMenuButtonVisible").join().asBoolean(true));
            tableView.setEditable(
                    vm.get("editable").join().asBoolean(true));
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }

        setItems(vm.get(element.getAttribute(":items")).join());

        return tableView;
    }

    protected void setItems(JsonNode items) {
        tableView.setItems(new ImmutableObservableJsonValue(items));
    }

    protected TableColumn<JsonNode, ?> applyTableColumn(Element element, ViewModel vm) {
        String text = element.getAttribute("text");
        String cellValue = element.getAttribute("cellValue");
        if (cellValue.isEmpty()) {
            Element cell = Xml.querySelector(element, "*");
            Component<?> component = getComponentFactory().apply(cell.getTagName());
            TableColumn<JsonNode, Node> tableColumn = new TableColumn<>(text);
            tableColumn.setCellValueFactory(new ComponentCellFactory(component, cell, vm));
            return tableColumn;
        } else {
            TableColumn<JsonNode, JsonNode> tableColumn = new TableColumn<>(text);
            tableColumn.setCellValueFactory(new JsonCellValueFactory(cellValue));
            return tableColumn;
        }
    }
}
