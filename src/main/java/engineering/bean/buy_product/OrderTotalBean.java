package engineering.bean.buy_product;

import java.util.ArrayList;

public class OrderTotalBean {

    private Double orderTotal ;
    private ArrayList<Integer> couponCodes ;
    private Integer orderPoints ;

    public OrderTotalBean(Double oderTotal, ArrayList<Integer> couponCodes, Integer orderPoints) {
        setOrderTotal(oderTotal);
        setCouponCodes(couponCodes);
        setOrderPoints(orderPoints);
    }

    public OrderTotalBean(Double orderTotal) {
        this(orderTotal, null, 0) ;
    }


    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public ArrayList<Integer> getInternalCouponCodes() {
        return couponCodes;
    }

    public ArrayList<String> getExternalCouponCodes() {
        ArrayList<String> externalCouponCodes = new ArrayList<>() ;
        for (Integer code : couponCodes) {
            externalCouponCodes.add(Integer.toString(code)) ;
        }
        return externalCouponCodes ;
    }

    public void setCouponCodes(ArrayList<Integer> couponCodes) {
        this.couponCodes = couponCodes;
    }

    public Integer getOrderPoints() {
        return orderPoints;
    }

    public void setOrderPoints(Integer orderPoints) {
        this.orderPoints = orderPoints;
    }
}
