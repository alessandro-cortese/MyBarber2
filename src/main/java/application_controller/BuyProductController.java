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
import model.buy_product.CartRow;
import model.buy_product.Order;
import model.buy_product.Product;
import model.buy_product.containers.ProductCatalog;
import model.buy_product.coupon.Coupon;
import model.buy_product.coupon.CouponApplier;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BuyProductController {

    private final CouponDAO couponDAO ;
    private final ProductCatalog productCatalog ;
    private final Cart cart ;
    private final Order order ;
    private Customer customer;
    private CartFileSaver cartFileSaver ;
    private final CouponApplier couponApplier ;


    public BuyProductController() {
        ProductDAO productDAO = new ProductDAO();
        couponDAO = new CouponDAO() ;
        productCatalog = productDAO.loadAllProducts() ;

        cart = new Cart() ;
        order = new Order() ;
        couponApplier = new CouponApplier(cart) ;
    }

    public BuyProductController(UserBean loggedUserBean) throws NotExistentUserException {
        ProductDAO productDAO = new ProductDAO();
        couponDAO = new CouponDAO() ;
        productCatalog = productDAO.loadAllProducts() ;

        customerLogin(loggedUserBean);
        cartFileSaver = new CartFileSaver(customer.getEmail()) ;
        cart = cartFileSaver.loadCartFromFile() ;
        couponApplier = new CouponApplier(cart) ;

        order = new Order() ;
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

    public CartBean insertProductToCart(ProductBean productBean) {
        Product product = productCatalog.getProductByIsbn(productBean.getBeanIsbn()) ;
        cart.insertProduct(product);
        if (customer != null) cartFileSaver.saveCartInFile(cart);

        return createCartBean() ;
    }

    public CartBean showCart() {
        return createCartBean() ;
    }

    public CartBean createCartBean() {
        List<CartRow> cartRows = cart.getCartRowArrayList() ;
        CartBean cartBean = new CartBean() ;
        cartBean.setTotal(cart.getPrice());
        List<CartRowBean> cartRowBeans = new ArrayList<>() ;
        for (CartRow cartRow : cartRows) {
            cartRowBeans.add(new CartRowBean(cartRow.getQuantity(), cartRow.getProductIsbn(), cartRow.getProductName(), cartRow.getProductPrice())) ;
        }
        cartBean.setCartRowBeanArrayList(cartRowBeans);
        return cartBean;
    }


    public CartBean removeProductFromCart(ProductBean productBean) {
        Product rmvProduct = productCatalog.getProductByIsbn(productBean.getBeanIsbn()) ;
        cart.removeProduct(rmvProduct);
        if (customer != null) cartFileSaver.saveCartInFile(cart);

        return createCartBean() ;
    }

    public OrderTotalBean applyCoupon(CouponBean couponBean) throws InvalidCouponException, NegativePriceException {
        Coupon myCoupon = couponDAO.loadCouponByCode(couponBean.getCouponCode());
        couponApplier.applyCoupon(myCoupon);
        Double cartPrice = cart.getPrice() ;
        Integer orderPoints = (int) Math.round(cartPrice) ;
        return new OrderTotalBean(couponApplier.getFinalPrice(), couponApplier.getAppliedCouponCode(), orderPoints) ;
    }

    public void completeOrder(OrderInfoBean orderInfoBean) throws NotExistentUserException {
        //IMPOSTIO INFORMAZIONI DI ORDER
        order.setAddress(orderInfoBean.getAddressInfo());
        order.setTelephone(orderInfoBean.getTelephoneInfo());
        order.setPaymentOption(orderInfoBean.getPaymentOptionInfo());
        order.setDate(LocalDate.now());
        order.setFinalPrice(couponApplier.getFinalPrice());
        order.setOrderOwner(customer.getEmail());
        order.setCartRows(cart.getCartRowArrayList());

        //SALVATAGGIO ORDER
        OrderDAO orderDAO = new OrderDAO() ;
        orderDAO.saveOrder(order);

        //INVALIDO TUTTI I COUPON UTILIZZATI
        CouponDAO couponDAO = new CouponDAO() ;
        couponDAO.invalidateAllCoupon(couponApplier.getCouponContainer());

        //Cancellazione del carrello provvisorio
        cartFileSaver.deleteCart() ;

        //AGGIORNAMENTO PUNTEGGIO CUSTOMER
        UserDAO userDAO = new UserDAO() ;
        Double cartPrice = cart.getPrice() ;
        Integer orderPoints = (int) Math.round(cartPrice) ;
        customer.setCardPoints(customer.getCardPoints() + orderPoints);
        userDAO.updateCustomerPoints(customer);

        BuyProductPaypalBoundary paypalBoundary = new BuyProductPaypalBoundary() ;
        paypalBoundary.pay(new OrderTotalBean(couponApplier.getFinalPrice()));

        BuyProductEMailSystemBoundary eMailSystemBoundary = new BuyProductEMailSystemBoundary() ;
        ArrayList<String> vendorsInfo = cart.getVendorsInfo();
        for (String vendor : vendorsInfo) {
            eMailSystemBoundary.notifyVendors(createNotificationInfo(vendor, order));
        }
    }

    private VendorOrderBean createNotificationInfo(String vendor, Order order) {
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(Date.from(Instant.now()));

        return new VendorOrderBean(vendor, order.getAddress(), order.getTelephone(), order.getDate(), order.getOrderOwner(), order.getOrderCode()) ;

    }

    public OrderTotalBean showOrder(){
        couponApplier.reset() ;
        Double cartPrice = cart.getPrice() ;
        Integer orderPoints = (int) Math.round(cartPrice) ;
        return new OrderTotalBean(couponApplier.getFinalPrice(), couponApplier.getAppliedCouponCode(), orderPoints) ;
    }

    public UserBean login(AccessInfoBean accessInfo) throws NotExistentUserException {
        LoginController loginController = new LoginController() ;

        UserBean userBean = loginController.verifyUser(accessInfo) ;
        customerLogin(userBean) ;

        cartFileSaver = new CartFileSaver(customer.getEmail()) ;
        cartFileSaver.saveCartInFile(cart);
        return userBean ;
    }

    public void customerLogin(UserBean userBean) throws NotExistentUserException {
        UserDAO userDAO = new UserDAO() ;
        customer = userDAO.loadCustomerByEmail(userBean.getUserEmail()) ;
    }

}
