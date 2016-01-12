package monto.service.registration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import monto.service.configuration.Option;
import monto.service.configuration.Options;
import monto.service.types.Product;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class RegisterMessages {

    public static JSONObject encode(RegisterServiceRequest message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("service_id", message.getServiceID().toString());
        jsonObject.put("label", message.getLabel());
        jsonObject.put("description", message.getDescription());
        jsonObject.put("language", message.getLanguage().toString());
        JSONArray jsonProducts = new JSONArray();
        for(Product product : message.getProducts())
		jsonProducts.add(product.toString());
        jsonObject.put("products", jsonProducts);
        JSONArray jsonOptions = new JSONArray();
        for(Option option : message.getOptions())
          jsonOptions.add(Options.encode(option));
        jsonObject.put("options", jsonOptions);
        JSONArray jsonDependencies = new JSONArray();
        for(Dependency dep : message.getDependencies())
		jsonDependencies.add(Dependencies.encode(dep));
        jsonObject.put("dependencies", jsonDependencies);
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
