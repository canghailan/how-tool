package cc.whohow.tool.app.engine.widget;

public interface State<W extends StatefulWidget> {
    W getWidget();
    BuildContext getBuildContext();
    void setState(Runnable fn);
    void dispose();
    Widget build(BuildContext context);

}
