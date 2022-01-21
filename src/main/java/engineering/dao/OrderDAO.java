package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.pattern.Connector;
import model.User;
import model.buy_product.Cart;
import model.buy_product.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private static final String ORDER_ID_LAB = "id" ;
    private static final String ORDER_DATE_LAB = "orderDate" ;
    private static final String ORDER_CUSTOMER_LAB = "customer" ;
    private static final String ORDER_TOTAL_LAB = "orderTotal" ;
    private static final String ORDER_ADDRESS_LAB = "orderAddress" ;
    private static final String ORDER_TELEPHONE_LAB = "orderTelephone" ;


    public void saveOrder(Order order) {

        Connection connection = Connector.getConnectorInstance().getConnection();
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO CustomerOrder (orderDate, orderTotal, orderAddress, orderTelephone, customer) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);) {

            statement.setDate(1, Date.valueOf(order.getDate()));
            statement.setDouble(2, order.getFinalPrice());
            statement.setString(3, order.getAddress());
            statement.setString(4, order.getTelephone());
            statement.setString(5, order.getOrderOwner());

            statement.executeUpdate() ;
            ResultSet generatedKeys = statement.getGeneratedKeys() ;
            if (generatedKeys.next()) {
                Integer orderKey = generatedKeys.getInt(1);
                order.setOrderCode(orderKey);
            }

            //Salvataggio del Carrello(varie righe di prodotti) relativo a questo ordine
            CartDAO cartDAO = new CartDAO() ;
            cartDAO.saveCart(order.getOrderCart(), order.getOrderCode());

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public List<Order> loadAllOrdersByBarber(String vendor) {
        Connection connection = Connector.getConnectorInstance().getConnection();
        List<Order> orderList = new ArrayList<>() ;

        try(
                Statement statement = connection.createStatement() ;
                ResultSet resultSet = Queries.selectOrderByVendor(statement, vendor) ;
                ) {

            CartDAO cartDAO = new CartDAO() ;
            while (resultSet.next()) {
                Order order = createOrder(resultSet) ;
                Cart cart = cartDAO.loadCartByOrderCode(order.getOrderCode()) ;
                order.setOrderCart(cart);
                orderList.add(order) ;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return orderList ;
    }


    private Order createOrder(ResultSet resultSet) throws SQLException {
        Integer orderCode = resultSet.getInt(ORDER_ID_LAB) ;
        String orderAddress = resultSet.getString(ORDER_ADDRESS_LAB) ;
        String orderTelephone = resultSet.getString(ORDER_TELEPHONE_LAB) ;
        String orderOwner = resultSet.getString(ORDER_CUSTOMER_LAB) ;
        LocalDate orderDate = resultSet.getDate(ORDER_DATE_LAB).toLocalDate() ;

       return new Order(orderCode, orderAddress, orderTelephone, orderOwner, orderDate) ;
    }
}
