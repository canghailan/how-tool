package cc.whohow.tool.app.engine.component;

import cc.whohow.tool.app.engine.AbstractComponent;
import cc.whohow.tool.app.engine.Component;
import cc.whohow.tool.app.engine.ViewModel;
import cc.whohow.tool.xml.Elements;
import cc.whohow.tool.xml.Xml;
import javafx.scene.Group;
import org.w3c.dom.Element;

public class GroupComponent extends AbstractComponent<Group> {
    @Override
    public Group apply(Element element, ViewModel vm) {
        Group group = new Group();
        for (Element child : new Elements(Xml.querySelectorAll(element, "*"))) {
            Component<?> component = componentFactory.apply(child.getTagName());
            component.setComponentFactory(getComponentFactory());
            group.getChildren().add(component.apply(child, vm));
        }
        return group;
    }
}
