package monto.service.product;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import monto.service.distributor.Visitor;
import monto.service.filedependencies.Dependencies;
import monto.service.filedependencies.Dependency;
import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.ParseException;
import monto.service.types.Product;
import monto.service.types.ServiceID;
import monto.service.types.Source;

public class ProductMessages {

    public static IProductMessage decode(String string) throws ParseException {
        return decode((JSONObject) JSONValue.parse(string));
    }

    @SuppressWarnings("unchecked")
    public static IProductMessage decode(JSONObject message) throws ParseException {
        try {
		LongKey versionId = new LongKey((Long) message.get("version_id"));
            Source source = new Source((String) message.get("source"));
            ServiceID serviceID = new ServiceID((String) message.get("service_id"));
            Product product = new Product((String) message.get("product"));
            Language language = new Language((String) message.get("language"));
            Object contents = message.get("contents");
            Integer contentsKey = (Integer) message.get("contentsKey");
            List<Dependency> dependencies = Dependencies.decode((JSONArray) message.getOrDefault("dependencies", new JSONArray()));

            ensureContentsFieldsAreValid(contents, contentsKey);

		if(contents != null){
			return new ProductMessageWithContents(
					versionId,
					source,
					serviceID,
					product,
					language,
					contents,
					dependencies);
		} else {
			return new ProductMessageWithKey(versionId, source, serviceID, product, language, contentsKey, dependencies);
		}
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    private static void ensureContentsFieldsAreValid(Object contents, Integer contentsKey) throws ParseException{
	if (contents != null && contentsKey != null){
		throw new ParseException("Message has contents and contentsKey.");
        }
	if (contents == null && contentsKey == null){
		throw new ParseException("Message has neither contents nor contentsKey.");
	}
    }

    @SuppressWarnings("unchecked")
    public static JSONObject encode(IProductMessage msg) throws ParseException{
        JSONObject encoding = new JSONObject();
        encoding.put("version_id", msg.getVersionId().longValue());
        encoding.put("source", msg.getSource().toString());
        encoding.put("service_id", msg.getServiceID().toString());
        encoding.put("product", msg.getProduct());
        encoding.put("language", msg.getLanguage().toString());
        encoding.put("dependencies", Dependencies.encode(msg.getDependencies()));
        return msg.accept(new Visitor<JSONObject,ParseException>() {

			@Override
			public JSONObject visitProductMessageWithContents(ProductMessageWithContents prd) throws ParseException {
				return encode(prd,encoding);
			}

			@Override
			public JSONObject visitProductMessageWithKey(ProductMessageWithKey kPrd) throws ParseException {
				return encode(kPrd,encoding);
			}
		});
    }

    @SuppressWarnings("unchecked")
    static JSONObject encode(ProductMessageWithKey prdWithKey,JSONObject encoding) throws ParseException{
	encoding.put("contents_key", prdWithKey.getContentsKey());
	return encoding;
    }

    @SuppressWarnings("unchecked")
     static JSONObject encode(ProductMessageWithContents prdWithContents, JSONObject encoding) throws ParseException{
	encoding.put("contents", prdWithContents.getContents());
	return encoding;
    }

    public static ProductMessageWithKey constructMsgWithKey(ProductMessage msg, Integer key){
	LongKey versionId = msg.getVersionId();
        Source source = msg.getSource();
        ServiceID serviceID = msg.getServiceID();
        Product product = msg.getProduct();
        Language language = msg.getLanguage();
        List<Dependency> dependencies = msg.getDependencies();

        if(dependencies == null){
		return new ProductMessageWithKey(versionId, source, serviceID, product, language, key);
        }
        return new ProductMessageWithKey(versionId, source, serviceID, product, language, key, dependencies);
    }

    public static ProductMessageWithContents constructMsgWithContents(ProductMessageWithKey msg, Object contents){
	LongKey versionId = msg.getVersionId();
        Source source = msg.getSource();
        ServiceID serviceID = msg.getServiceID();
        Product product = msg.getProduct();
        Language language = msg.getLanguage();
        List<Dependency> dependencies = msg.getDependencies();

        if(dependencies == null){
		return new ProductMessageWithContents(versionId, source, serviceID, product, language, contents);
        }
        return new ProductMessageWithContents(versionId, source, serviceID, product, language, contents, dependencies);
    }

}
