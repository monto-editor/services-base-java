package monto.service.message;

public class RegisterServiceRequest {

    private final String serviceID;
    private final Language language;
    private final Product product;
    private final String[] dependencies;


    public RegisterServiceRequest(String serviceID, Language language, Product product, String... dependencies) {
        this.serviceID = serviceID;
        this.language = language;
        this.product = product;
        this.dependencies = dependencies;
    }

    public String getServiceID() {
        return serviceID;
    }

    public Language getLanguage() {
        return language;
    }

    public Product getProduct() {
        return product;
    }

    public String[] getDependencies() {
        return dependencies;
    }
}
