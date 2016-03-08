package monto.service.dependency;

import monto.service.product.ProductMessage;
import monto.service.types.ParseException;
import monto.service.types.Source;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileDependencies {

    public static List<FileDependency> decode(ProductMessage message) throws ParseException {
        return decode((JSONArray)message.getContents());
    }

    public static List<FileDependency> decode(JSONArray array) throws ParseException {
        try {
            List<FileDependency> dependencies = new ArrayList<>();
            for (Object obj : array) {
                Set<Source> fileDependencies = new HashSet<>();
                JSONObject encoding = (JSONObject) obj;
                for (Object fileDep : (JSONArray) encoding.get("dependencies")) {
                    fileDependencies.add(new Source((String) fileDep));
                }
                dependencies.add(new FileDependency(
                        new Source((String) encoding.get("file_name")),
                        fileDependencies
                        ));
            }
            return dependencies;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static JSONArray encode(Set<FileDependency> fileDependencies) {
        JSONArray array = new JSONArray();
        for(FileDependency c: fileDependencies)
            array.add(encode(c));
        return array;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject encode(FileDependency fileDependency) {
        JSONObject object = new JSONObject();
        object.put("file_name", fileDependency.getFileName().toString());
        JSONArray deps = new JSONArray();
        for (Source dep : fileDependency.getDependencies()) {
            deps.add(dep.toString());
        }
        object.put("dependencies", deps);
        return object;
    }
}
