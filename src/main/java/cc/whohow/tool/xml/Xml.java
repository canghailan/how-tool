package cc.whohow.tool.xml;

import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Xml {
    private static final DocumentBuilderFactory BUILDER_FACTORY = DocumentBuilderFactory.newInstance();
    private static final XPathFactory X_PATH_FACTORY = XPathFactory.newInstance();
    private static final Map<String, XPathExpression> CACHE = new ConcurrentHashMap<>();

    @SneakyThrows
    public static Document parse(String xml) {
        return BUILDER_FACTORY.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
    }

    @SneakyThrows
    public static Document parse(URL url) {
        return BUILDER_FACTORY.newDocumentBuilder().parse(new InputSource(url.openStream()));
    }

    public static Document loadClasspath(String classpath) {
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

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <T extends Node> T querySelector(Object item, String expression) {
        return (T) CACHE.computeIfAbsent(expression, Xml::compile).evaluate(item, XPathConstants.NODE);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <T extends NodeList> T querySelectorAll(Object item, String expression) {
        return (T) CACHE.computeIfAbsent(expression, Xml::compile).evaluate(item, XPathConstants.NODESET);
    }
}
