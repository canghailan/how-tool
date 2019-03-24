package cc.whohow.tool.app.engine;

import cc.whohow.tool.json.Json;
import com.fasterxml.jackson.databind.JsonNode;
import org.w3c.dom.Attr;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BindingAttr implements Map.Entry<String, String> {
    private static final Pattern ATTR = Pattern.compile("(?<prefix>.*[:@])(?<key>[^:@].*)");
    private Attr attr;
    private ViewModel<?> vm;
    private String prefix;
    private String bindingKey;
    private CompletableFuture<JsonNode> bindingValue;

    public BindingAttr(Attr attr, ViewModel<?> vm) {
        this.attr = attr;
        this.vm = vm;
        Matcher matcher = ATTR.matcher(attr.getName());
        if (matcher.matches()) {
            prefix = matcher.group("prefix");
            bindingKey = matcher.group("key");
        } else {
            prefix = null;
            bindingKey = attr.getName();
        }
    }

    @Override
    public String getKey() {
        return attr.getName();
    }

    @Override
    public String getValue() {
        return attr.getValue();
    }

    public String getStringValue() {
        return getStringValue("");
    }

    public String getStringValue(String defaultValue) {
        String value = attr.getValue();
        if (value.isEmpty()) {
            return defaultValue;
        }
        if (isBinding()) {
            return vm.get(value).join().asText(defaultValue);
        }
        return value;
    }

    public boolean getBooleanValue() {
        return getBooleanValue(false);
    }

    public boolean getBooleanValue(boolean defaultValue) {
        String value = attr.getValue();
        if (value.isEmpty()) {
            return defaultValue;
        }
        if (isBinding()) {
            return vm.get(value).join().asBoolean(defaultValue);
        }
        return Boolean.parseBoolean(value);
    }

    public int getIntValue() {
        return getIntValue(0);
    }

    public int getIntValue(int defaultValue) {
        String value = attr.getValue();
        if (value.isEmpty()) {
            return defaultValue;
        }
        if (isBinding()) {
            return vm.get(value).join().asInt(defaultValue);
        }
        return Integer.parseInt(value);
    }

    public long getLongValue() {
        return getLongValue(0);
    }

    public long getLongValue(long defaultValue) {
        String value = attr.getValue();
        if (value.isEmpty()) {
            return defaultValue;
        }
        if (isBinding()) {
            return vm.get(value).join().asLong(defaultValue);
        }
        return Long.parseLong(value);
    }

    public double getDoubleValue() {
        return getDoubleValue(0);
    }

    public double getDoubleValue(double defaultValue) {
        String value = attr.getValue();
        if (value.isEmpty()) {
            return defaultValue;
        }
        if (isBinding()) {
            return vm.get(value).join().asDouble(defaultValue);
        }
        return Double.parseDouble(value);
    }

    public BigDecimal getNumberValue() {
        return getNumberValue(BigDecimal.ZERO);
    }

    public BigDecimal getNumberValue(BigDecimal defaultValue) {
        String value = attr.getValue();
        if (value.isEmpty()) {
            return defaultValue;
        }
        if (isBinding()) {
            JsonNode bindingValue = vm.get(value).join();
            if (bindingValue.isNumber()) {
                return bindingValue.decimalValue();
            }
            return defaultValue;
        }
        return new BigDecimal(value);
    }

    @Override
    public String setValue(String value) {
        throw new UnsupportedOperationException();
    }

    public boolean isBinding() {
        return prefix != null;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getBindingKey() {
        return bindingKey;
    }

    public CompletableFuture<JsonNode> getBindingValue() {
        if (bindingValue == null) {
            if (isBinding()) {
                bindingValue = vm.get(attr.getValue());
            } else {
                bindingValue = CompletableFuture.completedFuture(Json.string(attr.getValue()));
            }
        }
        return bindingValue;
    }

    @Override
    public String toString() {
        return attr.toString();
    }
}
