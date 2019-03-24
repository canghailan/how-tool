package cc.whohow.tool.engine;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ImmutableObservableValue<T> implements ObservableValue<T> {
    protected final T value;

    public ImmutableObservableValue(T value) {
        this.value = value;
    }

    @Override
    public void addListener(ChangeListener<? super T> listener) {

    }

    @Override
    public void removeListener(ChangeListener<? super T> listener) {

    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
