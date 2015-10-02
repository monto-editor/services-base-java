package monto.service.configuration;

import monto.service.message.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Configurations {

    @SuppressWarnings("unchecked")
    public static List<Configuration> decode(JSONArray array) throws ParseException {
        try {
            List<Configuration> configurations = new ArrayList<>();
            for (JSONObject obj : (Iterable<JSONObject>) array) {
                configurations.add(new Configuration((String) obj.get("option_id"), obj.get("value")));
            }
            return configurations;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }
}
