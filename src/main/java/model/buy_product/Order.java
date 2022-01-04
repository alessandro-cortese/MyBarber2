package model.buy_product;

import engineering.exception.InvalidCouponException;
import engineering.pattern.decorator.Priceable;
import engineering.pattern.observer.Subject;
import model.buy_product.containers.CouponContainer;

import java.util.ArrayList;
import java.util.Date;

public class Order extends Subject {

    private String address ;
    private String telephone ;
    private String paymentOption ;
    private Date date ;


    private Cart cart ;
    private CouponContainer couponContainer;
    private Priceable finalPrice;


    public Order(Cart cart){
        setCart(cart);
        couponContainer = new CouponContainer() ;
        finalPrice = cart ;
    }

    public void addCoupon(Coupon coupon) throws InvalidCouponException {
        couponContainer.addCoupon(coupon);
        applyDiscount() ;
        notifyObservers();
    }

    private void applyDiscount() {
        finalPrice = cart ;
        for (int i = 0 ; i < couponContainer.getSize() ; i++) {
            Coupon currentCoupon = couponContainer.getCouponByIndex(i) ;
            if (currentCoupon != null) {
                currentCoupon.setAppliedPrice(finalPrice);
                if (currentCoupon.getPrice() < 0) {
                    //throw NegativePriceException
                }
                finalPrice = currentCoupon ;
            }
        }
    }

    public CouponContainer getCouponContainer() {
        return couponContainer;
    }

    public void removeCoupon(Coupon toRemoveCoupon) {
        couponContainer.removeCoupon(toRemoveCoupon);
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
        return this.finalPrice.getPrice() ;
    }

    public ArrayList<String> getCouponsState() {
        ArrayList<String> couponCodeArray = new ArrayList<>() ;
        for (int i = 0 ; i < couponContainer.getSize() ; i++) {
            Coupon currentCoupon = couponContainer.getCouponByIndex(i) ;
            if (currentCoupon != null) {
                couponCodeArray.add(currentCoupon.getCouponCode());
            }
        }
        return couponCodeArray ;
    }

    public Cart getCart() {
        return cart;
    }

    public Integer getOrderPoints() {
        return (int) Math.round(cart.getPrice()) ;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
