package monto.service.error;

import monto.service.region.Region;

public class Error extends Region {

    private String level;
    private String category;
    private String description;

    public Error(int offset, int length, String level, String category, String description) {
        super(offset, length);
        this.level = level;
        this.category = category;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public String getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("Error { offset = %d, length = %d, level = \"%s\", category = \"%s\", description = \"%s\" }", super.getStartOffset(), super.getLength(), level, category, description);
    }
}
