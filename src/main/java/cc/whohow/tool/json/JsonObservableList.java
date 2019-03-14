package cc.whohow.tool.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

public class JsonObservableList implements ObservableList<JsonNode> {
    private ArrayNode list;

    @Override
    public void addListener(ListChangeListener<? super JsonNode> listener) {

    }

    @Override
    public void removeListener(ListChangeListener<? super JsonNode> listener) {

    }

    @Override
    public boolean addAll(JsonNode... elements) {
        list.addAll(Arrays.asList(elements));
        return elements.length > 0;
    }

    @Override
    public boolean setAll(JsonNode... elements) {
        return setAll(Arrays.asList(elements));
    }

    @Override
    public boolean setAll(Collection<? extends JsonNode> col) {
        list.removeAll();
        list.addAll(col);
        return true;
    }

    @Override
    public boolean removeAll(JsonNode... elements) {
        Set<JsonNode> set = new HashSet<>(Arrays.asList(elements));
        int n = 0;
        Iterator<JsonNode> i = list.iterator();
        while (i.hasNext()) {
            if (set.contains(i.next())) {
                i.remove();
                n++;
            }
        }
        return n > 0;
    }

    @Override
    public boolean retainAll(JsonNode... elements) {
        Set<JsonNode> set = new HashSet<>(Arrays.asList(elements));
        int n = 0;
        Iterator<JsonNode> i = list.iterator();
        while (i.hasNext()) {
            if (!set.contains(i.next())) {
                i.remove();
                n++;
            }
        }
        return n > 0;
    }

    @Override
    public void remove(int from, int to) {
        for (int i = to - 1; i >= from; i--) {
            list.remove(i);
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        Iterator<JsonNode> i = list.iterator();
        while (i.hasNext()) {
            if (o.equals(i.next())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<JsonNode> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(JsonNode jsonNode) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends JsonNode> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends JsonNode> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public JsonNode get(int index) {
        return null;
    }

    @Override
    public JsonNode set(int index, JsonNode element) {
        return null;
    }

    @Override
    public void add(int index, JsonNode element) {

    }

    @Override
    public JsonNode remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<JsonNode> listIterator() {
        return null;
    }

    @Override
    public ListIterator<JsonNode> listIterator(int index) {
        return null;
    }

    @Override
    public List<JsonNode> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
