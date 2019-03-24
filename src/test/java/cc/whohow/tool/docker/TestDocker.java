package cc.whohow.tool.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.netty.NettyDockerCmdExecFactory;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
public class TestDocker {
    private static DockerClientConfig config;
    private static DockerClient docker;

    @BeforeClass
    public static void init() {
        config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://")
                .withDockerTlsVerify(true)
                .withDockerCertPath("private")
                .build();
        docker = DockerClientBuilder.getInstance(config)
                .withDockerCmdExecFactory(new NettyDockerCmdExecFactory())
                .build();
    }

    @AfterClass
    @SneakyThrows
    public static void close() {
        docker.close();
    }

    @Test
    @SneakyThrows
    public void test() {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.submit(() -> {
//            String id = docker.execCreateCmd("")
//                    .withAttachStdin(true)
//                    .withAttachStderr(true)
//                    .withAttachStdout(true)
//                    .withCmd("sh")
//                    .withTty(true)
//                    .exec().getId();
//            docker.execStartCmd(id)
//                    .withStdIn(System.in)
//                .withTty(true)
//                .withDetach(false)
//                    .exec(new ExecStartResultCallback(System.out, System.err))
//                    .awaitCompletion();
//        });
    }
}
