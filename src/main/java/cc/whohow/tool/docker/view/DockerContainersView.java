package cc.whohow.tool.docker.view;

import cc.whohow.tool.json.Json;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.scene.Parent;
import lombok.extern.log4j.Log4j2;
import org.w3c.dom.Element;

@Log4j2
public class DockerContainersView extends DockerComponent {
    public DockerContainersView() {
        super("cc/whohow/tool/docker/view/DockerContainers.xml");
    }

    @Override
    public Parent apply(Element element, ObjectNode data) {
        if (docker != null) {
            data.set("containers", Json.from(docker.listContainersCmd().exec()));
        }
        log.debug("{}", data);
        return super.apply(element, data);
    }
}
