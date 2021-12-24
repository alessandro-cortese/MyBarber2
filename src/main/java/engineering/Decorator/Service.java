package engineering.Decorator;

public class Service extends VisualService {
    double price;
    String name;
    String description;
    @Override
    public double getPrice(){
        return this.price;
    }
}
