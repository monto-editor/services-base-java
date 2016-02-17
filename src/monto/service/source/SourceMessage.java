package monto.service.source;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import monto.service.product.ProductMessage;
import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.Message;
import monto.service.types.Selection;
import monto.service.types.Source;

public class SourceMessage implements Message {

    private final LongKey id;
    private final Source source;
    private final String content;
    private final Language language;
    private final List<Selection> selections;

    public SourceMessage(LongKey versionId, Source source, Language language, String content, Selection... selections) {
        this(versionId, source, language, content, Arrays.asList(selections));
    }

    public SourceMessage(LongKey id, Source source, Language language, String content, List<Selection> selections) {
        this.id = id;
        this.source = source;
        this.language = language;
        this.content = content;
        this.selections = selections;
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

    public List<Selection> getSelections() {
        return selections;
    }

	@Override
	public <A> A match(Function<SourceMessage, A> f, Function<ProductMessage, A> g) {
		return f.apply(this);
	}

}
