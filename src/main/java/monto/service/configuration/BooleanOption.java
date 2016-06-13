package monto.service.configuration;

import java.util.function.Function;

public class BooleanOption extends AbstractOption<Boolean> {

    public BooleanOption(String optionId, String label, Boolean defaultValue) {
        super(optionId, label, "boolean", defaultValue);
    }

    @Override
    public <A> A match(Function<BooleanOption, A> f, Function<NumberOption, A> g, Function<TextOption, A> h,
                       Function<XorOption, A> i, Function<OptionGroup, A> j) {
        return f.apply(this);
    }

    @Override
    public <A> A match(Function<AbstractOption<Boolean>, A> f, Function<OptionGroup, A> g) {
        return f.apply(this);
    }
}
