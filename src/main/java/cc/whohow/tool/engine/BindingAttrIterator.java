package cc.whohow.tool.engine;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;

import java.util.Iterator;

public class BindingAttrIterator implements Iterator<BindingAttr> {
    private NamedNodeMap attrs;
    private ViewModel<?> vm;
    private int index = 0;

    public BindingAttrIterator(NamedNodeMap attrs, ViewModel<?> vm) {
        this.attrs = attrs;
        this.vm = vm;
    }

    @Override
    public boolean hasNext() {
        return index < attrs.getLength();
    }

    @Override
    public BindingAttr next() {
        return new BindingAttr((Attr) attrs.item(index++), vm);
    }
}
