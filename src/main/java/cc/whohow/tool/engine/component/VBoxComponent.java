package cc.whohow.tool.engine.component;

import cc.whohow.tool.engine.AbstractComponent;
import cc.whohow.tool.engine.Component;
import cc.whohow.tool.engine.ViewModel;
import cc.whohow.tool.xml.Elements;
import cc.whohow.tool.xml.Xml;
import javafx.scene.layout.VBox;
import org.w3c.dom.Element;

public class VBoxComponent extends AbstractComponent<VBox> {
    @Override
    public VBox apply(Element element, ViewModel vm) {
        VBox vBox = new VBox();
        for (Element child : new Elements(Xml.querySelectorAll(element, "*"))) {
            Component<?> component = componentFactory.apply(child.getTagName());
            component.setComponentFactory(getComponentFactory());
            vBox.getChildren().add(component.apply(child, vm));
        }
        return vBox;
    }
}