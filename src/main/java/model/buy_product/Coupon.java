package model.buy_product;

import engineering.pattern.decorator.Priceable;

public class Coupon implements Priceable {

    //Supposto che nel codice del coupon ci sia scritto lo sconto si può fare un parsing
    private String couponCode ;
    private Priceable appliedPrice ;
    private Double couponDiscount ;


    public Coupon(String couponCode, Double couponDiscount) {
        setCouponCode(couponCode);
        setCouponDiscount(couponDiscount);
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    @Override
    public Double getPrice() {
        return appliedPrice.getPrice() - getCouponDiscount();
    }

    public void setAppliedPrice(Priceable appliedPrice) {
        this.appliedPrice = appliedPrice;
    }

    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

}
