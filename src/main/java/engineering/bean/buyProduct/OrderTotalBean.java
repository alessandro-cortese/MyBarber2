package engineering.bean.buyProduct;

import engineering.pattern.observer.Observer;
import model.buyProduct.Order;

import java.util.ArrayList;

public class OrderTotalBean implements Observer {

    private Order observedOrder ;
    private Double total ;
    private Integer points ;
    private ArrayList<CouponBean> couponBeans ;

    public OrderTotalBean(Order observedOrder) {
        setObservedOrder(observedOrder);
        couponBeans = new ArrayList<>() ;
        setPoints(0);
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
        setTotal(observedOrder.getFinalPrice());

        ArrayList<String> couponCodeArray = observedOrder.getCouponsState() ;
        couponBeans.clear();
        for (String couponCode : couponCodeArray) {
            couponBeans.add(new CouponBean(couponCode)) ;
        }

        setPoints((int) Math.round(observedOrder.getCartTotal()));
    }

    public ArrayList<CouponBean> getCouponBeans() {
        return couponBeans ;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
