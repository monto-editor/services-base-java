package monto.service.configuration;

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
    public <A> A match(Function<BooleanOption, A> f, Function<NumberOption, A> g, Function<TextOption, A> h,
                       Function<XorOption, A> i, Function<OptionGroup, A> j) {
        return h.apply(this);
    }

    @Override
    public <A> A match(Function<AbstractOption<String>, A> f, Function<OptionGroup, A> g) {
        return f.apply(this);
    }
}
