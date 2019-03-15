package cc.whohow.tool.docker.view;

import cc.whohow.tool.vue.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import org.w3c.dom.Document;

public class DockerComponent extends Component {
    private DockerClient docker;

    public DockerComponent(Document vue, JsonNode data) {
        super(vue, data);
        this.docker = DockerClientBuilder.getInstance(DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://master1g10.cs-cn-hangzhou.aliyun.com:20034")
                .withDockerTlsVerify(true)
                .withDockerCertPath("private")
                .build()).build();
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
