package monto.service.completion;

import java.net.URL;

public class Completion {

    private String description;
    private String replacement;
    private URL icon;
    private int insertionOffset;

    public Completion(String description, String replacement, URL icon) {
        this(description, replacement, 0, icon);
    }

    public Completion(String description, String replacement, int insertionOffset, URL icon) {
        this.description = description;
        this.replacement = replacement;
        this.insertionOffset = insertionOffset;
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public String getReplacement() {
        return replacement;
    }

    public URL getIcon() {
        return icon;
    }

    public int getInsertionOffset() {
        return insertionOffset;
    }

    @Override
    public String toString() {
        return String.format("%s", description);
    }
}
