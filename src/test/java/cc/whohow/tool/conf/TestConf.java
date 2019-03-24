package cc.whohow.tool.conf;

import cc.whohow.tool.docker.conf.DockerConfiguration;
import org.junit.Test;

public class TestConf {
    @Test
    public void test() {
        DockerConfiguration dockerConfiguration = new DockerConfiguration();
        System.out.println(dockerConfiguration.read("private/dev.docker.hot"));
        dockerConfiguration.write("private/dev1.docker.hot", dockerConfiguration.read("private/dev.docker.hot"));
    }
}
