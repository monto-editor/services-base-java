package monto.service.source;

import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.ParseException;
import monto.service.types.Selection;
import monto.service.types.Source;

public class SourceMessages {

    public static SourceMessage decode(final Reader reader) throws ParseException {
        final JSONObject message = (JSONObject) JSONValue.parse(reader);
        return decode(message);
    }

    @SuppressWarnings("unchecked")
    public static SourceMessage decode(JSONObject message) throws ParseException {
        try {
            final LongKey id = new LongKey((Long) message.get("id"));
            final Source source = new Source((String) message.get("source"));
            final Language language = new Language((String) message.get("language"));
            final String contents = (String) message.get("contents");
            if(message.containsKey("selection")) {
            	final JSONObject selection = (JSONObject) message.getOrDefault("selection", new JSONArray());
            	final Long offset = (Long) selection.get("offset");
            	final Long length = (Long) selection.get("length");
            	return new SourceMessage(id, source, language, contents, new Selection(offset.intValue(), length.intValue()));
            } else {
            	return new SourceMessage(id, source, language, contents);
            }
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static JSONObject encode(SourceMessage message) {
        JSONObject version = new JSONObject();
        version.put("id", message.getId().longValue());
        version.put("source", message.getSource().toString());
        version.put("language", message.getLanguage().toString());
        version.put("contents", message.getContent().toString());
        message.getSelection().ifPresent(sel -> version.put("selection", sel));
        return version;
    }

}
