package model.buy_product.containers;

import model.buy_product.coupon.Coupon;

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
        this.couponArrayList.sort(null);
    }

    public void addCoupon(Coupon newCoupon){
        if (verifyPresent(newCoupon) == null) {
            couponArrayList.add(newCoupon);
            couponArrayList.sort(null);
        }
    }

    public void removeCoupon(Coupon rmvCoupon) {
        Coupon presentCoupon = verifyPresent(rmvCoupon) ;
        if (presentCoupon != null) {
            couponArrayList.remove(presentCoupon) ;
        }
    }

    @Nullable
    public Coupon verifyPresent(Coupon verifyCoupon) {
        for (Coupon coupon : couponArrayList) {
            if (coupon.getCouponCode() == verifyCoupon.getCouponCode()) {
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
