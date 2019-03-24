package cc.whohow.tool.app.engine.component;

import cc.whohow.tool.app.engine.AbstractComponent;
import cc.whohow.tool.app.engine.BindingAttr;
import cc.whohow.tool.app.engine.BindingAttrs;
import cc.whohow.tool.app.engine.ViewModel;
import javafx.scene.control.Button;
import org.w3c.dom.Element;

public class ButtonComponent extends AbstractComponent<Button> {
    @Override
    public Button apply(Element element, ViewModel vm) {
        Button button = new Button();
        for (BindingAttr attr : new BindingAttrs(element.getAttributes(), vm)) {
            switch (attr.getBindingKey()) {
                case "text": {
                    button.setText(attr.getStringValue());
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return button;
    }
}
