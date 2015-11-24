package monto.service.message;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import monto.service.configuration.Option;
import monto.service.configuration.Options;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class RegisterMessages {

    public static JSONObject encode(RegisterServiceRequest message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("service_id", message.getServiceID());
        jsonObject.put("label", message.getLabel());
        jsonObject.put("description", message.getDescription());
        jsonObject.put("language", message.getLanguage().toString());
        jsonObject.put("product", message.getProduct().toString());
        List<Option> options = Arrays.asList(message.getOptions());
        JSONArray jsonOptions = options
        		.stream()
        		.map(option -> Options.encode(option))
        		.collect(Collectors.toCollection(() -> new JSONArray()));
        jsonObject.put("options", jsonOptions);
        JSONArray dependencies = new JSONArray();
        Collections.addAll(dependencies, message.getDependencies());
        jsonObject.put("dependencies", dependencies);
        return jsonObject;
    }

    public static JSONObject encode(DeregisterService message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deregister_service_id", message.getDeregisterServiceID());
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
