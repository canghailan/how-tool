package cc.whohow.tool.xml;

import lombok.SneakyThrows;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

public class TestXml {
    @Test
    @SneakyThrows
    public void test() {
        Document dom = Xml.loadClasspath("cc/whohow/tool/docker/view/Containers.xml");
        XPathExpression selector = Xml.compile("/app/TableView/TableColumn");
        NodeList nodeList = (NodeList) selector.evaluate(dom, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element node = (Element) nodeList.item(i);
            System.out.println(node.getAttribute("label"));
            System.out.println(node.getAttribute("value"));
        }
        System.out.println();
    }
}
