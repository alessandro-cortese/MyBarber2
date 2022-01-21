package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.pattern.Connector;
import model.buy_product.Cart;
import model.buy_product.CartRow;
import model.buy_product.Product;

import java.sql.*;

public class CartDAO {

    private static final String PRODUCT_ID_LAB = "productId" ;
    private static final String ORDER_ID_LAB = "orderId" ;
    private static final String PRODUCT_QUANTITY_LAB = "productQuantity" ;

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

    public Cart loadCartByOrderCode(Integer orderCode) {

        Connection connection = Connector.getConnectorInstance().getConnection();
        Cart loadedCart = new Cart() ;
        try(
                Statement statement = connection.createStatement() ;
                ResultSet resultSet = Queries.selectCartByOrderId(statement, orderCode) ;
                ) {

            ProductDAO productDAO = new ProductDAO() ;
            while (resultSet.next()) {
                Integer productId = resultSet.getInt(PRODUCT_ID_LAB) ;
                Integer productQuantity = resultSet.getInt(PRODUCT_QUANTITY_LAB) ;

                Product product = productDAO.loadProductByIsbn2(productId) ;

                for (int i = 0 ; i < productQuantity ; i++) {
                    loadedCart.insertProduct(product);
                }
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return loadedCart ;
    }
}
