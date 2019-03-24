package cc.whohow.tool.app.engine.component;

import cc.whohow.tool.app.engine.*;
import cc.whohow.tool.xml.Elements;
import cc.whohow.tool.xml.Xml;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.w3c.dom.Element;

public class TableViewComponent extends AbstractComponent<TableView<JsonNode>> {
    protected TableView<JsonNode> tableView;

    @Override
    public TableView<JsonNode> apply(Element element, ViewModel<?> vm) {
        if (tableView == null) {
            tableView = new TableView<>();
            for (Element tableColumn : new Elements(Xml.querySelectorAll(element, "TableColumn"))) {
                tableView.getColumns().add(applyTableColumn(tableColumn, vm));
            }
            for (BindingAttr attr : new BindingAttrs(element.getAttributes(), vm)) {
                setAttribute(attr);
            }

            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        } else {
            for (BindingAttr attr : new BindingAttrs(element.getAttributes(), vm)) {
                if ("items".equals(attr.getBindingKey())) {
                    setItems(attr);
                    break;
                }
            }
        }
        return tableView;
    }

    protected void setAttribute(BindingAttr attr) {
        switch (attr.getBindingKey()) {
            case "tableMenuButtonVisible": {
                tableView.setTableMenuButtonVisible(attr.getBooleanValue(true));
                break;
            }
            case "editable": {
                tableView.setEditable(attr.getBooleanValue(true));
                break;
            }
            case "items": {
                setItems(attr);
                break;
            }
            default: {
                break;
            }
        }
    }

    protected void setItems(BindingAttr attr) {
        tableView.setItems(new ImmutableObservableJsonValue(attr.getBindingValue().join()));
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
