package monto.service.message;

import monto.service.configuration.Configuration;
import monto.service.configuration.Configurations;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ConfigurationMessages {

    public static ConfigurationMessage decode(JSONObject message) throws ParseException {
        try {
            final ServiceID serviceID = new ServiceID((String) message.get("service_id"));
			final List<Configuration> configurations = Configurations.decode((JSONArray) message.getOrDefault("configurations", new JSONArray()));
            return new ConfigurationMessage(serviceID, configurations);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

	public static JSONObject encode(ConfigurationMessage conf) {
    	JSONObject obj = new JSONObject();
	obj.put("service_id", conf.getServiceID().toString());
    	JSONArray arr = new JSONArray();
    	for(Configuration c : conf.getConfigurations())
    		arr.add(Configurations.encodeConfiguration(c));
    	obj.put("configurations", arr);
    	return obj;
    }
}
