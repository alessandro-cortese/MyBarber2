package engineering.dao;

import engineering.exception.InvalidCouponException;
import model.buyProduct.Coupon;

public class CouponDAO {

    private final static String EXAMPLE_COUPON = "coupon_-20" ;

    public Coupon loadCouponByCode(String couponCode) throws InvalidCouponException {
        if (couponCode.compareTo(EXAMPLE_COUPON) == 0) {
            return new Coupon(couponCode) ;
        }
        else {
            throw new InvalidCouponException("Coupon non Trovato") ;
        }
    }

    public void invalidateCoupon(Coupon coupon) {}
}
