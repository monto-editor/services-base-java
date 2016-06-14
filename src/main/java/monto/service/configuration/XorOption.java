package monto.service.configuration;

import java.util.List;
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
      Function<BooleanOption, A> f,
      Function<NumberOption, A> g,
      Function<TextOption, A> h,
      Function<XorOption, A> i,
      Function<OptionGroup, A> j) {
    return i.apply(this);
  }

  @Override
  public <A> A match(Function<AbstractOption<String>, A> f, Function<OptionGroup, A> g) {
    return f.apply(this);
  }
}
