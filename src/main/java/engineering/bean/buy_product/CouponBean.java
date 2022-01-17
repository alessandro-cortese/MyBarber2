package engineering.bean.buy_product;

import engineering.exception.InvalidCouponException;

public class CouponBean {

    private String couponCode ;
    private Double couponDiscount ;
    private String couponType ;

    public CouponBean(String couponCode) {
        setCouponCode(couponCode);
    }

    public CouponBean(String couponCode, Double couponDiscount) throws InvalidCouponException {
        try {
            Integer.parseInt(couponCode) ;
            setCouponCode(couponCode);
            setCouponDiscount(couponDiscount);
        }
        catch (NumberFormatException numberFormatException) {
            throw new InvalidCouponException("FORMATO DEL CODICE INSERITO NON VALIDO");
        }
    }

    public CouponBean(Integer couponCode, Double couponDiscount, String couponType) {
        setCouponCode(Integer.toString(couponCode));
        setCouponDiscount(couponDiscount);
        setCouponType(couponType);
    }

    public CouponBean(Double couponDiscount, String couponType){
            setCouponDiscount(couponDiscount);
            setCouponType(couponType);
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
        if (couponType == null || couponDiscount == null) {
            return String.format("Codice: %s", couponCode);
        }
        else {
            return String.format("Codice: %s\t\tTipo: %s\t\tValore: %.2f", couponCode, couponType, couponDiscount);
        }
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }
}
