package monto.service.identifier;


public class Identifier implements Comparable<Identifier> {
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

    @Override
    public String toString() {
        return String.format("Identifier{%s, type:%s}", identifier, type);
    }

    @Override
    public int compareTo(Identifier o) {
        return identifier.compareToIgnoreCase(o.getIdentifier());
    }

    public enum IdentifierType {
        IMPORT, CLASS, INTERFACE, ENUM, METHOD, FIELD, VARIABLE, GENERIC
    }
}
