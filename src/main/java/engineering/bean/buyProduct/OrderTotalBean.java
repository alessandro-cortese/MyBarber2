package engineering.bean.buyProduct;

import engineering.pattern.observer.Observer;
import model.buyProduct.Order;

public class OrderTotalBean implements Observer {

    private Order observedOrder ;
    private Double total ;

    public OrderTotalBean(Order observedOrder) {
        setObservedOrder(observedOrder);
        observedOrder.attach(this);
        update();
    }

    public Order getObservedOrder() {
        return observedOrder;
    }

    public void setObservedOrder(Order observedOrder) {
        this.observedOrder = observedOrder;
    }

    public double getTotal() {
        return this.total ;
    }

    @Override
    public void update() {
        setTotal(observedOrder.getPrice());
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
