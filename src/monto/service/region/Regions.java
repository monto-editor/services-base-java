package monto.service.region;

import monto.service.types.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.Reader;

public class Regions {

    @SuppressWarnings("unchecked")
    public static JSONObject encode(IRegion range) {
        JSONObject encoding = new JSONObject();
        encoding.put("offset", range.getStartOffset());
        encoding.put("length", range.getLength());
        return encoding;
    }

    public static Region decode(Reader rangeEncoding) throws ParseException {
        try {
            return decode((JSONObject) JSONValue.parse(rangeEncoding));
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    public static Region decode(JSONObject encoding) throws ParseException {
        try {
            int offset = intValue(encoding.get("offset"));
            int length = intValue(encoding.get("length"));
            return new Region(offset, length);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    private static int intValue(Object obj) {
        return obj instanceof Long ? ((Long) obj).intValue() : ((Integer) obj).intValue();
    }
}
