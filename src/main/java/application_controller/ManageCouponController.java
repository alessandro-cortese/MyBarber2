package application_controller;

import engineering.bean.FidelityCardBean;
import engineering.bean.UserBean;
import engineering.bean.buy_product.CouponBean;
import engineering.dao.CouponDAO;
import engineering.dao.UserDAO;
import engineering.exception.InvalidCouponException;
import engineering.exception.NotExistentUserException;
import javafx.util.Pair;
import model.Customer;
import model.buy_product.containers.CouponContainer;
import model.buy_product.coupon.Coupon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static model.buy_product.coupon.Coupon.PERCENTAGE_TYPE;
import static model.buy_product.coupon.Coupon.SUBTRACTION_TYPE;

public class ManageCouponController {

    private static final Integer SUB_FIVE_COUPON_COST = 200 ;
    private static final Integer SUB_TEN_COUPON_COST = 300 ;
    private static final Integer SUB_TWENTY_COUPON_COST = 400 ;
    private static final Integer PERC_FIFTEEN_COUPON_COST = 150;
    private static final Integer PERC_THIRTY_FIVE_COUPON_COST = 275;
    private static final Integer PERC_FIFTY_COUPON_COST = 500;

    private static final Map<Pair<Integer,Double>, Integer> couponCostMap = Map.of(
            new Pair<>(SUBTRACTION_TYPE, 5.0), SUB_FIVE_COUPON_COST,
            new Pair<>(SUBTRACTION_TYPE, 10.0), SUB_TEN_COUPON_COST,
            new Pair<>(SUBTRACTION_TYPE, 20.0), SUB_TWENTY_COUPON_COST,

            new Pair<>(PERCENTAGE_TYPE, 15.0), PERC_FIFTEEN_COUPON_COST,
            new Pair<>(PERCENTAGE_TYPE, 35.0), PERC_THIRTY_FIVE_COUPON_COST,
            new Pair<>(PERCENTAGE_TYPE, 50.0), PERC_FIFTY_COUPON_COST) ;



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


    public FidelityCardBean showFidelityCard(UserBean userBean) throws NotExistentUserException {
        FidelityCardBean fidelityCard = null;

        if (userBean != null) {
            customer = userDAO.loadCustomerByEmail(userBean.getUserEmail()) ;
            List<Coupon> coupons = couponDAO.loadCouponByUser(userBean.getUserEmail()) ;
            couponContainer = new CouponContainer(coupons) ;

            fidelityCard = createFidelityCardBean() ;
        }
        else {
            throw new NotExistentUserException("ACCESSO NON EFFETTUATO!!") ;
        }

        return fidelityCard ;
    }

    private FidelityCardBean createFidelityCardBean() {
        List<CouponBean> couponBeans = createCouponBeans() ;
        Integer cardPoints = customer.getCardPoints() ;
        return new FidelityCardBean(cardPoints, couponBeans) ;
    }

    public FidelityCardBean generateNewCoupon(CouponBean couponBean) throws InvalidCouponException, NotExistentUserException {

        if (customer == null) {
            throw new NotExistentUserException("ACCESSO NON EFFETTUATO!!") ;
        }

        Double couponValue = couponBean.getCouponDiscount() ;

        Integer couponType ;
        if (couponBean.getCouponType().compareTo("subtraction") == 0) {
            couponType = SUBTRACTION_TYPE ;
        }
        else {
            couponType = PERCENTAGE_TYPE ;
        }
        Pair<Integer, Double> productCostKey = new Pair<>(couponType, couponBean.getCouponDiscount()) ;

        Integer couponCost = couponCostMap.get(productCostKey) ;
        System.out.println(couponCost);
        if (couponCost != null) {
            Integer customerPoints = customer.getCardPoints();
            if (customerPoints >= couponCost) {
                createNewCoupon(couponType, couponValue) ;
                updateCustomer(couponCost) ;
            }
            else {
                throw new InvalidCouponException("PUNTI NON SUFFICIENTI PER GENERARE IL COUPON!!") ;
            }
        }
        return createFidelityCardBean();
    }

    private void updateCustomer(Integer couponCost) {
        customer.setCardPoints(customer.getCardPoints() - couponCost);
        userDAO.updateCustomerPoints(customer, customer.getCardPoints()) ;
    }


    private void createNewCoupon(Integer couponType, Double couponValue) {
        Coupon newCoupon = couponDAO.addNewCoupon(couponType, couponValue, customer.getEmail()) ;
        if (newCoupon != null) {
            couponContainer.addCoupon(newCoupon) ;
        }
    }


    public List<CouponBean> createCouponBeans() {
        ArrayList<CouponBean> couponBeans = new ArrayList<>() ;
        for (int i = 0 ; i < couponContainer.getSize() ; i++) {
            Coupon coupon = couponContainer.getCouponByIndex(i) ;
            if (coupon != null) {
                CouponBean couponBean ;
                if (coupon.getCouponType() == SUBTRACTION_TYPE) {
                    couponBean = new CouponBean(coupon.getCouponCode(), coupon.getCouponDiscount(), "subtraction");
                }
                else {
                    couponBean = new CouponBean(coupon.getCouponCode(), coupon.getCouponDiscount(), "percentage");
                }
                couponBeans.add(couponBean) ;
            }
        }
        return couponBeans ;
    }
}
