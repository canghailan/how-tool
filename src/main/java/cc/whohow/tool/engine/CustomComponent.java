package cc.whohow.tool.engine;

import cc.whohow.tool.xml.Xml;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

public class CustomComponent extends AbstractComponent<Parent> {
    protected Components components;
    protected Element rootComponent;

    public CustomComponent(String classpath) {
        this(Xml.loadClasspath(classpath));
    }

    @SneakyThrows
    public CustomComponent(Document vue) {
        this(vue, Components.getDefault());
    }

    @SneakyThrows
    public CustomComponent(Document vue, Components components) {
        this.components = components;
        this.rootComponent = Xml.querySelector(vue, "/*/template/*");
    }

    @Override
    public Parent apply(Element element, ObjectNode data) {
        return components.apply(rootComponent.getTagName()).apply(rootComponent, data);
    }

    @Override
    public void close() throws Exception {
    }
}
