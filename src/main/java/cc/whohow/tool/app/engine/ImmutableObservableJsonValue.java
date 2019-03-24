package cc.whohow.tool.app.engine;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.NullNode;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings("all")
public class ImmutableObservableJsonValue extends JsonNode implements ObservableValue<JsonNode>, ObservableList<JsonNode> {
    private final JsonNode json;

    public ImmutableObservableJsonValue(JsonNode json) {
        this.json = (json == null) ? NullNode.getInstance() : json;
    }

    @Override
    public <T extends JsonNode> T deepCopy() {
        return json.deepCopy();
    }

    @Override
    public int size() {
        return json.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        Object[] a = new Object[size()];
        int index = 0;
        for (JsonNode e : this) {
            a[index++] = e;
        }
        return a;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
        }
        int i = 0;
        for (JsonNode e : this) {
            a[i++] = (T) e;
        }
        return a;
    }

    @Override
    public boolean add(JsonNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends JsonNode> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends JsonNode> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isMissingNode() {
        return json.isMissingNode();
    }

    @Override
    public boolean isArray() {
        return json.isArray();
    }

    @Override
    public boolean isObject() {
        return json.isObject();
    }

    @Override
    public JsonNode get(int index) {
        return json.get(index);
    }

    @Override
    public JsonNode set(int index, JsonNode element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, JsonNode element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonNode remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            o = NullNode.getInstance();
        }
        int i = 0;
        for (JsonNode e : json) {
            if (o.equals(e)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            o = NullNode.getInstance();
        }
        for (int i = json.size() - 1; i >= 0; i--) {
            if (o.equals(json.get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<JsonNode> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<JsonNode> listIterator(int index) {
        return new ListIter(index);
    }

    @Override
    public List<JsonNode> subList(int fromIndex, int toIndex) {
        return new SubList(fromIndex, toIndex);
    }

    @Override
    public JsonNode get(String fieldName) {
        return json.get(fieldName);
    }

    @Override
    public JsonNode path(String fieldName) {
        return json.path(fieldName);
    }

    @Override
    public JsonNode path(int index) {
        return json.path(index);
    }

    @Override
    public Iterator<String> fieldNames() {
        return json.fieldNames();
    }

    @Override
    public JsonNode _at(JsonPointer ptr) {
        return json.at(ptr);
    }

    @Override
    public JsonNodeType getNodeType() {
        return json.getNodeType();
    }

    @Override
    public boolean isIntegralNumber() {
        return json.isIntegralNumber();
    }

    @Override
    public boolean isFloatingPointNumber() {
        return json.isFloatingPointNumber();
    }

    @Override
    public boolean isShort() {
        return json.isShort();
    }

    @Override
    public boolean isInt() {
        return json.isInt();
    }

    @Override
    public boolean isLong() {
        return json.isLong();
    }

    @Override
    public boolean isFloat() {
        return json.isFloat();
    }

    @Override
    public boolean isDouble() {
        return json.isDouble();
    }

    @Override
    public boolean isBigDecimal() {
        return json.isBigDecimal();
    }

    @Override
    public boolean isBigInteger() {
        return json.isBigInteger();
    }

    @Override
    public boolean canConvertToInt() {
        return json.canConvertToInt();
    }

    @Override
    public boolean canConvertToLong() {
        return json.canConvertToLong();
    }

    @Override
    public String textValue() {
        return json.textValue();
    }

    @Override
    public byte[] binaryValue() throws IOException {
        return json.binaryValue();
    }

    @Override
    public boolean booleanValue() {
        return json.booleanValue();
    }

    @Override
    public Number numberValue() {
        return json.numberValue();
    }

    @Override
    public short shortValue() {
        return json.shortValue();
    }

    @Override
    public int intValue() {
        return json.intValue();
    }

    @Override
    public long longValue() {
        return json.longValue();
    }

    @Override
    public float floatValue() {
        return json.floatValue();
    }

    @Override
    public double doubleValue() {
        return json.doubleValue();
    }

    @Override
    public BigDecimal decimalValue() {
        return json.decimalValue();
    }

    @Override
    public BigInteger bigIntegerValue() {
        return json.bigIntegerValue();
    }

    @Override
    public String asText() {
        return json.asText();
    }

    @Override
    public String asText(String defaultValue) {
        return json.asText(defaultValue);
    }

    @Override
    public int asInt() {
        return json.asInt();
    }

    @Override
    public int asInt(int defaultValue) {
        return json.asInt(defaultValue);
    }

    @Override
    public long asLong() {
        return json.asLong();
    }

    @Override
    public long asLong(long defaultValue) {
        return json.asLong(defaultValue);
    }

    @Override
    public double asDouble() {
        return json.asDouble();
    }

    @Override
    public double asDouble(double defaultValue) {
        return json.asDouble(defaultValue);
    }

    @Override
    public boolean asBoolean() {
        return json.asBoolean();
    }

    @Override
    public boolean asBoolean(boolean defaultValue) {
        return json.asBoolean(defaultValue);
    }

    @Override
    public boolean has(String fieldName) {
        return json.has(fieldName);
    }

    @Override
    public boolean has(int index) {
        return json.has(index);
    }

    @Override
    public boolean hasNonNull(String fieldName) {
        return json.hasNonNull(fieldName);
    }

    @Override
    public boolean hasNonNull(int index) {
        return json.hasNonNull(index);
    }

    @Override
    public Iterator<JsonNode> elements() {
        return json.elements();
    }

    @Override
    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return json.fields();
    }

    @Override
    public JsonNode findValue(String fieldName) {
        return json.findValue(fieldName);
    }

    @Override
    public JsonNode findPath(String fieldName) {
        return json.findPath(fieldName);
    }

    @Override
    public JsonNode findParent(String fieldName) {
        return json.findParent(fieldName);
    }

    @Override
    public List<JsonNode> findValues(String fieldName, List<JsonNode> foundSoFar) {
        return json.findValues(fieldName, foundSoFar);
    }

    @Override
    public List<String> findValuesAsText(String fieldName, List<String> foundSoFar) {
        return json.findValuesAsText(fieldName, foundSoFar);
    }

    @Override
    public List<JsonNode> findParents(String fieldName, List<JsonNode> foundSoFar) {
        return json.findParents(fieldName, foundSoFar);
    }

    @Override
    public JsonNode with(String propertyName) {
        return json.with(propertyName);
    }

    @Override
    public JsonNode withArray(String propertyName) {
        return json.withArray(propertyName);
    }

    @Override
    public boolean equals(Comparator<JsonNode> comparator, JsonNode other) {
        return json.equals(comparator, other);
    }

    @Override
    public boolean equals(Object o) {
        return json.equals(o);
    }

    @Override
    public boolean isEmpty(SerializerProvider serializers) {
        return json.isEmpty(serializers);
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        json.serialize(gen, serializers);
    }

    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        json.serializeWithType(gen, serializers, typeSer);
    }

    @Override
    public JsonToken asToken() {
        return json.asToken();
    }

    @Override
    public JsonParser.NumberType numberType() {
        return json.numberType();
    }

    @Override
    public JsonParser traverse() {
        return json.traverse();
    }

    @Override
    public JsonParser traverse(ObjectCodec codec) {
        return json.traverse(codec);
    }

    @Override
    public void forEach(Consumer<? super JsonNode> action) {
        json.forEach(action);
    }

    @Override
    public Spliterator<JsonNode> spliterator() {
        return json.spliterator();
    }

    @Override
    public void addListener(ChangeListener<? super JsonNode> listener) {

    }

    @Override
    public void removeListener(ChangeListener<? super JsonNode> listener) {

    }

    @Override
    public JsonNode getValue() {
        return this;
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

    @Override
    public void addListener(ListChangeListener<? super JsonNode> listener) {

    }

    @Override
    public void removeListener(ListChangeListener<? super JsonNode> listener) {

    }

    @Override
    public boolean addAll(JsonNode... elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setAll(JsonNode... elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setAll(Collection<? extends JsonNode> col) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(JsonNode... elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(JsonNode... elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(int from, int to) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return json.asText(null);
    }

    @Override
    public int hashCode() {
        return json.hashCode();
    }

    class ListIter implements ListIterator<JsonNode> {
        int index;

        ListIter(int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public JsonNode next() {
            return get(index++);
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public JsonNode previous() {
            return get(--index);
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(JsonNode node) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(JsonNode node) {
            throw new UnsupportedOperationException();
        }
    }

    class SubList extends AbstractList<JsonNode> {
        final int from;
        final int to;

        SubList(int from, int to) {
            if (from > to) {
                throw new IllegalArgumentException();
            }
            this.from = from;
            this.to = to;
        }

        @Override
        public JsonNode get(int index) {
            return get(from + index);
        }

        @Override
        public int size() {
            return to - from;
        }
    }
}
