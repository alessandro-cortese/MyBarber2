package engineering.dao;

import engineering.pattern.Connector;
import model.User;
import model.buyProduct.Order;
import model.buyProduct.containers.CouponContainer;

import java.sql.*;
import java.time.LocalTime;

public class OrderDAO {

    private static final String INSERT_ORDER_STATEMENT = "INSERT INTO CustomerOrder (hourOrder, dateOrder, customerEmail, idProduct) VALUES (?,?,?,?)" ;

    public void saveOrder(Order order, User user) {
        CouponDAO couponDAO = new CouponDAO() ;
        CouponContainer couponContainer = order.getCouponContainer() ;
        couponDAO.invalidateAllCoupon(couponContainer);

        Connection connection = Connector.getConnectorInstance().getConnection();
        try(PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_STATEMENT);) {
            statement.setTime(1, Time.valueOf(LocalTime.now()));
            statement.setDate(2, new Date(order.getDate().getTime()));
            statement.setString(3, user.getEmail());
            statement.setInt(4, 0);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
