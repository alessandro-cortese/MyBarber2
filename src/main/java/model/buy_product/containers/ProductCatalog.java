package model.buy_product.containers;

import model.buy_product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

public class ProductCatalog {

    private final List<Product> productArrayList ;

    public ProductCatalog(List<Product> products) {
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

    public List<Product> filterByName(String productName) {
        List<Product> filteredProductArray = new ArrayList<>() ;
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
