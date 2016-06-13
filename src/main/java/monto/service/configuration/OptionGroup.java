package monto.service.configuration;

import java.util.Arrays;
import java.util.List;
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
    public <A> A match(Function<BooleanOption, A> f, Function<NumberOption, A> g, Function<TextOption, A> h,
                       Function<XorOption, A> i, Function<OptionGroup, A> j) {
        return j.apply(this);
    }

    @Override
    public <A> A match(Function<AbstractOption<Void>, A> f, Function<OptionGroup, A> g) {
        return g.apply(this);
    }

    @Override
    public String toString() {
        return "OptionGroup " + label;
    }
}
