package engineering.pattern.decorator;

public class Price implements Priceable{

    private Double price ;

    public Price(Double price) {
        setPrice(price);
    }

    @Override
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
