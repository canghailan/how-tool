package cc.whohow.tool.docker.conf;

import cc.whohow.tool.app.conf.Configuration;
import cc.whohow.tool.app.conf.ConfigurationHandler;
import cc.whohow.tool.app.engine.ViewModel;
import cc.whohow.tool.docker.view.DockerContainersView;
import cc.whohow.tool.docker.view.DockerViewModel;

public class DockerConfigurationHandler implements ConfigurationHandler {
    private DockerConfiguration configuration = new DockerConfiguration();

    @Override
    public String getType() {
        return "docker";
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public ViewModel<?> getIndexViewModel(String configuration) {
        DockerViewModel vm = new DockerViewModel();
        vm.setValue(getConfiguration().read(configuration));
        vm.setComponent(new DockerContainersView());
        return vm;
    }

    @Override
    public String toString() {
        return getType();
    }
}
