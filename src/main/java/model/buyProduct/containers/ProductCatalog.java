package model.buyProduct.containers;

import model.buyProduct.Product;

import java.util.ArrayList;
import java.util.Objects;

import javax.annotation.Nullable;

public class ProductCatalog {

    private ArrayList<Product> productArrayList ;

    public ProductCatalog(ArrayList<Product> products) {
        this.productArrayList = products ;
    }

    public ProductCatalog() {
        this.productArrayList = new ArrayList<>() ;
    }

    public Product createProduct(Integer isbn, String name, String description, Double price, String vendor) {
        return new Product(isbn, name, description, price, vendor) ;
    }

    public void addProduct(Integer isbn, String name, String description, Double price, String vendor) {
        productArrayList.add(createProduct(isbn, name, description, price, vendor)) ;
    }

    public ArrayList<Product> filterByName(String productName) {
        ArrayList<Product> filteredProductArray = new ArrayList<>() ;
        for (Product product : productArrayList) {
            if (product.getName().contains( productName)) {
                filteredProductArray.add(product) ;
            }
        }
        return filteredProductArray ;
    }

    @Nullable
    public Product getProductByIsbn(Integer isbn) {
        for (Product product : productArrayList) {
            if (Objects.equals(product.getIsbn(), isbn)) {
                return product;
            }
        }
        return null ;
    }

    @Nullable
    public Product getProductByName(String nameOfProduct) {
        for (Product product : productArrayList) {
            if (product.getName().compareTo(nameOfProduct) == 0) {
                return product;
            }
        }
        return null;
    }
}
