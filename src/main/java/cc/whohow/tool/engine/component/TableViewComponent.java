package cc.whohow.tool.engine.component;

import cc.whohow.tool.engine.Component;
import cc.whohow.tool.json.Json;
import cc.whohow.tool.engine.AbstractComponent;
import cc.whohow.tool.engine.ImmutableObservableJsonValue;
import cc.whohow.tool.xml.Xml;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.scene.Node;
import javafx.scene.control.*;
import lombok.SneakyThrows;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TableViewComponent extends AbstractComponent<TableView<JsonNode>> {
    @Override
    @SneakyThrows
    public TableView<JsonNode> apply(Element element, ObjectNode data) {
        TableView<JsonNode> tableView = new TableView<>();
        NodeList tableColumnNodeList = Xml.querySelectorAll(element, "TableColumn");
        for (int i = 0; i < tableColumnNodeList.getLength(); i++) {
            Element tableColumn = (Element) tableColumnNodeList.item(i);
            tableView.getColumns().add(applyTableColumn(tableColumn, data));
        }
        tableView.setTableMenuButtonVisible(
                data.path("tableMenuButtonVisible").asBoolean(true));
        tableView.setEditable(
                data.path("editable").asBoolean(true));
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setOnKeyPressed((e) -> {
            System.out.println(e.getCode());
        });
        tableView.setItems(new ImmutableObservableJsonValue(Json.compile(
                element.getAttribute(":items")).evaluate(data)));
        return tableView;
    }

    protected TableColumn<JsonNode, ?> applyTableColumn(Element element, ObjectNode data) {
        String text = element.getAttribute("text");
        String cellValue = element.getAttribute("cellValue");
        if (cellValue.isEmpty()) {
            Element cell = Xml.querySelector(element, "*");
            Component<?> component = getComponentFactory().apply(cell.getTagName());
            TableColumn<JsonNode, Node> tableColumn = new TableColumn<>();
            tableColumn.setCellValueFactory(new ComponentCellFactory(component, cell, data));
            return tableColumn;
        } else {
            TableColumn<JsonNode, JsonNode> tableColumn = new TableColumn<>(text);
            tableColumn.setCellValueFactory(new JsonCellValueFactory(cellValue));
            return tableColumn;
        }
    }

    @Override
    public void close() throws Exception {

    }
}
