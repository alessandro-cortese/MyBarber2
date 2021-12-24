package engineering.Decorator;

public class Decorator extends VisualService{
    private  VisualService service;

    public Decorator(VisualService service){
        this.service = service;
    }
    @Override
    public double getPrice(){
        double resultsFromRedirection = this.service.getPrice();
        return resultsFromRedirection;
    }


}
