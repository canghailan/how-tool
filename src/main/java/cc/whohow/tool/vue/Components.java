package cc.whohow.tool.vue;

import cc.whohow.tool.vue.component.TableViewComponent;
import javafx.scene.Parent;
import org.w3c.dom.Element;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Components implements Function<String, BiFunction<Element, Component, Parent>> {
    private static final Components DEFAULT = new Components();
    private static final Map<String, BiFunction<Element, Component, Parent>> DEFAULT_COMPONENTS = defaultComponents();

    private static Map<String, BiFunction<Element, Component, Parent>> defaultComponents() {
        Map<String, BiFunction<Element, Component, Parent>> components = new HashMap<>();
        components.put("TableView", new TableViewComponent());
        return Collections.unmodifiableMap(components);
    }

    public static Components getDefault() {
        return DEFAULT;
    }

    private final Map<String, BiFunction<Element, Component, Parent>> components;

    private Components() {
        this.components = DEFAULT_COMPONENTS;
    }

    public Components(Map<String, BiFunction<Element, Component, Parent>> components) {
        this.components = new HashMap<>(DEFAULT_COMPONENTS);
        this.components.putAll(components);
    }

    @Override
    public BiFunction<Element, Component, Parent> apply(String name) {
        return components.get(name);
    }
}
