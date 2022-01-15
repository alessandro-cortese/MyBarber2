package engineering.dao;

import engineering.pattern.Connector;
import model.buy_product.Cart;
import model.buy_product.CartRow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartDAO {

    public void saveCart(Cart cart, Integer orderKey) {

        Connection connection = Connector.getConnectorInstance().getConnection();
        String insert = "INSERT INTO ProductOrder (productId, orderId, productQuantity) VALUES(?,?,?)" ;
        try(PreparedStatement statement = connection.prepareStatement(insert) ;) {

            for (CartRow cartRow : cart.getCartRowArrayList()) {
                statement.setInt(1, cartRow.getProductIsbn());
                statement.setInt(2, orderKey);
                statement.setInt(3, cartRow.getQuantity());

                statement.addBatch();
            }

            statement.executeBatch() ;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } ;
    }
}
