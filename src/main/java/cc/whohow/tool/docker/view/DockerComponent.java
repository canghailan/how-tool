package cc.whohow.tool.docker.view;

import cc.whohow.tool.engine.CustomComponent;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.netty.NettyDockerCmdExecFactory;

public class DockerComponent extends CustomComponent {
    protected DockerClient docker;

    public DockerComponent(String dom) {
        super(dom);
    }

    @Override
    public void setValue(ObjectNode value) {
        if (docker == null) {
            docker = DockerClientBuilder.getInstance(DefaultDockerClientConfig.createDefaultConfigBuilder()
                    .withDockerHost(value.path("host").asText())
                    .withDockerTlsVerify(true)
                    .withDockerCertPath(value.path("certPath").asText())
                    .build())
                    .withDockerCmdExecFactory(new NettyDockerCmdExecFactory())
                    .build();
        }
        super.setValue(value);
    }

    @Override
    public void close() throws Exception {
        try {
            docker.close();
        } finally {
            super.close();
        }
    }
}
