package engineering.bean.buyProduct;

public class ProductBean {

    private Integer isbn ;
    private String name ;
    private String description ;
    private Double price ;
    //private Barber vendor ;

    public ProductBean() {
        this(0,"","",0.0) ;
    }

    public ProductBean(Integer isbn) {
        this(isbn, "", "", 0.0) ;
    }

    public ProductBean(Integer isbn, String name, String description, Double price) {
        setIsbn(isbn);
        setName(name);
        setDescription(description);
        setPrice(price);
    }

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


    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }
}
