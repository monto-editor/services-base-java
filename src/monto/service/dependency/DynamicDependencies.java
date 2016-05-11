package monto.service.dependency;

import org.json.simple.JSONObject;

public class DynamicDependencies {

    public static JSONObject encode(DynamicDependency dyndep) {
        JSONObject obj = new JSONObject();
        obj.put("service_id", dyndep.getServiceID().toString());
        obj.put("source", dyndep.getSource().toString());
        obj.put("product", dyndep.getProduct().toString());
        obj.put("language", dyndep.getLanguage().toString());
        return obj;
    }
}
