package cc.whohow.tool.engine.component;

import cc.whohow.tool.engine.AbstractComponent;
import cc.whohow.tool.engine.ViewModel;
import cc.whohow.tool.xml.Xml;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class HBoxComponent extends AbstractComponent<HBox> {
    @Override
    public HBox apply(Element element, ViewModel vm) {
        HBox hBox = new HBox();
        NodeList nodeList = Xml.querySelectorAll(element, "*");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element node = (Element) nodeList.item(i);
            Parent child = componentFactory.apply(node.getTagName()).apply(node, vm);
            hBox.getChildren().add(child);
        }
        return hBox;
    }
}