package monto.service.configuration;

public abstract class AbstractOption<T> implements Option<T> {

    private String optionId;
    private String label;
    private String type;
    private T defaultValue;

    public AbstractOption(String optionId, String label, String type, T defaultValue) {
        this.optionId = optionId;
        this.label = label;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public String getOptionId() {
        return optionId;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return optionId;
    }
}
