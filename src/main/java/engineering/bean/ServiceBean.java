package engineering.bean;

import engineering.exception.NegativePriceException;
import engineering.pattern.observer.Subject;

public class ServiceBean extends Subject {

    private String name;
    private String description;
    private String nameOfUsedProduct;
    private Double price;

    public ServiceBean() throws NegativePriceException{
        this("", "", "", 0.0D);

    }

    public ServiceBean (String name, String description, String nameOfUsedProduct, Double price) throws NegativePriceException{

        super();
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

    public void setPrice(Double price) throws NegativePriceException{

        if (controlPrice(price)){
            this.price = price;
        }

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

    private boolean controlPrice(Double localPrice) throws NegativePriceException {

        boolean flag = false;
        if(localPrice <= 0.0D){
            throw new NegativePriceException();
        }
        else if(localPrice > 0.0D) {
            flag = true;
        }

        return flag;

    }

    public void notifyChanges(){

        super.notifyObservers();

    }

}
