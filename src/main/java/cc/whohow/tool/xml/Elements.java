package cc.whohow.tool.xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Elements implements Iterable<Element> {
    protected NodeList elements;

    public Elements(NodeList elements) {
        this.elements = elements;
    }

    public int size() {
        return elements.getLength();
    }

    @Override
    public Iterator<Element> iterator() {
        return new ElementIterator(elements);
    }

    @Override
    public Spliterator<Element> spliterator() {
        return Spliterators.spliterator(iterator(), elements.getLength(),
                Spliterator.ORDERED |
                        Spliterator.DISTINCT |
                        Spliterator.SIZED |
                        Spliterator.NONNULL |
                        Spliterator.IMMUTABLE);
    }

    public Stream<Element> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
