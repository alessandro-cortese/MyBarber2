package application_controller;

import engineering.bean.FidelityCardBean;
import engineering.bean.UserBean;
import engineering.bean.buy_product.CouponBean;
import engineering.dao.CouponDAO;
import engineering.dao.CustomerDAO;
import engineering.exception.CardPointsException;
import engineering.exception.NotExistentUserException;
import model.Customer;
import model.buy_product.containers.CouponContainer;
import model.buy_product.coupon.Coupon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static model.buy_product.coupon.Coupon.PERCENTAGE_TYPE;
import static model.buy_product.coupon.Coupon.SUBTRACTION_TYPE;

public class ManageCouponController {

    private final CouponDAO couponDAO ;
    private CouponContainer couponContainer ;
    private final CustomerDAO customerDAO ;

    private Customer customer ;

    private final List<CouponBean> couponCosts ;


    public ManageCouponController(UserBean loggedUser) {
        couponDAO = new CouponDAO() ;
        customerDAO = new CustomerDAO();
        try {
            if (loggedUser == null) throw new NotExistentUserException() ;

            customer = customerDAO.loadCustomerByEmail(loggedUser.getUserEmail());
            List<Coupon> coupons = couponDAO.loadCouponByUser(customer.getEmail()) ;
            couponContainer = new CouponContainer(coupons) ;
        } catch (NotExistentUserException e) {
            couponContainer = new CouponContainer();
            customer = null ;
        }

        Map<Double, Integer> subtractionCouponCostMap = Map.of(5.0, 200, 10.0, 300, 20.0, 400);
        List<CouponBean> subtractionCouponBean = createCouponPriceBean(subtractionCouponCostMap, SUBTRACTION_TYPE) ;

        Map<Double, Integer> percentageCouponCostMap = Map.of(15.0, 150, 35.0, 275, 50.0, 500);
        List<CouponBean> percentageCouponBean = createCouponPriceBean(percentageCouponCostMap, PERCENTAGE_TYPE) ;

        couponCosts = new ArrayList<>() ;
        couponCosts.addAll(subtractionCouponBean) ;
        couponCosts.addAll(percentageCouponBean) ;
        couponCosts.sort(Comparator.comparingInt(CouponBean::getCouponPointsPrice));
    }


    public List<CouponBean> showCouponCosts() {
        return couponCosts ;
    }


    public FidelityCardBean showFidelityCard() throws NotExistentUserException {
        if (customer == null) throw new NotExistentUserException("ACCESSO NON EFFETTUATO!!") ;

        return createFidelityCardBean() ;
    }


    public FidelityCardBean generateNewCoupon(CouponBean couponBean) throws NotExistentUserException, CardPointsException {
        if (customer == null) {
            throw new NotExistentUserException("ACCESSO NON EFFETTUATO!!") ;
        }

        Double couponValue = couponBean.getCouponDiscount() ;
        Integer couponCost = couponBean.getCouponPointsPrice() ;
        Integer customerPoints = customer.getCardPoints();
        if (customerPoints >= couponCost) {
            createNewCoupon(couponBean.getCouponType(), couponValue) ;
            updateCustomer(couponCost) ;
        }
        else {
            throw new CardPointsException("PUNTI NON SUFFICIENTI PER GENERARE IL COUPON!!") ;
        }

        return createFidelityCardBean();
    }

    private void updateCustomer(Integer couponCost) {
        customer.setCardPoints(customer.getCardPoints() - couponCost);
        customerDAO.updateCustomerPoints(customer) ;
    }


    private void createNewCoupon(Integer couponType, Double couponValue) {
        Coupon newCoupon = couponDAO.addNewCoupon(couponType, couponValue, customer.getEmail()) ;
        if (newCoupon != null) {
            couponContainer.addCoupon(newCoupon) ;
        }
    }


    private FidelityCardBean createFidelityCardBean() {
        List<CouponBean> couponBeans = createCouponBeans() ;
        Integer cardPoints = customer.getCardPoints() ;
        return new FidelityCardBean(cardPoints, couponBeans, customer.getEmail()) ;
    }


    public List<CouponBean> createCouponBeans() {
        List<CouponBean> couponBeans = new ArrayList<>() ;
        for (int i = 0 ; i < couponContainer.getSize() ; i++) {
            Coupon coupon = couponContainer.getCouponByIndex(i) ;
            if (coupon != null) {
                CouponBean couponBean = new CouponBean(coupon.getCouponCode(), coupon.getCouponDiscount(), coupon.getCouponType());
                couponBeans.add(couponBean) ;
            }
        }
        return couponBeans ;
    }

    private List<CouponBean> createCouponPriceBean(Map<Double,Integer> couponCostMap, Integer couponType) {
        List<CouponBean> couponBeanList = new ArrayList<>() ;

        for (Map.Entry<Double,Integer> couponDiscount : couponCostMap.entrySet()) {
            CouponBean couponBean = new CouponBean() ;
            couponBean.setCouponDiscount(couponDiscount.getKey());
            couponBean.setCouponType(couponType);
            couponBean.setCouponPointsPrice(couponDiscount.getValue());

            couponBeanList.add(couponBean) ;
        }

        return couponBeanList ;
    }
}
