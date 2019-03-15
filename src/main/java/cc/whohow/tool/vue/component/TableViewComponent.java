package cc.whohow.tool.vue.component;

import cc.whohow.tool.vue.ImmutableObservableJsonValue;
import cc.whohow.tool.vue.Component;
import cc.whohow.tool.xml.Xml;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.SneakyThrows;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import java.util.function.BiFunction;

public class TableViewComponent implements BiFunction<Element, Component, Parent> {
    private static final XPathExpression TABLE_COLUMN = Xml.compile("TableColumn");

    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public Parent apply(Element tableViewNode, Component component) {
        TableView tableView = new TableView<JsonNode>();
        NodeList tableColumnNodeList = (NodeList) TABLE_COLUMN.evaluate(tableViewNode, XPathConstants.NODESET);
        for (int i = 0; i < tableColumnNodeList.getLength(); i++) {
            Element node = (Element) tableColumnNodeList.item(i);
            String label = node.getAttribute("label");
            String value = node.getAttribute("value");
            TableColumn<JsonNode, JsonNode> tableColumn = new TableColumn<>(label);
            tableColumn.setCellValueFactory(new JsonCellValueFactory(value));
            tableView.getColumns().add(tableColumn);
        }
        tableView.setItems(new ImmutableObservableJsonValue(component.evaluate(tableViewNode.getAttribute(":data"))));
        return tableView;
    }
}
