package monto.service.source;

import java.util.function.Function;
import monto.service.product.ProductMessage;
import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.Message;
import monto.service.types.Source;

public class SourceMessage implements Message {

  private final LongKey id;
  private final Source source;
  private final String contents;
  private final Language language;

  public SourceMessage(LongKey id, Source source, Language language, String contents) {
    this.id = id;
    this.source = source;
    this.language = language;
    this.contents = contents;
  }

  public LongKey getId() {
    return id;
  }

  public Source getSource() {
    return source;
  }

  public String getContents() {
    return contents;
  }

  public Language getLanguage() {
    return language;
  }

  @Override
  public <A> A match(Function<SourceMessage, A> f, Function<ProductMessage, A> g) {
    return f.apply(this);
  }
}
