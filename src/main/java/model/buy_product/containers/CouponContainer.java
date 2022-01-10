package model.buy_product.containers;

import engineering.exception.InvalidCouponException;
import model.buy_product.Coupon;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CouponContainer {

    private final List<Coupon> couponArrayList ;

    public CouponContainer() {
        couponArrayList = new ArrayList<>() ;
    }

    public CouponContainer(List<Coupon> coupons) {
        this.couponArrayList = coupons ;
    }

    public void addCoupon(Coupon newCoupon) throws InvalidCouponException {
        if (verifyPresent(newCoupon) == null) {
            couponArrayList.add(newCoupon);
        }
        else {
            throw new InvalidCouponException("Coupon già Utilizzato");
        }

    }

    public void removeCoupon(Coupon rmvCoupon) {
        Coupon presentCoupon = verifyPresent(rmvCoupon) ;
        if (presentCoupon != null) {
            couponArrayList.remove(presentCoupon) ;
        }
    }

    @Nullable
    private Coupon verifyPresent(Coupon verifyCoupon) {
        for (Coupon coupon : couponArrayList) {
            if (coupon.getCouponCode().compareTo(verifyCoupon.getCouponCode()) == 0) {
                return coupon ;
            }
        }
        return null ;
    }

    @Nullable
    public Coupon getCouponByIndex(Integer index) {
        if (index < couponArrayList.size()) {
            return couponArrayList.get(index) ;
        }
        return null ;
    }

    public Integer getSize() {
        return couponArrayList.size() ;
    }

    public void clear() {
        couponArrayList.clear();
    }
}
