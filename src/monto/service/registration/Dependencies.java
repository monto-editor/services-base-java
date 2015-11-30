package monto.service.registration;

import org.json.simple.JSONObject;

public class Dependencies {

	@SuppressWarnings("unchecked")
	public static JSONObject encode(Dependency dep) {
		JSONObject encoding = new JSONObject();
		dep.<Void>match(serviceDep -> {
			encoding.put("service_id", serviceDep.getServiceID().toString());
			return null;
		},
		sourceDep -> {
			encoding.put("source_language", sourceDep.getSourceLanguage().toString());
			return null;
		});
		return encoding;
	}
}
