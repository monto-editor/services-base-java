package monto.service.discovery;

import monto.service.configuration.Option;
import monto.service.configuration.Options;
import monto.service.types.ParseException;
import monto.service.types.ServiceID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.List;

public class Discoveries {

    public static JSONObject encode(DiscoveryRequest discovery) {
        return new JSONObject();
    }

    public static DiscoveryResponse decode(String message) throws ParseException {
        try {
            return decode((JSONArray) JSONValue.parse(message));
        } catch (Exception e) {
            throw new ParseException(message, e);
        }
    }

    private static DiscoveryResponse decode(JSONArray response) throws ParseException {
        List<ServiceDescription> services = new ArrayList<>();
        for (Object s : response)
            services.add(decodeServiceDescription(s));
        return new DiscoveryResponse(services);
    }

    @SuppressWarnings("rawtypes")
    private static ServiceDescription decodeServiceDescription(Object encoding) throws ParseException {
        try {
            JSONObject enc = (JSONObject) encoding;
            ServiceID serviceID = new ServiceID((String) enc.get("service_id"));
            JSONArray optionsJSON = (JSONArray) enc.get("options");
            optionsJSON = optionsJSON == null ? new JSONArray() : optionsJSON;
            List<Option> options = new ArrayList<>(optionsJSON.size());
            for (Object option : optionsJSON)
                options.add(Options.decode((JSONObject) option));
            String description = (String) enc.get("description");
            String label = (String) enc.get("label");
            return new ServiceDescription(serviceID, options, description, label);
        } catch (Exception e) {
            throw new ParseException(encoding.toString(), e);
        }
    }
}
