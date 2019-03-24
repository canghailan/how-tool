package cc.whohow.tool.docker.view;

import cc.whohow.tool.engine.CustomComponent;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DockerContainersView extends CustomComponent {
    public DockerContainersView() {
        super("cc/whohow/tool/docker/view/DockerContainers.xml");
    }
}
