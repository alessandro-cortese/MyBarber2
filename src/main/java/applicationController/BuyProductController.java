package applicationController;

import engineering.bean.buyProduct.*;
import engineering.dao.CouponDAO;
import engineering.dao.ProductDAO;
import model.buyProduct.Cart;
import model.buyProduct.Coupon;
import model.buyProduct.Order;
import model.buyProduct.Product;
import model.buyProduct.containers.ProductCatalog;

import java.util.ArrayList;

public class BuyProductController {

    private ProductDAO productDAO ;
    private CouponDAO couponDAO ;
    private ProductCatalog productCatalog ;
    private Cart cart ;
    private Order order ;

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
        cart.insertProduct(productCatalog, productBean.getIsbn());
    }

    public CartBean showCart() {
        return new CartBean(cart);
    }

    public void removeProductFromCart(ProductBean productBean) {
        cart.removeProduct(productBean.getIsbn());
    }

    public void applyCoupon(CouponBean couponBean) {
        Coupon myCoupon = couponDAO.loadCouponByCode(couponBean.getCouponCode());
        if (myCoupon != null) {
            order.addCoupon(myCoupon);
        }
    }

    public void completeOrder(OrderInfoBean orderInfoBean) {}

    public OrderTotalBean showOrder(){
        order = new Order(cart) ;
        return new OrderTotalBean(order) ;
    }
}
