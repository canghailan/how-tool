package cc.whohow.tool.docker.view;

import cc.whohow.tool.vue.ImmutableObservableJsonValue;
import cc.whohow.tool.vue.JsonCellValueFactory;
import cc.whohow.tool.xml.Xml;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import java.util.function.Function;

public class ContainersComponent implements Function<JsonNode, Parent> {
    private Document dom = Xml.load("cc/whohow/tool/docker/view/Containers.xml");

    @Override
    @SneakyThrows
    public Parent apply(JsonNode data) {
        TableView tableView = new TableView<JsonNode>();

        XPathExpression tableViewSelector = Xml.compile("/app/TableView");
        XPathExpression tableColumnSelector = Xml.compile("/app/TableView/TableColumn");
        Element tableNode = (Element) tableViewSelector.evaluate(dom, XPathConstants.NODE);
        NodeList nodeList = (NodeList) tableColumnSelector.evaluate(dom, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element node = (Element) nodeList.item(i);
            String label = node.getAttribute("label");
            String value = node.getAttribute("value");
            TableColumn<JsonNode, JsonNode> tableColumn = new TableColumn<>(label);
            tableColumn.setCellValueFactory(new JsonCellValueFactory(value));
            tableView.getColumns().add(tableColumn);
        }

        JsonNode tableData = data.path(tableNode.getAttribute(":data"));
        tableView.setItems(new ImmutableObservableJsonValue(tableData));

        return tableView;
    }
}
