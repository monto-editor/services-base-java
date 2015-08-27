package monto.service.message;

import monto.service.configuration.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class RegisterMessages {

    @SuppressWarnings("unchecked")
    public static JSONObject encode(RegisterServiceRequest message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("service_id", message.getServiceID());
        jsonObject.put("label", message.getLabel());
        jsonObject.put("description", message.getDescription());
        jsonObject.put("language", message.getLanguage().toString());
        jsonObject.put("product", message.getProduct().toString());
        Configuration[] configuration = message.getConfiguration();
        if (configuration != null && !(configuration.length == 0)) {
            JSONArray configuratons = new JSONArray();
            for (Configuration conf : configuration) {
                configuratons.add(conf.encode());
            }
            jsonObject.put("configuration", configuration);
        }
        JSONArray dependencies = new JSONArray();
        for (String dependency : message.getDependencies()) {
            dependencies.add(dependency);
        }
        jsonObject.put("dependencies", dependencies);
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject encode(DeregisterService message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deregister_service_id", message.getDeregisterServiceID());
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public static RegisterServiceResponse decodeResponse(JSONObject message) {
        final String response = (String) message.get("response");
        final int bindOnPort = ((Long) message.getOrDefault("bind_on_port", 0)).intValue();
        return new RegisterServiceResponse(response, bindOnPort);
    }
}
