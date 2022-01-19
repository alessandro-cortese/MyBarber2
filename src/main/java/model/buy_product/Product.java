package model.buy_product;

public class Product {

    private Integer isbn ;
    private String name ;
    private String description ;
    private Double price ;
    private String vendor ;

    public Product() {
        this(0,"","", 0.0, "") ;
    }

    public Product(Integer isbn, String name, String description, Double price, String vendor) {
        setIsbn(isbn);
        setName(name);
        setDescription(description);
        setPrice(price);
        setVendor(vendor);
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
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

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) return true ;
        else if (toCompare instanceof Product) {
            return ((Product) toCompare).getIsbn() == this.getIsbn() ;
        }
        else return false ;
    }
}
