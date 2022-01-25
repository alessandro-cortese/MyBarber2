package model.buy_product.coupon;

import engineering.pattern.decorator.Priceable;

public abstract class Coupon implements Priceable, Comparable<Coupon> {

    private Integer couponCode ;
    private Priceable appliedPrice ;
    private Double couponDiscount ;
    private Integer couponType ;

    public static final Integer SUBTRACTION_TYPE = 0 ;
    public static final Integer PERCENTAGE_TYPE = 1 ;

    protected Coupon(Integer couponCode, Double couponDiscount) {
        setCouponCode(couponCode);
        setCouponDiscount(couponDiscount);
    }

    protected Coupon(Integer couponCode, Double couponDiscount, Integer couponType) {
        setCouponCode(couponCode);
        setCouponDiscount(couponDiscount);
        setCouponType(couponType);
    }

    public Integer getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(Integer couponCode) {
        this.couponCode = couponCode;
    }

    public void setAppliedPrice(Priceable appliedPrice) {
        this.appliedPrice = appliedPrice;
    }

    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public Integer getCouponType() {
        return couponType ;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType ;
    }

    protected Priceable getAppliedPrice() {
        return appliedPrice ;
    }

    @Override
    public int compareTo(Coupon toCompare) {
        Integer typeComparison = Integer.compare(this.couponType, toCompare.getCouponType()) ;
        if (typeComparison != 0) return typeComparison ;
        else {
            return Double.compare(this.getCouponDiscount(), toCompare.getCouponDiscount());
        }
    }
}
