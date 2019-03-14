package cc.whohow.tool.xml;

import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.net.URL;

public class Xml {
    private static final DocumentBuilderFactory BUILDER_FACTORY = DocumentBuilderFactory.newInstance();
    private static final XPathFactory X_PATH_FACTORY = XPathFactory.newInstance();

    @SneakyThrows
    public static Document parse(String xml) {
        return BUILDER_FACTORY.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
    }

    @SneakyThrows
    public static Document parse(URL url) {
        return BUILDER_FACTORY.newDocumentBuilder().parse(new InputSource(url.openStream()));
    }

    public static Document load(String classpath) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(classpath);
        if (url == null) {
            throw new IllegalArgumentException(classpath);
        }
        return parse(url);
    }

    @SneakyThrows
    public static XPathExpression compile(String expression) {
        return X_PATH_FACTORY.newXPath().compile(expression);
    }
}
