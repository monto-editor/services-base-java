package monto.service.identifier;

public class Identifier {
    private String identifier;
    private IdentifierType type;

    public Identifier(String identifier, IdentifierType type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public IdentifierType getType() {
        return type;
    }

    public enum IdentifierType {
        IMPORT, CLASS, INTERFACE, ENUM, METHOD, FIELD, VARIABLE, GENERIC
    }

    @Override
    public String toString() {
        return String.format("Identifier{%s, type:%s}", identifier, type);
    }
}
