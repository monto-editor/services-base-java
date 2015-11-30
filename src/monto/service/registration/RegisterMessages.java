package monto.service.registration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import monto.service.configuration.Option;
import monto.service.configuration.Options;
import monto.service.message.DeregisterService;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class RegisterMessages {

    public static JSONObject encode(RegisterServiceRequest message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("service_id", message.getServiceID().toString());
        jsonObject.put("label", message.getLabel());
        jsonObject.put("description", message.getDescription());
        jsonObject.put("language", message.getLanguage().toString());
        jsonObject.put("product", message.getProduct().toString());
        List<Option> options = Arrays.asList(message.getOptions());
<<<<<<< 334af460e1fd9e28397f7537fa7cca56c4f96509:src/monto/service/message/RegisterMessages.java
        JSONArray jsonOptions = (JSONArray) options
        		.stream()
        		.map(option -> Options.encode(option))
        		.collect(Collectors.toCollection(JSONArray::new));
=======
        JSONArray jsonOptions = options
			.stream()
			.map(option -> Options.encode(option))
			.collect(Collectors.toCollection(() -> new JSONArray()));
>>>>>>> Use serviceIDs instead of (Product,Language):src/monto/service/registration/RegisterMessages.java
        jsonObject.put("options", jsonOptions);
        JSONArray dependencies = new JSONArray();
        for(Dependency dep : message.getDependencies())
		dependencies.add(Dependencies.encode(dep));
        jsonObject.put("dependencies", dependencies);
        return jsonObject;
    }

    public static JSONObject encode(DeregisterService message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deregister_service_id", message.getDeregisterServiceID().toString());
        return jsonObject;
    }

    public static RegisterServiceResponse decodeResponse(JSONObject message) {
        final String response = (String) message.get("response");
        try {
            int bindOnPort = ((Long) message.getOrDefault("bind_on_port", 0)).intValue();
            return new RegisterServiceResponse(response, bindOnPort);
        } catch (Exception e) {
            return new RegisterServiceResponse(response);
        }
    }
}
