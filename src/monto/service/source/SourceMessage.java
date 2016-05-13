package monto.service.source;

import monto.service.product.ProductMessage;
import monto.service.types.*;

import java.util.Optional;
import java.util.function.Function;

public class SourceMessage implements Message {

    private final LongKey id;
    private final Source source;
    private final String contents;
    private final Language language;
    private final Selection selection;

    public SourceMessage(LongKey id, Source source, Language language, String contents) {
        this(id, source, language, contents, null);
    }

    public SourceMessage(LongKey id, Source source, Language language, String contents, Selection selection) {
        this.id = id;
        this.source = source;
        this.language = language;
        this.contents = contents;
        this.selection = selection;
    }

    public LongKey getId() {
        return id;
    }

    public Source getSource() {
        return source;
    }

    public String getContents() {
        return contents;
    }

    public Language getLanguage() {
        return language;
    }

    public Optional<Selection> getSelection() {
        return Optional.ofNullable(selection);
    }

    @Override
    public <A> A match(Function<SourceMessage, A> f, Function<ProductMessage, A> g) {
        return f.apply(this);
    }

}
