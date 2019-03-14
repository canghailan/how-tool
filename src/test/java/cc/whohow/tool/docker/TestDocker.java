package cc.whohow.tool.docker;

import cc.whohow.tool.json.Json;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

@Log4j2
public class TestDocker {
    private static DockerClientConfig config;
    private static DockerClient docker;

    @BeforeClass
    public static void init() {
        config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("")
                .withDockerTlsVerify(true)
                .withDockerCertPath("private")
                .build();
        docker = DockerClientBuilder.getInstance(config).build();
    }

    @AfterClass
    @SneakyThrows
    public static void close() {
        docker.close();
    }

    @Test
    public void test() {
        log.debug(Json.from(docker.listContainersCmd().exec()));
    }
}
