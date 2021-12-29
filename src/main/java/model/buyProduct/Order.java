package model.buyProduct;

import java.util.ArrayList;
import java.util.Date;

public class Order {

    private Cart cart ;
    private String address ;
    private String telephone ;
    private String paymentOption ;
    private Date date ;

    private ArrayList<Coupon> couponArrayList ;

    //private Price total ;


    public Order(Cart cart){
        this.cart = cart ;
        couponArrayList = new ArrayList<>() ;
    }

    public void addCoupon(Coupon coupon) {
        couponArrayList.add(coupon) ;
    }

    public void removeCoupon(Coupon toRemoveCoupon) {
        couponArrayList.removeIf(coupon -> coupon.getCouponCode().compareTo(toRemoveCoupon.getCouponCode()) == 0);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
