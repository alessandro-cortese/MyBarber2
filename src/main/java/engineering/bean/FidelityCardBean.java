package engineering.bean;

import engineering.bean.buy_product.CouponBean;

import java.util.ArrayList;

public class FidelityCardBean {

    private Integer pointsSale ;
    private ArrayList<CouponBean> couponBeans ;

    public FidelityCardBean(Integer pointsSale, ArrayList<CouponBean> couponBeans) {
        setPointsSale(pointsSale);
        setCouponBeans(couponBeans);
    }

    public Integer getPointsSale() {
        return pointsSale;
    }

    public void setPointsSale(Integer pointsSale) {
        this.pointsSale = pointsSale;
    }

    public ArrayList<CouponBean> getCouponBeans() {
        return couponBeans;
    }

    public void setCouponBeans(ArrayList<CouponBean> couponBeans) {
        this.couponBeans = couponBeans;
    }
}
