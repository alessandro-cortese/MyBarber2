package engineering.bean.buyProduct;

public class CouponBean {

    private String couponCode ;

    public CouponBean(String couponCode) {
        setCouponCode(couponCode);
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    @Override
    public String toString() {
        return couponCode ;
    }
}
