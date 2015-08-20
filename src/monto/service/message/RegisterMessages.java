package monto.service.message;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RegisterMessages {

    @SuppressWarnings("unchecked")
    public static JSONObject encode(RegisterServiceRequest message) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("service_id", message.getServiceID());
        jsonObj.put("label", message.getLabel());
        jsonObj.put("description", message.getDescription());
        jsonObj.put("language", message.getLanguage().toString());
        jsonObj.put("product", message.getProduct().toString());
        JSONArray dependencies = new JSONArray();
        for (String dependency : message.getDependencies()) {
            dependencies.add(dependency);
        }
        jsonObj.put("dependencies", dependencies);
        return jsonObj;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject encode(DeregisterService message) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("deregister_service_id", message.getDeregisterServiceID());
        return jsonObj;
    }

    @SuppressWarnings("unchecked")
    public static RegisterServiceResponse decodeResponse(JSONObject message) {
        final String response = (String) message.get("response");
        final int bindOnPort = ((Long) message.getOrDefault("bind_on_port", 0)).intValue();
        return new RegisterServiceResponse(response, bindOnPort);
    }
}
