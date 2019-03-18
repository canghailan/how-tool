package cc.whohow.tool.vue.component;

import cc.whohow.tool.json.Json;
import cc.whohow.tool.vue.AbstractComponent;
import cc.whohow.tool.vue.ImmutableObservableJsonValue;
import cc.whohow.tool.xml.Xml;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.scene.control.*;
import lombok.SneakyThrows;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

public class TableViewComponent extends AbstractComponent<TableView<JsonNode>> {
    private static final XPathExpression TABLE_COLUMN = Xml.compile("TableColumn");
    private static final XPathExpression CONTEXT_MENU = Xml.compile("ContextMenu");

    @Override
    @SneakyThrows
    public TableView<JsonNode> apply(Element element, ObjectNode data) {
        TableView<JsonNode> tableView = new TableView<>();
        NodeList tableColumnNodeList = (NodeList) TABLE_COLUMN.evaluate(element, XPathConstants.NODESET);
        for (int i = 0; i < tableColumnNodeList.getLength(); i++) {
            Element node = (Element) tableColumnNodeList.item(i);
            String text = node.getAttribute("text");
            String cellValue = node.getAttribute("cellValue");
            TableColumn<JsonNode, JsonNode> tableColumn = new TableColumn<>(text);
            tableColumn.setCellValueFactory(new JsonCellValueFactory(cellValue));
            tableView.getColumns().add(tableColumn);
        }
        tableView.setTableMenuButtonVisible(
                data.path("tableMenuButtonVisible").asBoolean(true));
        tableView.setEditable(
                data.path("editable").asBoolean(true));
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setContextMenu(new ContextMenu(new MenuItem("复制")));
        tableView.setOnKeyPressed((e) -> {
            System.out.println(e.getCode());
        });
        tableView.setItems(new ImmutableObservableJsonValue(Json.compile(
                element.getAttribute(":items")).evaluate(data)));
        return tableView;
    }

    @Override
    public void close() throws Exception {

    }
}
