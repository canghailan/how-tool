package cc.whohow.tool.engine.component;

import cc.whohow.tool.engine.AbstractComponent;
import cc.whohow.tool.engine.Component;
import cc.whohow.tool.engine.ViewModel;
import cc.whohow.tool.xml.Elements;
import cc.whohow.tool.xml.Xml;
import javafx.scene.layout.HBox;
import org.w3c.dom.Element;

public class HBoxComponent extends AbstractComponent<HBox> {
    @Override
    public HBox apply(Element element, ViewModel vm) {
        HBox hBox = new HBox();
        for (Element child : new Elements(Xml.querySelectorAll(element, "*"))) {
            Component<?> component = componentFactory.apply(child.getTagName());
            component.setComponentFactory(getComponentFactory());
            hBox.getChildren().add(component.apply(child, vm));
        }
        return hBox;
    }
}