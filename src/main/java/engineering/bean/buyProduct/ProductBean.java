package engineering.bean.buyProduct;

public class ProductBean {

    private Integer productIndex ;
    private String name ;
    private String description ;
    private Double price ;
    //private Barber vendor ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(Integer productIndex) {
        this.productIndex = productIndex;
    }
}
