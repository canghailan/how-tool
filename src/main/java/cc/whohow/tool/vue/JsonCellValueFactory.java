package cc.whohow.tool.vue;

import cc.whohow.tool.json.Json;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class JsonCellValueFactory implements Callback<TableColumn.CellDataFeatures<JsonNode, JsonNode>, ObservableValue<JsonNode>> {
    private final String expression;

    public JsonCellValueFactory(String expression) {
        this.expression = expression;
    }

    @Override
    public ObservableValue<JsonNode> call(TableColumn.CellDataFeatures<JsonNode, JsonNode> cellDataFeatures) {
        JsonNode row = cellDataFeatures.getValue();
        if (row == null || row.isNull() || row.isMissingNode()) {
            return null;
        }
        return new ReadOnlyObjectWrapper<>(Json.evaluate(row, expression));
    }
}
