package cc.whohow.tool.json;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class JsonCellValueFactory implements Callback<TableColumn.CellDataFeatures<JsonNode, JsonNode>, ObservableValue<JsonNode>> {
    private final JsonPointer pointer;

    public JsonCellValueFactory(String pointer) {
        this.pointer = JsonPointer.compile(pointer);
    }

    @Override
    public ObservableValue<JsonNode> call(TableColumn.CellDataFeatures<JsonNode, JsonNode> cellDataFeatures) {
        JsonNode row = cellDataFeatures.getValue();
        if (row == null || row.isNull() || row.isMissingNode()) {
            return null;
        }
        return new ReadOnlyObjectWrapper<>(row.at(pointer));
    }
}
