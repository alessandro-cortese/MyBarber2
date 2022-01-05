package engineering.dao;

import engineering.pattern.Connector;
import model.User;
import model.buy_product.Cart;
import model.buy_product.Order;
import model.buy_product.containers.CouponContainer;

import java.sql.*;
import java.time.LocalTime;

public class OrderDAO {

    private static final String INSERT_ORDER_STATEMENT = "INSERT INTO CustomerOrder (hourOrder, dateOrder, customerEmail, idProduct) VALUES (?,?,?,?)" ;

    public Integer saveOrder(Order order, Cart cart, User user) {
        CouponDAO couponDAO = new CouponDAO() ;
        CouponContainer couponContainer = order.getCouponContainer() ;
        couponDAO.invalidateAllCoupon(couponContainer);

        Integer orderKey = -1 ;

        Connection connection = Connector.getConnectorInstance().getConnection();
        try(PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_STATEMENT, Statement.RETURN_GENERATED_KEYS);) {
            statement.setTime(1, Time.valueOf(LocalTime.now()));
            statement.setDate(2, new Date(order.getDate().getTime()));
            statement.setString(3, user.getEmail());
            statement.setInt(4, 0);

            //statement.execute() ;
            //orderKey = statement.getGeneratedKeys() ;
            CartDAO cartDAO = new CartDAO() ;
            cartDAO.saveCart(cart, orderKey);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return orderKey ;
    }
}
