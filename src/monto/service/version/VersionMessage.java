package monto.service.version;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import monto.service.filedependencies.Dependency;
import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.Message;
import monto.service.types.Selection;
import monto.service.types.Source;

public class VersionMessage implements Message {

    private final LongKey versionId;
    private final Source source;
    private final String content;
    private final Language language;
    private final List<Selection> selections;
    private final List<Dependency> invalid;

    public VersionMessage(LongKey versionId, Source source, Language language, String content, Selection... selections) {
        this(versionId, source, language, content, Arrays.asList(selections));
    }

    public VersionMessage(LongKey versionId, Source source, Language language, String content, List<Selection> selections) {
        this(versionId, source, language, content, selections, new ArrayList<>());
    }

    public VersionMessage(LongKey id, Source source, Language language, String content, List<Selection> selections, List<Dependency> invalid) {
        this.versionId = id;
        this.source = source;
        this.language = language;
        this.content = content;
        this.selections = selections;
        this.invalid = invalid;
    }

    public LongKey getVersionId() {
        return versionId;
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

    public List<Dependency> getInvalid() {
        return invalid;
    }

}