package monto.service.dependency;

import monto.service.types.Language;
import monto.service.types.Product;

public class Edge {

    private Product product;
    private Language language;

    public Edge(Product product, Language language) {
        this.product = product;
        this.language = language;
    }

    public Product getProduct() {
        return product;
    }

    public Language getLanguage() {
        return language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (product != null ? !product.equals(edge.product) : edge.product != null) return false;
        return language != null ? language.equals(edge.language) : edge.language == null;

    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}
