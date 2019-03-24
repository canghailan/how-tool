package cc.whohow.tool.engine.component;

import cc.whohow.tool.engine.AbstractComponent;
import cc.whohow.tool.xml.Xml;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.scene.Group;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GroupComponent extends AbstractComponent<Group> {
    @Override
    @SneakyThrows
    public Group apply(Element element, ObjectNode data) {
        Group group = new Group();
        NodeList nodeList = Xml.querySelectorAll(element, "*");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element node = (Element) nodeList.item(i);
            Parent control = componentFactory.apply(node.getTagName()).apply(node, data);
            group.getChildren().add(i, control);
        }
        return group;
    }
}
