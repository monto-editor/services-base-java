package monto.service.configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class XorOption extends AbstractOption<String> {

  private List<String> values;

  public XorOption(String optionId, String label, String defaultValue, List<String> values) {
    super(optionId, label, "xor", defaultValue);
    this.values = values;
  }

  public List<String> getValues() {
    return values;
  }

  @Override
  public <A> A match(
      Function<BooleanOption, A> bo,
      Function<NumberOption, A> no,
      Function<TextOption, A> to,
      Function<XorOption, A> xo,
      Function<OptionGroup, A> og) {
    return xo.apply(this);
  }

  @Override
  public <A> A match(Function<AbstractOption<String>, A> ao, Function<OptionGroup, A> og) {
    return ao.apply(this);
  }

  @Override
  public void matchVoid(
      Consumer<BooleanOption> bo,
      Consumer<NumberOption> no,
      Consumer<TextOption> to,
      Consumer<XorOption> xo,
      Consumer<OptionGroup> og) {
    xo.accept(this);
  }

  @Override
  public void matchVoid(Consumer<AbstractOption<String>> ao, Consumer<OptionGroup> og) {
    ao.accept(this);
  }
}
