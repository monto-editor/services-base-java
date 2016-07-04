package monto.service.configuration;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Option<T> {
  <A> A match(
      Function<BooleanOption, A> bo,
      Function<NumberOption, A> no,
      Function<TextOption, A> to,
      Function<XorOption, A> xo,
      Function<OptionGroup, A> og);

  <A> A match(Function<AbstractOption<T>, A> ao, Function<OptionGroup, A> og);

  void matchVoid(
      Consumer<BooleanOption> bo,
      Consumer<NumberOption> no,
      Consumer<TextOption> to,
      Consumer<XorOption> xo,
      Consumer<OptionGroup> og);

  void matchVoid(Consumer<AbstractOption<T>> ao, Consumer<OptionGroup> og);
}
