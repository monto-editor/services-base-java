package monto.service.source;

import monto.service.product.ProductMessage;
import monto.service.types.*;

import java.util.Optional;
import java.util.function.Function;

public class SourceMessage implements Message {

    private final LongKey id;
    private final Source source;
    private final String content;
    private final Language language;
    private final Optional<Selection> selection;

    public SourceMessage(LongKey id, Source source, Language language, String content) {
        this(id, source, language, content, Optional.empty());
    }

    public SourceMessage(LongKey id, Source source, Language language, String content, Selection selection) {
        this(id, source, language, content, Optional.of(selection));
    }

    public SourceMessage(LongKey id, Source source, Language language, String content, Optional<Selection> selection) {
        this.id = id;
        this.source = source;
        this.language = language;
        this.content = content;
        this.selection = selection;
    }

    public LongKey getId() {
        return id;
    }

    public Source getSource() {
        return source;
    }

    public String getContent() {
        return content;
    }

    public Language getLanguage() {
        return language;
    }

    public Optional<Selection> getSelection() {
        return selection;
    }

    @Override
    public <A> A match(Function<SourceMessage, A> f, Function<ProductMessage, A> g) {
        return f.apply(this);
    }

}
