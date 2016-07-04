package monto.service.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("rawtypes")
public class OptionGroup implements Option<Void> {

  private String label;
  private List<Option> members;

  public OptionGroup(String label, List<Option> members) {
    this.label = label;
    this.members = members;
  }

  public OptionGroup(String label, Option... members) {
    this(label, Arrays.asList(members));
  }

  public String getLabel() {
    return label;
  }

  public List<Option> getMembers() {
    return members;
  }

  @Override
  public String toString() {
    return "OptionGroup " + label;
  }

  @Override
  public <A> A match(
      Function<BooleanOption, A> bo,
      Function<NumberOption, A> no,
      Function<TextOption, A> to,
      Function<XorOption, A> xo,
      Function<OptionGroup, A> og) {
    return og.apply(this);
  }

  @Override
  public <A> A match(Function<AbstractOption<Void>, A> ao, Function<OptionGroup, A> og) {
    return og.apply(this);
  }

  @Override
  public void matchVoid(
      Consumer<BooleanOption> bo,
      Consumer<NumberOption> no,
      Consumer<TextOption> to,
      Consumer<XorOption> xo,
      Consumer<OptionGroup> og) {
    og.accept(this);
  }

  @Override
  public void matchVoid(Consumer<AbstractOption<Void>> ao, Consumer<OptionGroup> og) {
    og.accept(this);
  }
}
