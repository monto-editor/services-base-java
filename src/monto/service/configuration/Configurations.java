package monto.service.configuration;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import monto.service.message.ParseException;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class Configurations {

    public static List<Configuration> decode(JSONArray array) throws ParseException {
        try {
            List<Configuration> configurations = new ArrayList<>();
            if(array == null)
            	return configurations;
            for (JSONObject obj : (Iterable<JSONObject>) array) {
                configurations.add(new Configuration((String) obj.get("option_id"), obj.get("value")));
            }
            return configurations;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

	public static <T> JSONObject encodeConfiguration(Configuration<T> conf) {
    	JSONObject obj = new JSONObject();
    	obj.put("option_id",conf.getOptionID());
    	obj.put("value", conf.getValue());
    	return obj;
    }
}
