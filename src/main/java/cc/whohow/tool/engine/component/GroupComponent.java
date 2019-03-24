package cc.whohow.tool.engine.component;

import cc.whohow.tool.engine.AbstractComponent;
import cc.whohow.tool.engine.ViewModel;
import cc.whohow.tool.xml.Xml;
import javafx.scene.Group;
import javafx.scene.Parent;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GroupComponent extends AbstractComponent<Group> {
    @Override
    public Group apply(Element element, ViewModel vm) {
        Group group = new Group();
        NodeList nodeList = Xml.querySelectorAll(element, "*");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element node = (Element) nodeList.item(i);
            Parent control = componentFactory.apply(node.getTagName()).apply(node, vm);
            group.getChildren().add(i, control);
        }
        return group;
    }
}
