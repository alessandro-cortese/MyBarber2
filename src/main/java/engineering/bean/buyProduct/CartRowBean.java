package engineering.bean.buyProduct;

public class CartRowBean {

    private Integer quantity ;
    private ProductBean productBean ;


    public CartRowBean(Integer quantity, ProductBean productBean) {
        setQuantity(quantity);
        setProductBean(productBean) ;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }
}
