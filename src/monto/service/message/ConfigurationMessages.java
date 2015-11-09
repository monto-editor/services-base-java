package monto.service.message;

import monto.service.configuration.Configuration;
import monto.service.configuration.Configurations;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class ConfigurationMessages {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static ConfigurationMessage decode(JSONObject message) throws ParseException {
        try {
            final String serviceID = (String) message.get("service_id");
			final List<Configuration> configurations = Configurations.decode((JSONArray) message.getOrDefault("configurations", new JSONArray()));
            return new ConfigurationMessage(serviceID, configurations);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }
}
