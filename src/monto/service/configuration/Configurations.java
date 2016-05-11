package monto.service.configuration;

import monto.service.types.ParseException;
import monto.service.types.ServiceId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings({"unchecked", "rawtypes"})
public class Configurations {

    public static List<Setting> decodeSettings(JSONArray array) throws ParseException {
        try {
            List<Setting> settings = new ArrayList<>();
            if (array == null)
                return settings;
            for (JSONObject obj : (Iterable<JSONObject>) array) {
                settings.add(new Setting((String) obj.get("option_id"), obj.get("value")));
            }
            return settings;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    public static <T> JSONObject encodeSetting(Setting<T> setting) {
        JSONObject obj = new JSONObject();
        obj.put("option_id", setting.getOptionID());
        obj.put("value", setting.getValue());
        return obj;
    }


    public static Configuration decodeConfiguration(JSONObject message) throws ParseException {
        try {
            final ServiceId serviceId = new ServiceId((String) message.get("service_id"));
            final List<Setting> settings = Configurations.decodeSettings((JSONArray) message.getOrDefault("settings", new JSONArray()));
            return new Configuration(serviceId, settings);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    public static JSONObject encodeConfiguration(Configuration conf) {
        JSONObject obj = new JSONObject();
        obj.put("service_id", conf.getServiceId().toString());
        JSONArray arr = new JSONArray();
        for (Setting c : conf.getConfigurations())
            arr.add(Configurations.encodeSetting(c));
        obj.put("settings", arr);
        return obj;
    }
}
