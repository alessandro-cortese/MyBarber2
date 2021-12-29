package engineering.dao;

import model.buyProduct.Product;
import model.buyProduct.containers.ProductCatalog;

public class ProductDAO {

    public ProductCatalog loadAllProducts() {
        ProductCatalog catalog = new ProductCatalog() ;
        catalog.addProduct(1,"Dopobarba","ciao a tutti", 22.0, "ciao");
        return catalog ;
    }

    public Product loadProductByIsbn(Integer isbn) {
        return new Product() ;
    }
}
