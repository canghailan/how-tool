package cc.whohow.tool.xml;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Attrs implements Iterable<Attr> {
    protected NamedNodeMap attrs;

    public Attrs(NamedNodeMap attrs) {
        this.attrs = attrs;
    }

    public int size() {
        return attrs.getLength();
    }

    @Override
    public Iterator<Attr> iterator() {
        return new AttrIterator(attrs);
    }

    @Override
    public Spliterator<Attr> spliterator() {
        return Spliterators.spliterator(iterator(), attrs.getLength(),
                Spliterator.ORDERED |
                        Spliterator.DISTINCT |
                        Spliterator.SIZED |
                        Spliterator.NONNULL |
                        Spliterator.IMMUTABLE);
    }

    public Stream<Attr> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
