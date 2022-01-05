package application_controller;

import boundary.buy_product.BuyProductEMailSystemBoundary;
import boundary.buy_product.BuyProductPaypalBoundary;
import engineering.bean.buy_product.*;
import engineering.dao.CouponDAO;
import engineering.dao.OrderDAO;
import engineering.dao.ProductDAO;
import engineering.exception.InvalidCouponException;
import model.User;
import model.buy_product.Cart;
import model.buy_product.Coupon;
import model.buy_product.Order;
import model.buy_product.Product;
import model.buy_product.containers.ProductCatalog;

import java.util.ArrayList;
import java.util.Map;

public class BuyProductController {

    private ProductDAO productDAO ;
    private CouponDAO couponDAO ;

    private ProductCatalog productCatalog ;
    private Cart cart ;
    private Order order ;

    private CartBean cartBean ;

    private User user ;

    public BuyProductController() {
        productDAO = new ProductDAO() ;
        couponDAO = new CouponDAO() ;
        productCatalog = productDAO.loadAllProducts() ;
        cart = new Cart() ;
        order = new Order(cart) ;
    }

    public ArrayList<ProductBean> filterProductList(ProductSearchInfoBean searchInfoBean) {
        String searchName = searchInfoBean.getProductName() ;
        ArrayList<Product> productArrayList = productCatalog.filterByName(searchName) ;
        return createArrayProductBean(productArrayList) ;
    }

    private ArrayList<ProductBean> createArrayProductBean(ArrayList<Product> productArrayList) {
        ArrayList<ProductBean> productBeanArrayList = new ArrayList<>() ;
        for (Product product : productArrayList) {
            ProductBean productBean = new ProductBean(product.getIsbn(), product.getName(), product.getDescription(), product.getPrice()) ;
            productBeanArrayList.add(productBean) ;
        }
        return productBeanArrayList ;
    }

    public void insertProductToCart(ProductBean productBean) {
        Product product = productCatalog.getProductByIsbn(productBean.getIsbn()) ;
        cart.insertProduct(product);
    }

    public CartBean showCart() {
        cartBean = new CartBean(cart);
        return cartBean ;
    }

    public void removeProductFromCart(ProductBean productBean) {
        Product rmvProduct = productCatalog.getProductByIsbn(productBean.getIsbn()) ;
        cart.removeProduct(rmvProduct);
    }

    public void applyCoupon(CouponBean couponBean) throws InvalidCouponException {

        Coupon myCoupon = couponDAO.loadCouponByCode(couponBean.getCouponCode(), user);
        order.addCoupon(myCoupon);

    }

    public void completeOrder(OrderInfoBean orderInfoBean) {
        order.setAddress(orderInfoBean.getAddress());
        order.setTelephone(orderInfoBean.getTelephone());
        order.setPaymentOption(orderInfoBean.getPaymentOption());
        order.setDate(orderInfoBean.getDate());

        OrderDAO orderDAO = new OrderDAO() ;
        //orderDAO.saveOrder(order, user);

        BuyProductPaypalBoundary paypalBoundary = new BuyProductPaypalBoundary() ;
        paypalBoundary.pay(new OrderTotalBean(order));

        BuyProductEMailSystemBoundary eMailSystemBoundary = new BuyProductEMailSystemBoundary() ;
        ArrayList<String> vendorsInfo = cart.getVendorsInfo();
        for (String vendor : vendorsInfo) {
            eMailSystemBoundary.notifyVendors(createNotificationInfo(vendor, orderInfoBean));
        }
    }

    private VendorOrderBean createNotificationInfo(String vendor, OrderInfoBean orderInfoBean) {
        //VendorOrderBean orderBean = new VendorOrderBean(vendor);
        CartBean cartBean = new CartBean() ;
        ArrayList<CartRowBean> cartRowBeans = new ArrayList<>() ;

        for (Map<String,String> cartRowInfo : cart.getRowsInfoByVendor(vendor)) {
            cartRowBeans.add(cartBean.createRowBean(cartRowInfo)) ;
        }
        return new VendorOrderBean(vendor, cartRowBeans, orderInfoBean.getAddress(), orderInfoBean.getTelephone(), orderInfoBean.getDate()) ;

    }

    public OrderTotalBean showOrder(){
        order = new Order(cart) ;
        return new OrderTotalBean(order) ;
    }
}