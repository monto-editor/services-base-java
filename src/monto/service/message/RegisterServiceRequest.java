package monto.service.message;

public class RegisterServiceRequest {

    private final String registerServiceID;
    private final Language language;
    private final Product product;
    private final String[] dependencies;


    public RegisterServiceRequest(String registerServiceID, Language language, Product product, String[] dependencies) {
        this.registerServiceID = registerServiceID;
        this.language = language;
        this.product = product;
        this.dependencies = dependencies;
    }

    public String getRegisterServiceID() {
        return registerServiceID;
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
