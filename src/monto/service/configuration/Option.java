package monto.service.configuration;

import java.util.function.Function;

public interface Option<T> {
    <A> A match(
            Function<BooleanOption, A> f,
            Function<NumberOption, A> g,
            Function<TextOption, A> h,
            Function<XorOption, A> i,
            Function<OptionGroup, A> j
    );

    <A> A match(
            Function<AbstractOption<T>, A> f,
            Function<OptionGroup, A> g);
}
