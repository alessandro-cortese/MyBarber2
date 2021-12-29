package engineering.dao;

import model.buyProduct.Coupon;

public class CouponDAO {

    public Coupon loadCouponByCode(String couponCode) {
        return new Coupon("") ;
    }

    public void invalidateCoupon(Coupon coupon) {}
}
