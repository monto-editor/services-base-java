package monto.service.configuration;

public class BooleanOption extends Option<Boolean> {

    public BooleanOption(String optionId, String label, Boolean defaultValue) {
        super(optionId, label, "boolean", defaultValue);
    }
}
