package cc.whohow.tool.engine.component;

import cc.whohow.tool.engine.AbstractComponent;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.scene.control.Button;
import org.w3c.dom.Element;

public class ButtonComponent extends AbstractComponent<Button> {
    @Override
    public Button apply(Element element, ObjectNode data) {
        String text = element.getAttribute("text");

        Button button = new Button();
        button.setText(text);
        return button;
    }
}
