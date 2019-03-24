package cc.whohow.tool.conf;

import cc.whohow.tool.engine.ViewModel;

public interface ConfigurationHandler {
    String getType();

    Configuration getConfiguration();

    ViewModel<?> getIndexViewModel(String configuration);
}
