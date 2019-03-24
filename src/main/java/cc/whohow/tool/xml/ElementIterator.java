package cc.whohow.tool.xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Iterator;

public class ElementIterator implements Iterator<Element> {
    protected NodeList elements;
    protected int index = 0;

    public ElementIterator(NodeList elements) {
        this.elements = elements;
    }

    @Override
    public boolean hasNext() {
        return index < elements.getLength();
    }

    @Override
    public Element next() {
        return (Element) elements.item(index++);
    }
}
