package engineering.dao;

import model.buyProduct.Coupon;

public class CouponDAO {

    private final static String EXAMPLE_COUPON = "coupon_-20" ;

    public Coupon loadCouponByCode(String couponCode) {
        System.out.println("ciao 2");
        if (couponCode.compareTo(EXAMPLE_COUPON) == 0) {
            return new Coupon(couponCode) ;
        }
        return null ;
    }

    public void invalidateCoupon(Coupon coupon) {}
}
