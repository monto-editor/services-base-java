package monto.service.message;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ProductMessages {

    public static ProductMessage decode(String string) throws ParseException {
        return decode((JSONObject) JSONValue.parse(string));
    }

    @SuppressWarnings("unchecked")
    public static ProductMessage decode(JSONObject message) throws ParseException {
        try {
            Long versionId = (Long) message.get("version_id");
            Source source = new Source((String) message.get("source"));
            ServiceID serviceID = new ServiceID((String) message.get("service_id"));
            Product product = new Product((String) message.get("product"));
            Language language = new Language((String) message.get("language"));
            Object contents = message.get("contents");
            List<Dependency> invalid = Dependencies.decode((JSONArray) message.getOrDefault("invalid", new JSONArray()));
            List<Dependency> dependencies = Dependencies.decode((JSONArray) message.getOrDefault("dependencies", new JSONArray()));
            return new ProductMessage(
                    new LongKey(versionId),
                    source,
                    serviceID,
                    product,
                    language,
                    contents,
                    invalid,
                    dependencies);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static JSONObject encode(ProductMessage msg) {
        JSONObject encoding = new JSONObject();
        encoding.put("version_id", msg.getVersionId().longValue());
        encoding.put("source", msg.getSource().toString());
        encoding.put("service_id", msg.getServiceID().toString());
        encoding.put("product", msg.getProduct().toString());
        encoding.put("language", msg.getLanguage().toString());
        encoding.put("contents", msg.getContents());
        encoding.put("invalid", Dependencies.encode(msg.getInvalid()));
        encoding.put("dependencies", Dependencies.encode(msg.getDependencies()));
        return encoding;
    }

}
