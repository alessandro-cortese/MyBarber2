package engineering.bean;

public class ServiceBean {

    private String name;
    private String description;
    private String nameOfUsedProduct;
    private Double price;

    public ServiceBean() {

        this("", "", "", 0.0D);

    }

    public ServiceBean (String name, String description, String nameOfUsedProduct, Double price) {

        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setNameOfUsedProduct(nameOfUsedProduct);

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public String getNameOfUsedProduct() {
        return nameOfUsedProduct;
    }

    public void setNameOfUsedProduct(String nameOfUsedProduct) {
        this.nameOfUsedProduct = nameOfUsedProduct;
    }

}
