package cc.whohow.tool.app.engine.component;

import cc.whohow.tool.app.engine.AbstractComponent;
import cc.whohow.tool.app.engine.ViewModel;
import javafx.scene.control.Button;
import org.w3c.dom.Element;

public class ButtonComponent extends AbstractComponent<Button> {
    @Override
    public Button apply(Element element, ViewModel data) {
        String text = element.getAttribute("text");

        Button button = new Button();
        button.setText(text);
        return button;
    }
}
