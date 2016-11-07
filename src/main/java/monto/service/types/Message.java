package monto.service.types;

import java.util.function.Function;
import monto.service.product.ProductMessage;
import monto.service.source.SourceMessage;

public interface Message {
  <A> A match(Function<SourceMessage, A> f, Function<ProductMessage, A> g);
}
