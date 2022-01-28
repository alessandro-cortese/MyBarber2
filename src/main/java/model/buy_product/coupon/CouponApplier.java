package model.buy_product.coupon;

import engineering.exception.InvalidCouponException;
import engineering.exception.NegativePriceException;
import engineering.pattern.decorator.Priceable;
import model.buy_product.containers.CouponContainer;

import java.util.ArrayList;
import java.util.List;

public class CouponApplier {

    private Priceable originalPrice ;
    private Priceable finalPrice ;

    private final CouponContainer couponContainer ;

    public CouponApplier(Priceable priceable) {
        setOriginalPrice(priceable);
        couponContainer = new CouponContainer() ;
        finalPrice = originalPrice ;
    }

    public void setOriginalPrice(Priceable originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getFinalPrice() {
        return finalPrice.getPrice() ;
    }

    public void applyCoupon(Coupon coupon) throws InvalidCouponException, NegativePriceException {
        if (couponContainer.verifyPresent(coupon) == null) {
            couponContainer.addCoupon(coupon);
            try {
                applyDiscount() ;
            } catch (NegativePriceException negativePriceException) {
                couponContainer.removeCoupon(coupon);
                applyDiscount();
                throw negativePriceException ;
            }
        }
        else {
            throw new InvalidCouponException("IL COUPON è GIà STATO UTILIZZATO!!") ;
        }
    }

    private void applyDiscount() throws NegativePriceException {
        finalPrice = originalPrice ;
        for (int i = 0 ; i < couponContainer.getSize() ; i++) {
            Coupon currentCoupon = couponContainer.getCouponByIndex(i) ;
            if (currentCoupon != null) {
                currentCoupon.setAppliedPrice(finalPrice);
                if (currentCoupon.getPrice() < 0) {
                    throw new NegativePriceException("IL COUPON APPLICATO NEGATIVIZZA IL PREZZO") ;
                }
                finalPrice = currentCoupon ;
            }
        }
    }

    public List<Integer> getAppliedCouponCode() {
        List<Integer> couponCodes = new ArrayList<>() ;
        for (int i = 0 ; i < couponContainer.getSize() ; i++) {
            if (couponContainer.getCouponByIndex(i) != null) couponCodes.add(couponContainer.getCouponByIndex(i).getCouponCode()) ;
        }
        return couponCodes ;
    }

    public void reset() {
        finalPrice = originalPrice ;
        couponContainer.clear();
    }

    public CouponContainer getCouponContainer() {
        return couponContainer ;
    }
}
