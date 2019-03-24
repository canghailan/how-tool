package cc.whohow.tool.conf;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Configuration {
    ObjectNode read(String path);

    void write(String path, ObjectNode data);
}
