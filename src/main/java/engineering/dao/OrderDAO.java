package engineering.dao;

import engineering.pattern.Connector;
import model.User;
import model.buy_product.Cart;
import model.buy_product.Order;
import model.buy_product.containers.CouponContainer;

import java.sql.*;
import java.time.LocalTime;

public class OrderDAO {


    public void saveOrder(Order order, Cart cart, User user) {

        CouponDAO couponDAO = new CouponDAO() ;
        CouponContainer couponContainer = order.getCouponContainer() ;
        couponDAO.invalidateAllCoupon(couponContainer);

        Connection connection = Connector.getConnectorInstance().getConnection();
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO CustomerOrder (dateOrder, orderTotal, oderAddress, orderTelephone, customer) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);) {

            statement.setDate(1, new Date(order.getDate().getTime()));
            statement.setDouble(2, order.getFinalPrice());
            statement.setString(3, order.getAddress());
            statement.setString(4, order.getTelephone());
            statement.setString(5, user.getEmail());

            statement.executeUpdate() ;
            ResultSet generatedKeys = statement.getGeneratedKeys() ;
            if (generatedKeys.next()) {
                Integer orderKey = generatedKeys.getInt(1);
                order.setOrderCode(orderKey);
            }
            //CartDAO cartDAO = new CartDAO() ;
            //cartDAO.saveCart(cart, orderKey);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
