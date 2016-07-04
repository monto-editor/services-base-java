package monto.service.configuration;

import java.util.function.Consumer;
import java.util.function.Function;

public class BooleanOption extends AbstractOption<Boolean> {

  public BooleanOption(String optionId, String label, Boolean defaultValue) {
    super(optionId, label, "boolean", defaultValue);
  }

  @Override
  public <A> A match(
      Function<BooleanOption, A> bo,
      Function<NumberOption, A> no,
      Function<TextOption, A> to,
      Function<XorOption, A> xo,
      Function<OptionGroup, A> og) {
    return bo.apply(this);
  }

  @Override
  public <A> A match(Function<AbstractOption<Boolean>, A> ao, Function<OptionGroup, A> og) {
    return ao.apply(this);
  }

  @Override
  public void matchVoid(
      Consumer<BooleanOption> bo,
      Consumer<NumberOption> no,
      Consumer<TextOption> to,
      Consumer<XorOption> xo,
      Consumer<OptionGroup> og) {
    bo.accept(this);
  }

  @Override
  public void matchVoid(Consumer<AbstractOption<Boolean>> ao, Consumer<OptionGroup> og) {
    ao.accept(this);
  }
}
