package application_controller;

import boundary.buy_product.BuyProductEMailSystemBoundary;
import boundary.buy_product.BuyProductPaypalBoundary;
import engineering.bean.AccessInfoBean;
import engineering.bean.UserBean;
import engineering.bean.buy_product.*;
import engineering.dao.*;
import engineering.exception.InvalidCouponException;
import engineering.exception.NegativePriceException;
import engineering.exception.NotExistentUserException;
import model.Customer;
import model.buy_product.Cart;
import model.buy_product.Order;
import model.buy_product.Product;
import model.buy_product.containers.ProductCatalog;
import model.buy_product.coupon.Coupon;

import java.time.Instant;
import java.util.*;

import static engineering.bean.buy_product.VendorOrderBean.*;

public class BuyProductController {

    private final CouponDAO couponDAO ;

    private final ProductCatalog productCatalog ;

    private final Cart cart ;
    private final Order order ;

    private final CartBean cartBean ;
    private final OrderTotalBean orderBean ;

    private Customer customer;

    public BuyProductController() {
        ProductDAO productDAO = new ProductDAO();
        couponDAO = new CouponDAO() ;
        productCatalog = productDAO.loadAllProducts() ;
        cart = new Cart() ;
        order = new Order(cart) ;
        cartBean = new CartBean(cart) ;
        orderBean = new OrderTotalBean(order) ;
    }

    public BuyProductController(UserBean loggedUserBean) throws NotExistentUserException {
        this() ;
        customerLogin(loggedUserBean);
    }

    public List<ProductBean> filterProductList(ProductSearchInfoBean searchInfoBean) {
        String searchName = searchInfoBean.getProductName() ;
        List<Product> productArrayList = productCatalog.filterByName(searchName) ;
        return createArrayProductBean(productArrayList) ;
    }

    private List<ProductBean> createArrayProductBean(List<Product> productArrayList) {
        List<ProductBean> productBeanArrayList = new ArrayList<>() ;
        for (Product product : productArrayList) {
            ProductBean productBean = new ProductBean(product.getIsbn(), product.getName(), product.getDescription(), product.getPrice()) ;
            productBeanArrayList.add(productBean) ;
        }
        return productBeanArrayList ;
    }

    public void insertProductToCart(ProductBean productBean) {
        Product product = productCatalog.getProductByIsbn(productBean.getBeanIsbn()) ;
        cart.insertProduct(product);
    }

    public CartBean showCart() {
        return cartBean ;
    }

    public void removeProductFromCart(ProductBean productBean) {
        Product rmvProduct = productCatalog.getProductByIsbn(productBean.getBeanIsbn()) ;
        cart.removeProduct(rmvProduct);
    }

    public void applyCoupon(CouponBean couponBean) throws InvalidCouponException, NegativePriceException {
        Coupon myCoupon = couponDAO.loadCouponByCode(Integer.parseInt(couponBean.getCouponCode()));
        order.addCoupon(myCoupon);
    }

    public void completeOrder(OrderInfoBean orderInfoBean, UserBean loggedUser) throws NotExistentUserException {
        UserDAO userDAO = new UserDAO() ;
        if (customer == null) {
            customer = userDAO.loadCustomerByEmail(loggedUser.getUserEmail());
        }

        order.setAddress(orderInfoBean.getAddressInfo());
        order.setTelephone(orderInfoBean.getTelephoneInfo());
        order.setPaymentOption(orderInfoBean.getPaymentOptionInfo());
        order.setDate(Date.from(Instant.now()));

        OrderDAO orderDAO = new OrderDAO() ;
        orderDAO.saveOrder(order, cart, customer);

        CartDAO cartDAO = new CartDAO() ;
        cartDAO.saveCart(cart, order.getOrderCode());

        customer.setCardPoints(customer.getCardPoints() + order.getOrderPoints());
        userDAO.updateCustomerPoints(customer, customer.getCardPoints());

        BuyProductPaypalBoundary paypalBoundary = new BuyProductPaypalBoundary() ;
        paypalBoundary.pay(new OrderTotalBean(order));

        BuyProductEMailSystemBoundary eMailSystemBoundary = new BuyProductEMailSystemBoundary() ;
        ArrayList<String> vendorsInfo = cart.getVendorsInfo();
        for (String vendor : vendorsInfo) {
            eMailSystemBoundary.notifyVendors(createNotificationInfo(vendor, order));
        }
    }

    private VendorOrderBean createNotificationInfo(String vendor, Order order) {
        //VendorOrderBean orderBean = new VendorOrderBean(vendor);
        CartBean cartRowCreator = new CartBean() ;
        ArrayList<CartRowBean> cartRowBeans = new ArrayList<>() ;

        for (Map<String,String> cartRowInfo : cart.getRowsInfoByVendor(vendor)) {
            cartRowBeans.add(cartRowCreator.createRowBean(cartRowInfo)) ;
        }

        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(order.getDate());

        Map<String,Integer> dateMap = Map.of(VENDOR_ORDER_YEAR_KEY, calendar.get(Calendar.YEAR), VENDOR_ORDER_MONTH_KEY, calendar.get(Calendar.MONTH), VENDOR_ORDER_DAY_KEY, calendar.get(Calendar.DAY_OF_MONTH)) ;
        return new VendorOrderBean(vendor, cartRowBeans, order.getAddress(), order.getTelephone(), dateMap) ;

    }

    public OrderTotalBean showOrder(){
        order.removeAllCoupon();
        return orderBean ;
    }

    public UserBean login(AccessInfoBean accessInfo) throws NotExistentUserException {
        LoginController loginController = new LoginController() ;

        UserBean userBean = loginController.verifyUser(accessInfo) ;
        customerLogin(userBean) ;

        return userBean ;
    }

    public void customerLogin(UserBean userBean) throws NotExistentUserException {
        UserDAO userDAO = new UserDAO() ;
        customer = userDAO.loadCustomerByEmail(userBean.getUserEmail()) ;
    }

}
