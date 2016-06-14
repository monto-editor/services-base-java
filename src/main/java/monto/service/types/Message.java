package monto.service.types;

import monto.service.product.ProductMessage;
import monto.service.source.SourceMessage;

import java.util.function.Function;

public interface Message {
  <A> A match(Function<SourceMessage, A> f, Function<ProductMessage, A> g);
}
