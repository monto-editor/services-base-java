package monto.service.completion;

import monto.service.product.ProductMessage;
import monto.service.types.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;


public class Completions {

    public static List<Completion> decode(ProductMessage message) throws ParseException {
        return decode((JSONArray)message.getContents());
    }

    public static List<Completion> decode(JSONArray array) throws ParseException {
        try {
            List<Completion> completions = new ArrayList<>();
            for (Object obj : array) {
                JSONObject encoding = (JSONObject) obj;
                completions.add(new Completion(
                        (String) encoding.get("description"),
                        (String) encoding.get("replacement"),
                        ((Long) encoding.get("insertionOffset")).intValue(),
                        new URL((String) encoding.get("icon"))));
            }
            return completions;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static JSONArray encode(Stream<Completion> completions) {
        Iterator<Completion> iter = completions.iterator();
        JSONArray array = new JSONArray();
        while (iter.hasNext()) {
            array.add(encode(iter.next()));
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject encode(Completion completion) {
        JSONObject object = new JSONObject();
        object.put("description", completion.getDescription());
        object.put("replacement", completion.getReplacement());
        object.put("insertionOffset", completion.getInsertionOffset());
        object.put("icon", completion.getIcon());
        return object;
    }
}
