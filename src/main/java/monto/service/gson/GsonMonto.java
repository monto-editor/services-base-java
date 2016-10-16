package monto.service.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

import java.util.Arrays;
import java.util.List;
import monto.service.ast.AST;
import monto.service.ast.NonTerminal;
import monto.service.configuration.Option;
import monto.service.discovery.DiscoveryResponse;
import monto.service.product.ProductMessage;
import monto.service.types.Command;
import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.Message;
import monto.service.types.Product;
import monto.service.types.ServiceId;
import monto.service.types.Source;

public final class GsonMonto {
  private static Gson gson;

  static {
    ToStringSerializer toStringSerializer = new ToStringSerializer();
    gson =
        new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Message.class, new MessageDeserializer())
            .registerTypeAdapter(DiscoveryResponse.class, new DiscoveryResponseDeserializer())
            .registerTypeAdapter(ServiceId.class, toStringSerializer)
            .registerTypeAdapter(ServiceId.class, new ServiceIdDeserializer())
            .registerTypeAdapter(Product.class, toStringSerializer)
            .registerTypeAdapter(Product.class, new ProductDeserializer())
            .registerTypeAdapter(Language.class, toStringSerializer)
            .registerTypeAdapter(Language.class, new LanguageDeserializer())
            .registerTypeAdapter(Command.class, toStringSerializer)
            .registerTypeAdapter(Command.class, new CommandDeserializer())
            .registerTypeAdapter(LongKey.class, new LongKeySerializer())
            .registerTypeAdapter(LongKey.class, new LongKeyDeserializer())
            .registerTypeAdapter(AST.class, new ASTDeserializer())
            // when registering the ASTDeserializer, serializing the children of a NonTerminal doesn't work any more
            // no idea why, but that's why ASTNonTerminalSerializer is necessary
            .registerTypeAdapter(NonTerminal.class, new ASTNonTerminalSerializer())
            .registerTypeAdapter(Option.class, new OptionDeserializer())
            .registerTypeAdapter(Option.class, new OptionSerializer())
            // see comment of ASTDeserializer, same applies for Option
            .registerTypeAdapter(byte[].class, new ByteArrayDeSerializer())
            .create();
  }

  private GsonMonto() {}

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

  public static <T> T fromJson(ProductMessage productMessage, Class<T> classOfT)
      throws JsonSyntaxException {
    return gson.fromJson(productMessage.getContents(), classOfT);
  }

  public static <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
    return gson.fromJson(json, classOfT);
  }

  /**
   * Use it like this:<br>
   * <code>
   * List&lt;Completion&gt; completions = GsonMonto.fromJsonArray(message, Completion[].class);
   * </code>
   * @see <a href="http://stackoverflow.com/a/28805158/2634932">http://stackoverflow.com/a/28805158/2634932</a>
   */
  public static <T> List<T> fromJsonArray(ProductMessage productMessage, Class<T[]> aClass) {
    T[] jsonToObject = gson.fromJson(productMessage.getContents(), aClass);
    return Arrays.asList(jsonToObject);
  }

  /**
   * Use it like this:<br> <code> List&lt;Completion&gt; completions =
   * GsonMonto.fromJsonArray(completionJsonString, Completion[].class); </code>
   * @see <a href="http://stackoverflow.com/a/28805158/2634932">http://stackoverflow.com/a/28805158/2634932</a>
   */
  public static <T> List<T> fromJsonArray(String json, Class<T[]> aClass) {
    T[] jsonToObject = gson.fromJson(json, aClass);
    return Arrays.asList(jsonToObject);
  }
}
