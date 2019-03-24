package cc.whohow.tool.xml;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;

import java.util.Iterator;

public class AttrIterator implements Iterator<Attr> {
    protected NamedNodeMap attrs;
    protected int index = 0;

    public AttrIterator(NamedNodeMap attrs) {
        this.attrs = attrs;
    }

    @Override
    public boolean hasNext() {
        return index < attrs.getLength();
    }

    @Override
    public Attr next() {
        return (Attr) attrs.item(index++);
    }
}
