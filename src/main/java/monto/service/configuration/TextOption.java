package monto.service.configuration;

import java.util.function.Consumer;
import java.util.function.Function;

public class TextOption extends AbstractOption<String> {

  private String regularExpression;

  public TextOption(String optionId, String label, String defaultValue, String regularExpression) {
    super(optionId, label, "text", defaultValue);
    this.regularExpression = regularExpression;
  }

  public String getRegularExpression() {
    return regularExpression;
  }

  @Override
  public <A> A match(
      Function<BooleanOption, A> bo,
      Function<NumberOption, A> no,
      Function<TextOption, A> to,
      Function<XorOption, A> xo,
      Function<OptionGroup, A> og) {
    return to.apply(this);
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
    to.accept(this);
  }

  @Override
  public void matchVoid(Consumer<AbstractOption<String>> ao, Consumer<OptionGroup> og) {
    ao.accept(this);
  }
}
