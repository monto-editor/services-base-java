package monto.service.configuration;

import java.util.function.Function;

public class NumberOption extends AbstractOption<Long> {

  private long from;
  private long to;

  public NumberOption(String optionId, String label, long defaultValue, long from, long to) {
    super(optionId, label, "number", defaultValue);
    this.from = from;
    this.to = to;
  }

  public long getFrom() {
    return from;
  }

  public long getTo() {
    return to;
  }

  @Override
  public <A> A match(
      Function<BooleanOption, A> f,
      Function<NumberOption, A> g,
      Function<TextOption, A> h,
      Function<XorOption, A> i,
      Function<OptionGroup, A> j) {
    return g.apply(this);
  }

  @Override
  public <A> A match(Function<AbstractOption<Long>, A> f, Function<OptionGroup, A> g) {
    return f.apply(this);
  }
}
