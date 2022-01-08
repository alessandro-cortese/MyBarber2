package model.buy_product;

import engineering.exception.InvalidCouponException;
import engineering.exception.NegativePriceException;
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

    private final CouponContainer couponContainer;
    private Priceable originalPrice ;
    private Priceable finalPrice;


    public Order(Priceable originalPrice){
        setOriginalPrice(originalPrice);
        couponContainer = new CouponContainer() ;
        setFinalPrice(originalPrice);
    }

    public void addCoupon(Coupon coupon) throws InvalidCouponException, NegativePriceException {
        couponContainer.addCoupon(coupon);
        try {
            applyDiscount() ;
        } catch (NegativePriceException negativePriceException) {
            couponContainer.removeCoupon(coupon);
            applyDiscount();
            throw negativePriceException ;
        }
        notifyObservers();
    }

    private void applyDiscount() throws NegativePriceException {
        finalPrice = originalPrice ;
        for (int i = 0 ; i < couponContainer.getSize() ; i++) {
            Coupon currentCoupon = couponContainer.getCouponByIndex(i) ;
            if (currentCoupon != null) {
                currentCoupon.setAppliedPrice(finalPrice);
                if (currentCoupon.getPrice() < 0) {
                    throw new NegativePriceException() ;
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
                couponCodeArray.add(Integer.toString(currentCoupon.getCouponCode()));
            }
        }
        return couponCodeArray ;
    }


    public Integer getOrderPoints() {
        return (int) Math.round(originalPrice.getPrice()) ;
    }


    public Priceable getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Priceable originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setFinalPrice(Priceable finalPrice) {
        this.finalPrice = finalPrice ;
    }

    public void removeAllCoupon() {
        couponContainer.clear() ;
        setFinalPrice(originalPrice);
        notifyObservers();
    }
}
