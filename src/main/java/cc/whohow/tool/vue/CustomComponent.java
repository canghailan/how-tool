package cc.whohow.tool.vue;

import cc.whohow.tool.xml.Xml;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

public class CustomComponent extends AbstractComponent<Parent> {
    protected static final XPathExpression ROOT = Xml.compile("/*/template/*");
    protected static final XPathExpression STYLE = Xml.compile("/*/style");

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
        this.rootComponent = (Element) ROOT.evaluate(vue, XPathConstants.NODE);
    }

    @Override
    public Parent apply(Element element, ObjectNode data) {
        return components.apply(rootComponent.getTagName()).apply(rootComponent, data);
    }

    @Override
    public void close() throws Exception {
    }
}
