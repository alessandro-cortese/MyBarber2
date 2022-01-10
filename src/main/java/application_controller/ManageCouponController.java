package application_controller;

import engineering.bean.FidelityCardBean;
import engineering.bean.UserBean;
import engineering.bean.buy_product.CouponBean;
import engineering.dao.CouponDAO;
import engineering.dao.UserDAO;
import engineering.exception.InvalidCouponException;
import model.Customer;
import model.buy_product.Coupon;
import model.buy_product.containers.CouponContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManageCouponController {

    private static final Integer FIVE_COUPON_COST = 100 ;
    private static final Integer TEN_COUPON_COST = 200 ;
    private static final Integer TWENTY_COUPON_COST = 400 ;

    private final Map<Double, Integer> couponCostMap = Map.of(5.0, FIVE_COUPON_COST, 10.0, TEN_COUPON_COST, 20.0, TWENTY_COUPON_COST) ;


    private final CouponDAO couponDAO ;
    private CouponContainer couponContainer ;
    private final UserDAO userDAO ;

    private Customer customer ;


    public ManageCouponController() {
        couponDAO = new CouponDAO() ;
        couponContainer = null ;
        userDAO = new UserDAO() ;
        customer = null;
    }


    public FidelityCardBean showFidelityCard(UserBean userBean) {
        FidelityCardBean fidelityCard = null;

        if (userBean != null) {
            customer = userDAO.loadCustomerByEmail(userBean.getUserEmail()) ;
            List<Coupon> coupons = couponDAO.loadCouponByUser(userBean.getUserEmail()) ;
            couponContainer = new CouponContainer(coupons) ;

            List<CouponBean> couponBeans = createCouponBeans() ;
            fidelityCard = new FidelityCardBean(customer.getCardPoints(), couponBeans) ;
        }

        return fidelityCard ;
    }

    public FidelityCardBean generateNewCoupon(CouponBean couponBean) throws InvalidCouponException {
        Double couponValue = couponBean.getCouponDiscount() ;
        Integer couponCost = couponCostMap.get(couponValue) ;
        FidelityCardBean fidelityCardBean = null ;
        if (couponCost != null) {
            Integer customerPoints = customer.getCardPoints();
            if (customerPoints >= couponCost) {
                createNewCoupon(couponValue) ;
                customer.setCardPoints(customer.getCardPoints() - couponCost);
                userDAO.updateCustomerPoints(customer.getCardPoints(), customer.getEmail()) ;
                fidelityCardBean = new FidelityCardBean(customer.getCardPoints(), createCouponBeans()) ;
            }
            else {
                throw new InvalidCouponException("PUNTI NON SUFFICIENTI PER GENERARE IL COUPON!!") ;
            }
        }
        return fidelityCardBean ;
    }

    private void createNewCoupon(Double couponValue) throws InvalidCouponException {
        Coupon newCoupon = couponDAO.addNewCoupon(couponValue, customer.getEmail()) ;
        if (newCoupon != null) {
            couponContainer.addCoupon(newCoupon) ;
        }
    }


    public List<CouponBean> createCouponBeans() {
        ArrayList<CouponBean> couponBeans = new ArrayList<>() ;
        for (int i = 0 ; i < couponContainer.getSize() ; i++) {
            Coupon coupon = couponContainer.getCouponByIndex(i) ;
            if (coupon != null) {
                CouponBean couponBean = new CouponBean(coupon.getCouponCode(), coupon.getCouponDiscount()) ;
                couponBeans.add(couponBean) ;
            }
        }
        return couponBeans ;
    }
}
