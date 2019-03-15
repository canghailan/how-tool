package cc.whohow.tool.vue;

import cc.whohow.tool.json.Json;
import cc.whohow.tool.xml.Xml;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import javafx.scene.Parent;

import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import java.util.function.BiFunction;

public class Component implements BiFunction<Element, JsonNode, Parent>, AutoCloseable {
    private static final XPathExpression ROOT = Xml.compile("/*/template/*");
    private static final XPathExpression STYLE = Xml.compile("/*/style");

    private Components components;
    private Element root;
    private Element style;
    private JsonNode data = NullNode.getInstance();
    private Parent view;

    @SneakyThrows
    public Component(Document vue) {
        this(vue, Components.getDefault());
    }

    @SneakyThrows
    public Component(Document vue, Components components) {
        this.components = components;
        this.root = (Element)ROOT.evaluate(vue,  XPathConstants.NODE);
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        if (data == null) {
            this.data = NullNode.getInstance();
        } else {
            this.data = data;
        }
    }

    public JsonNode evaluate(String expression) {
        return Json.evaluate(data, expression);
    }

    @Override
    public Parent apply(Element element, JsonNode data) {
        return components.apply(root.getTagName()).apply(root, this);
    }

    @Override
    public void close() throws Exception {

    }
}
