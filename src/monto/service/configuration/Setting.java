package monto.service.configuration;

public class Setting<T> {

    private String optionID;
    private T value;

    public Setting(String optionID, T value) {
        this.optionID = optionID;
        this.value = value;
    }

    public String getOptionID() {
        return optionID;
    }

    public T getValue() {
        return value;
    }
}
