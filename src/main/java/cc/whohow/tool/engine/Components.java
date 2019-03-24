package cc.whohow.tool.engine;

import cc.whohow.tool.engine.component.ButtonComponent;
import cc.whohow.tool.engine.component.GroupComponent;
import cc.whohow.tool.engine.component.TableViewComponent;
import javafx.scene.Parent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class Components implements Function<String, Component<? extends Parent>> {
    private static final Map<String, Supplier<? extends Component<? extends Parent>>> DEFAULT_COMPONENTS = defaultComponents();
    private static final Components DEFAULT = new Components();
    private final Map<String, Supplier<? extends Component<? extends Parent>>> components;

    private Components() {
        this.components = DEFAULT_COMPONENTS;
    }

    public Components(Map<String, Supplier<? extends Component<? extends Parent>>> components) {
        this.components = new HashMap<>(DEFAULT_COMPONENTS);
        this.components.putAll(components);
    }

    private static Map<String, Supplier<? extends Component<? extends Parent>>> defaultComponents() {
        Map<String, Supplier<? extends Component<? extends Parent>>> components = new HashMap<>();
        components.put("TableView", TableViewComponent::new);
        components.put("Group", GroupComponent::new);
        components.put("Button", ButtonComponent::new);
        return Collections.unmodifiableMap(components);
    }

    public static Components getDefault() {
        return DEFAULT;
    }

    @Override
    public Component<?> apply(String name) {
        return components.get(name).get();
    }
}
