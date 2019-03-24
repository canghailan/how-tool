package cc.whohow.tool.app.engine;

import org.w3c.dom.NamedNodeMap;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class BindingAttrs implements Iterable<BindingAttr> {
    private NamedNodeMap attrs;
    private ViewModel<?> vm;

    public BindingAttrs(NamedNodeMap attrs, ViewModel<?> vm) {
        this.attrs = attrs;
        this.vm = vm;
    }

    public int size() {
        return attrs.getLength();
    }

    @Override
    public Iterator<BindingAttr> iterator() {
        return new BindingAttrIterator(attrs, vm);
    }

    @Override
    public Spliterator<BindingAttr> spliterator() {
        return Spliterators.spliterator(iterator(), attrs.getLength(),
                Spliterator.ORDERED |
                        Spliterator.DISTINCT |
                        Spliterator.SIZED |
                        Spliterator.NONNULL |
                        Spliterator.IMMUTABLE);
    }

    public Stream<BindingAttr> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
