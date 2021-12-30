package model.buyProduct;

import engineering.pattern.decorator.Price;
import engineering.pattern.decorator.Priceable;
import engineering.pattern.observer.Subject;

import java.util.ArrayList;
import java.util.Date;

public class Order extends Subject {

    private Cart cart ;
    private String address ;
    private String telephone ;
    private String paymentOption ;
    private Date date ;

    private ArrayList<Coupon> couponArrayList ;

    private Priceable price ;


    public Order(Cart cart){
        setCart(cart);
        couponArrayList = new ArrayList<>() ;
        price = new Price(getCartTotal()) ;
    }

    public void addCoupon(Coupon coupon) {
        couponArrayList.add(coupon) ;
        applyDiscount(coupon) ;
        notifyObservers();
    }

    private void applyDiscount(Coupon coupon) {
        coupon.setAppliedPrice(price);
        this.price = coupon ;
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

    public Double getFinalPrice() {
        return this.price.getPrice() ;
    }

    public ArrayList<String> getCouponsState() {
        ArrayList<String> couponCodeArray = new ArrayList<>() ;
        for (Coupon coupon : couponArrayList) {
            couponCodeArray.add(coupon.getCouponCode()) ;
        }
        return couponCodeArray ;
    }

    public Cart getCart() {
        return cart;
    }

    public Double getCartTotal() {
        return cart.getTotal() ;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
