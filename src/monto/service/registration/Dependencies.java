package monto.service.registration;

import org.json.simple.JSONObject;

public class Dependencies {

	@SuppressWarnings("unchecked")
	public static JSONObject encode(Dependency dep) {
		JSONObject encoding = new JSONObject();
		dep.<Void>match(productDep -> {
			encoding.put("service_id", productDep.getServiceID().toString());
			encoding.put("language", productDep.getLanguage().toString());
			encoding.put("product", productDep.getProduct().toString());
			return null;
		},
		sourceDep -> {
			encoding.put("language", sourceDep.getSourceLanguage().toString());
			return null;
		});
		return encoding;
	}
}
