package monto.service.product;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.ParseException;
import monto.service.types.Product;
import monto.service.types.ServiceID;
import monto.service.types.Source;

public class ProductMessages {

    public static ProductMessage decode(String string) throws ParseException {
        return decode((JSONObject) JSONValue.parse(string));
    }

    @SuppressWarnings("unchecked")
    public static ProductMessage decode(JSONObject message) throws ParseException {
        try {
            Long versionId = (Long) message.get("id");
            Source source = new Source((String) message.get("source"));
            ServiceID serviceID = new ServiceID((String) message.get("service_id"));
            Product product = new Product((String) message.get("product"));
            Language language = new Language((String) message.get("language"));
            Object contents = message.get("contents");
            return new ProductMessage(
                    new LongKey(versionId),
                    source,
                    serviceID,
                    product,
                    language,
                    contents);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static JSONObject encode(ProductMessage msg) {
        JSONObject encoding = new JSONObject();
        encoding.put("id", msg.getId().longValue());
        encoding.put("source", msg.getSource().toString());
        encoding.put("service_id", msg.getServiceID().toString());
        encoding.put("product", msg.getProduct().toString());
        encoding.put("language", msg.getLanguage().toString());
        encoding.put("contents", msg.getContents());
        return encoding;
    }

}
