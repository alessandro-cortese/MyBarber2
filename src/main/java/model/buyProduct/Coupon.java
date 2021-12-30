package model.buyProduct;

import engineering.pattern.decorator.Priceable;

public class Coupon implements Priceable {

    //Supposto che nel codice del coupon ci sia scritto lo sconto si può fare un parsing
    private String couponCode ;
    private Priceable appliedPrice ;


    public Coupon(String couponCode) {
        setCouponCode(couponCode);
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    @Override
    public Double getPrice() {
        return appliedPrice.getPrice() - 20;
    }

    public void setAppliedPrice(Priceable appliedPrice) {
        this.appliedPrice = appliedPrice;
    }
}
