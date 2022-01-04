package model.buy_product;

import engineering.pattern.decorator.Priceable;

public class Coupon implements Priceable {

    //Supposto che nel codice del coupon ci sia scritto lo sconto si può fare un parsing
    private String couponCode ;
    private String ownerUser ;
    private Priceable appliedPrice ;


    public Coupon(String couponCode, String ownerUser) {
        setCouponCode(couponCode);
        setOwnerUser(ownerUser);
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


    public String getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(String ownerUser) {
        this.ownerUser = ownerUser;
    }
}
