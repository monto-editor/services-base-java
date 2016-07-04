package monto.service.configuration;

import java.util.function.Consumer;
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
      Function<BooleanOption, A> bo,
      Function<NumberOption, A> no,
      Function<TextOption, A> to,
      Function<XorOption, A> xo,
      Function<OptionGroup, A> og) {
    return no.apply(this);
  }

  @Override
  public <A> A match(Function<AbstractOption<Long>, A> ao, Function<OptionGroup, A> og) {
    return ao.apply(this);
  }

  @Override
  public void matchVoid(
      Consumer<BooleanOption> bo,
      Consumer<NumberOption> no,
      Consumer<TextOption> to,
      Consumer<XorOption> xo,
      Consumer<OptionGroup> og) {
    no.accept(this);
  }

  @Override
  public void matchVoid(Consumer<AbstractOption<Long>> ao, Consumer<OptionGroup> og) {
    ao.accept(this);
  }
}
