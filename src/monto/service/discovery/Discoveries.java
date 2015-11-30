package monto.service.discovery;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import monto.service.configuration.Option;
import monto.service.configuration.Options;
import monto.service.message.ParseException;
import monto.service.message.ServiceID;

@SuppressWarnings("unchecked")
public class Discoveries {

	public static JSONObject encode(DiscoveryRequest discovery) {
		JSONObject encoding = new JSONObject();
		encoding.put("discover_services", encodeFilters(discovery));
		return encoding;
	}

	private static JSONArray encodeFilters(DiscoveryRequest discovery) {
		JSONArray encoding = new JSONArray();
		encoding.addAll(
			discovery
			.getFilters()
			.stream()
			.map(Discoveries::encodeFilter)
			.collect(Collectors.toList()));
		return encoding;
	}
	
	private static JSONObject encodeFilter(Filter filter) {
		JSONObject encoding = new JSONObject();
		filter.<Void>match(
				serviceIDFilter -> { encoding.put("service_id", serviceIDFilter.getServiceID().toString()); return null; },
				productFilter -> { encoding.put("product",productFilter.getProduct().toString()); return null; },
				languageFilter -> { encoding.put("language",languageFilter.getLanguage().toString()); return null; });
		return encoding;
	}
	
	public static DiscoveryResponse decode(String message) throws ParseException {
		try {
			return decode((JSONArray) JSONValue.parse(message));
		} catch (Exception e) {
			throw new ParseException(message,e);
		}
	}
	
	private static DiscoveryResponse decode(JSONArray response) throws ParseException {
		List<ServiceDescription> services = new ArrayList<>();
		for(Object s : response)
			services.add(decodeServiceDescription(s));
		return new DiscoveryResponse(services);
	}
	
	@SuppressWarnings("rawtypes")
	private static ServiceDescription decodeServiceDescription(Object encoding) throws ParseException {
		try {
			JSONObject enc = (JSONObject) encoding;
			ServiceID serviceID = new ServiceID((String) enc.get("service_id"));
			String language = (String) enc.get("language");
			String product = (String) enc.get("product");
			JSONArray optionsJSON = (JSONArray) enc.get("options");
			optionsJSON = optionsJSON == null ? new JSONArray() : optionsJSON;
			List<Option> options = new ArrayList<>(optionsJSON.size());
			for(Object option : optionsJSON)
				options.add(Options.decode((JSONObject) option));
			String description = (String) enc.get("description");
			String label = (String) enc.get("label");
			return new ServiceDescription(serviceID,language,product,options,description,label);
		} catch (Exception e) {
			throw new ParseException(encoding.toString(),e);
		}
	}
}
