package cc.whohow.tool.app.engine.widget;

public abstract class InheritedWidget extends ProxyWidget {
    abstract boolean updateShouldNotify(InheritedWidget oldWidget);
}
