package model.buy_product.coupon;

public class SubtractionCoupon extends Coupon{

    public SubtractionCoupon(Integer couponCode, Double couponDiscount) {
        super(couponCode, couponDiscount, SUBTRACTION_TYPE) ;
    }

    @Override
    public Double getPrice() {
        return getAppliedPrice().getPrice() - getCouponDiscount() ;
    }
}
