package monto.service.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import monto.service.outline.Outline;
import monto.service.product.ProductMessage;
import monto.service.types.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class GsonMonto {
    private static Gson gson;

    static {
        ToStringSerializer toStringSerializer = new ToStringSerializer();
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

                .registerTypeAdapter(Message.class, new MessageDeserializer())

                .registerTypeAdapter(ServiceId.class, toStringSerializer)
                .registerTypeAdapter(ServiceId.class, new ServiceIdDeserializer())

                .registerTypeAdapter(Product.class, toStringSerializer)
                .registerTypeAdapter(Product.class, new ProductDeserializer())

                .registerTypeAdapter(Language.class, toStringSerializer)
                .registerTypeAdapter(Language.class, new LanguageDeserializer())

                .registerTypeAdapter(Source.class, toStringSerializer)
                .registerTypeAdapter(Source.class, new SourceDeserializer())

                .registerTypeAdapter(LongKey.class, new LongKeySerializer())
                .registerTypeAdapter(LongKey.class, new LongKeyDeserializer())

                .registerTypeAdapter(Outline.class, new OutlineSerializer())
                // OutlineDeserializer not yet implemented

                .registerTypeAdapter(Optional.class, new OptionalSerializer<>())
                // OptionalDeserializer is tricky, because OptionalSerializer returns null for Optional.empty()s.
                // If a Json property is null, no Deserializer is called by Gson. The Optional gets set to null.
                // If you want to have a Optional<T> field property t, make the field of type T and in the getter
                // return Optional.ofNullable(t)

                .create();
    }

    private GsonMonto() {
    }

    public static Gson getGson() {
        return gson;
    }

    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(ProductMessage productMessage, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson((String) productMessage.getContents(), classOfT);
    }

    /**
     * Use it like this:<br>
     * <code>
     * List&lt;Completion&gt; completions = GsonMonto.fromJsonArray(json, Completion[].class);
     * </code>
     *
     * @see <a href="http://stackoverflow.com/a/28805158/2634932">http://stackoverflow.com/a/28805158/2634932</a>
     */
    public static <T> List<T> fromJsonArray(String json, Class<T[]> aClass) {
        T[] jsonToObject = new Gson().fromJson(json, aClass);
        return Arrays.asList(jsonToObject);
    }

    public static <T> List<T> fromJsonArray(ProductMessage productMessage, Class<T[]> aClass) {
        T[] jsonToObject = new Gson().fromJson((String) productMessage.getContents(), aClass);
        return Arrays.asList(jsonToObject);
    }
}
