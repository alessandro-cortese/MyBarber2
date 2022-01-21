package application_controller;

import engineering.bean.UserBean;
import engineering.bean.buy_product.CartRowBean;
import engineering.bean.buy_product.VendorOrderBean;
import engineering.dao.OrderDAO;
import engineering.dao.UserDAO;
import model.User;
import model.buy_product.Cart;
import model.buy_product.CartRow;
import model.buy_product.Order;

import java.util.ArrayList;
import java.util.List;

public class BarberManageOrderController {

    private User barber;

    private List<Order> orderList ;

    public BarberManageOrderController(UserBean loggedUser) {
        UserDAO userDAO = new UserDAO() ;
        //barber = userDAO.loadBarberByEmail() ;
    }

    public List<VendorOrderBean> showAllOrders() {
        OrderDAO orderDAO = new OrderDAO() ;
        orderList = orderDAO.loadAllOrdersByBarber("barber") ;

        List<VendorOrderBean> vendorOrderBeans = new ArrayList<>() ;
        for (Order order : orderList) {
            VendorOrderBean vendorOrderBean = createVendorOrderBean(order) ;
            vendorOrderBeans.add(vendorOrderBean) ;
            System.out.println("CIAOOOOOO");
        }

        return vendorOrderBeans ;
    }

    private VendorOrderBean createVendorOrderBean(Order order) {
        Cart cart = order.getOrderCart();
        List<CartRow> cartRows = cart.getCartRowArrayList() ;

        List<CartRowBean> cartRowBeans = new ArrayList<>() ;
        for (CartRow cartRow : cartRows) {
            cartRowBeans.add(new CartRowBean(cartRow.getQuantity(), cartRow.getProductIsbn(), cartRow.getProductName(), cartRow.getProductPrice())) ;
        }

        return new VendorOrderBean("barber", order.getAddress(), order.getTelephone(), order.getDate(), order.getOrderOwner(), order.getOrderCode()) ;
    }

    public List<CartRowBean> showOrderCart(VendorOrderBean vendorOrderBean) {
        for (Order order : orderList) {
            if (order.getOrderCode() == vendorOrderBean.getOrderCode()) {
                return buildCartRowBean(order) ;
            }
        }
        return new ArrayList<>() ;
    }

    private List<CartRowBean> buildCartRowBean(Order order) {
        Cart cart = order.getOrderCart() ;
        List<CartRow> cartRows = cart.getCartRowArrayList() ;

        List<CartRowBean> cartRowBeans = new ArrayList<>() ;
        for (CartRow cartRow : cartRows) {
            cartRowBeans.add(new CartRowBean(cartRow.getQuantity(), cartRow.getProductIsbn(), cartRow.getProductName(), cartRow.getProductPrice())) ;
        }
        return cartRowBeans ;
    }
}
