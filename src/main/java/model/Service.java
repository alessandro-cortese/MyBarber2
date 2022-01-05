package model;

import model.buy_product.Product;

public class Service {

    private String name;
    private String description;
    private double price;
    private Product usedProduct;

    public Service () {

        this("", "", 0.0D, null);

    }

    public Service (String name, String description, double price, Product usedProduct) {

        this.setName(name) ;
        this.setDescription(description) ;
        this.setPrice(price);
        this.setUsedProduct(usedProduct);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public  void setDescription(String description){
        this.description = description;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setUsedProduct(Product usedProduct) {
        this.usedProduct = usedProduct;
    }

    public Product getUsedProduct() {
        return this.usedProduct;
    }
}
