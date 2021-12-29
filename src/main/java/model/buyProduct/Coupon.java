package model.buyProduct;

public class Coupon {

    private String couponCode ;

    public Coupon(String couponCode) {
        setCouponCode(couponCode);
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
