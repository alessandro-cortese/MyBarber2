package engineering.bean;

import engineering.exception.NegativePriceException;
import engineering.pattern.observer.Subject;

public class ServiceBean extends Subject {

    private String nameInfo;
    private String descriptionInfo;
    private String nameOfUsedProductInfo;
    private Double priceInfo;

    public ServiceBean() throws NegativePriceException{
        this("", "", "", 0.0D);

    }

    public ServiceBean (String name, String description, String nameOfUsedProduct, Double price) throws NegativePriceException{

        super();
        this.setNameInfo(name);
        this.setDescriptionInfo(description);
        this.setPriceInfo(price);
        this.setNameOfUsedProductInfo(nameOfUsedProduct);

    }

    public void setNameInfo(String nameInfo) {

        this.nameInfo = nameInfo;

    }

    public String getNameInfo() {

        return this.nameInfo;

    }

    public void setDescriptionInfo(String descriptionInfo) {

        this.descriptionInfo = descriptionInfo;

    }

    public String getDescriptionInfo() {

        return descriptionInfo;

    }

    public void setPriceInfo(Double priceInfo) throws NegativePriceException{

        if (controlPrice(priceInfo)){
            this.priceInfo = priceInfo;
        }

    }

    public Double getPriceInfo() {

        return priceInfo;

    }

    public String getNameOfUsedProductInfo() {

        return nameOfUsedProductInfo;

    }

    public void setNameOfUsedProductInfo(String nameOfUsedProductInfo) {

        this.nameOfUsedProductInfo = nameOfUsedProductInfo;

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
