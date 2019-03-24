package cc.whohow.tool.app.engine;

import cc.whohow.tool.xml.Xml;
import javafx.scene.Parent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CustomComponent extends AbstractComponent<Parent> {
    protected Element root;

    public CustomComponent(String classpath) {
        this(Xml.loadClasspath(classpath));
    }

    public CustomComponent(Document vue) {
        this.root = Xml.querySelector(vue, "/*/template/*");
    }

    @Override
    public Parent apply(Element element, ViewModel vm) {
        Component<?> component = componentFactory.apply(root.getTagName());
        component.setComponentFactory(getComponentFactory());
        return component.apply(root, vm);
    }
}
