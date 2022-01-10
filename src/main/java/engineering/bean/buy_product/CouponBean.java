package engineering.bean.buy_product;

public class CouponBean {

    private String couponCode ;
    private Double couponDiscount ;

    public CouponBean(String couponCode) {
        setCouponCode(couponCode);
    }

    public CouponBean(String couponCode, Double couponDiscount) {
        setCouponCode(couponCode);
        setCouponDiscount(couponDiscount);
    }

    public CouponBean(Integer couponCode, Double couponDiscount) {
        setCouponCode(Integer.toString(couponCode));
        setCouponDiscount(couponDiscount);
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }


    @Override
    public String toString() {
        return String.format("Codice Coupon: %s", couponCode);
    }
}
