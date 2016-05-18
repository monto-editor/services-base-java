package monto.service.gson;

import com.google.gson.*;
import monto.service.ast.AST;
import monto.service.ast.NonTerminal;
import monto.service.configuration.Option;
import monto.service.configuration.OptionGroup;
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

                .registerTypeAdapter(Option.class, new OptionDeserializer())
                .registerTypeAdapter(OptionGroup.class, new OptionGroupSerializer())

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

                .registerTypeAdapter(AST.class, new ASTDeserializer())
                // when registering the ASTDeserializer, serializing the children of a NonTerminal doesn't work any more
                // no idea why, but that's why ASTNonTerminalSerializer is necessary
                .registerTypeAdapter(NonTerminal.class, new ASTNonTerminalSerializer())

                .create();
    }

    private GsonMonto() {
    }

    public static Gson getGson() {
        return gson;
    }

    public static JsonElement toJsonTree(Object src) {
        return gson.toJsonTree(src);
    }

    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(ProductMessage productMessage, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(productMessage.getContents(), classOfT);
    }

    /**
     * Use it like this:<br>
     * <code>
     * List&lt;Completion&gt; completions = GsonMonto.fromJsonArray(message, Completion[].class);
     * </code>
     *
     * @see <a href="http://stackoverflow.com/a/28805158/2634932">http://stackoverflow.com/a/28805158/2634932</a>
     */
    public static <T> List<T> fromJsonArray(ProductMessage productMessage, Class<T[]> aClass) {
        T[] jsonToObject = gson.fromJson(productMessage.getContents(), aClass);
        return Arrays.asList(jsonToObject);
    }

    /**
     * Use it like this:<br>
     * <code>
     * List&lt;Completion&gt; completions = GsonMonto.fromJsonArray(completionJsonString, Completion[].class);
     * </code>
     *
     * @see <a href="http://stackoverflow.com/a/28805158/2634932">http://stackoverflow.com/a/28805158/2634932</a>
     */
    public static <T> List<T> fromJsonArray(String json, Class<T[]> aClass) {
        T[] jsonToObject = gson.fromJson(json, aClass);
        return Arrays.asList(jsonToObject);
    }

}
