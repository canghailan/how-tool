package cc.whohow.tool.engine.component;

import cc.whohow.tool.engine.AbstractComponent;
import cc.whohow.tool.engine.ViewModel;
import cc.whohow.tool.xml.Xml;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class VBoxComponent extends AbstractComponent<VBox> {
    @Override
    public VBox apply(Element element, ViewModel vm) {
        VBox vBox = new VBox();
        NodeList nodeList = Xml.querySelectorAll(element, "*");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element node = (Element) nodeList.item(i);
            Parent child = componentFactory.apply(node.getTagName()).apply(node, vm);
            vBox.getChildren().add(child);
        }
        return vBox;
    }
}