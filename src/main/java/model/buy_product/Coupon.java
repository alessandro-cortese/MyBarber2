package model.buy_product;

import engineering.pattern.decorator.Priceable;

public class Coupon implements Priceable {

    //Supposto che nel codice del coupon ci sia scritto lo sconto si può fare un parsing
    private Integer couponCode ;
    private Priceable appliedPrice ;
    private Double couponDiscount ;


    public Coupon(Integer couponCode, Double couponDiscount) {
        setCouponCode(couponCode);
        setCouponDiscount(couponDiscount);
    }

    public Integer getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(Integer couponCode) {
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
