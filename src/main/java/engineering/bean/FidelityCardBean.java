package engineering.bean;

import engineering.bean.buy_product.CouponBean;

import java.util.ArrayList;
import java.util.List;

public class FidelityCardBean {

    private Integer pointsSale ;
    private List<CouponBean> couponBeans ;

    public FidelityCardBean(Integer pointsSale, List<CouponBean> couponBeans) {
        setPointsSale(pointsSale);
        setCouponBeans(couponBeans);
    }

    public Integer getPointsSale() {
        return pointsSale;
    }

    public void setPointsSale(Integer pointsSale) {
        this.pointsSale = pointsSale;
    }

    public List<CouponBean> getCouponBeans() {
        return couponBeans;
    }

    public void setCouponBeans(List<CouponBean> couponBeans) {
        this.couponBeans = couponBeans;
    }
}
