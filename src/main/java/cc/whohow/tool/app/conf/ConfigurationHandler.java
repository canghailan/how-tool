package cc.whohow.tool.app.conf;

import cc.whohow.tool.app.engine.ViewModel;

public interface ConfigurationHandler {
    String getType();

    Configuration getConfiguration();

    ViewModel<?> getIndexViewModel(String configuration);
}
