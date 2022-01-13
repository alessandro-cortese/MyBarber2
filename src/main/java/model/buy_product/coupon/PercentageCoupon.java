package model.buy_product.coupon;

public class PercentageCoupon extends Coupon{

    public PercentageCoupon(Integer couponCode, Double couponDiscount) {
        super(couponCode, couponDiscount, PERCENTAGE_TYPE) ;
    }

    @Override
    public Double getPrice() {
        Double finalPrice = getAppliedPrice().getPrice() * (1 - getCouponDiscount() / 100.0) ;
        return Math.round(finalPrice * 100) / 100.0;
    }
}
