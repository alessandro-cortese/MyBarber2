package engineering.bean.buy_product;

import engineering.exception.InvalidCouponException;

import static model.buy_product.coupon.Coupon.PERCENTAGE_TYPE;
import static model.buy_product.coupon.Coupon.SUBTRACTION_TYPE;

public class CouponBean {

    private Integer couponCode ;
    private Double couponDiscount ;
    private Integer couponType ;

    public CouponBean() {}

    public CouponBean(String couponCode) throws InvalidCouponException {
        this(couponCode, 0.0) ;
    }

    public CouponBean(String couponCode, Double couponDiscount) throws InvalidCouponException {
        try {
            setCouponCode(Integer.parseInt(couponCode));
            setCouponDiscount(couponDiscount);
        }
        catch (NumberFormatException numberFormatException) {
            throw new InvalidCouponException("FORMATO DEL CODICE INSERITO NON VALIDO");
        }
    }

    public CouponBean(Integer couponCode, Double couponDiscount, Integer couponType) {
        setCouponCode(couponCode);
        setCouponDiscount(couponDiscount);
        setCouponType(couponType);
    }

    public CouponBean(Double couponDiscount, String couponType){
        setCouponDiscount(couponDiscount);
        if (couponType == "subtraction") setCouponType(SUBTRACTION_TYPE);
        else setCouponType(PERCENTAGE_TYPE);
    }

    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }


    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponCode(Integer couponCode) {
        this.couponCode = couponCode;
    }

    public String getExternalCouponCode() {
        return Integer.toString(couponCode) ;
    }

    public Integer getCouponCode() {
        return this.couponCode ;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public String getExternalCouponType() {
        if (couponType == SUBTRACTION_TYPE) return "subtraction" ;
        else return "percentage" ;
    }
}
