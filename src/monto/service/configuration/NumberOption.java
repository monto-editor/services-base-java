package monto.service.configuration;

import org.json.simple.JSONObject;

public class NumberOption extends Option<Double> {

    private double from;
    private double to;

    public NumberOption(String optionId, String label, Double defaultValue, double from, double to) {
        super(optionId, label, "number", defaultValue);
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject encode() {
        JSONObject jsonObject = super.encode();
        jsonObject.put("from", getFrom());
        jsonObject.put("to", getTo());
        return jsonObject;
    }
}
