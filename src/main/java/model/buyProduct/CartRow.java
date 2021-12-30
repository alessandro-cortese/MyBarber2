package model.buyProduct;

public class CartRow {

    private Product product ;
    private Integer quantity ;

    public CartRow(Product product, Integer quantity) {
        setProduct(product);
        setQuantity(quantity);
    }

    public Double getSubTotal() {
        return product.getPrice() * quantity ;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductIsbn() {
        return product.getIsbn() ;
    }

    public String getProductName() {
        return product.getName() ;
    }


    public Double getProductPrice() {
        return product.getPrice() ;
    }

    public String getProductVendor() {
        return product.getVendor() ;
    }
}
