package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.pattern.Connector;
import model.User;
import model.buyProduct.Product;
import model.buyProduct.containers.ProductCatalog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAO {

    private static final String ISBN_COL_NAME = "id" ;
    private static final String NAME_COL_LABEL = "name" ;
    private static final String DESCRIPTION_COL_LABEL = "description" ;
    private static final String PRICE_COL_LABEL = "price" ;
    private static final String BARBER_COL_LABEL = "barber" ;

    public ProductCatalog loadAllProducts() {
        ProductCatalog catalog = new ProductCatalog() ;
        catalog.addProduct(1,"Dopobarba","ciao a tutti", 22.0, "ciao");
        catalog.addProduct(2,"Shampoo", "Antigiallo", 11.25, "Simo");

        ArrayList<Product> products = new ArrayList<>() ;

        /*Connection connection = Connector.getConnectorInstance().getConnection();
        try (Statement statement = connection.createStatement() ;
             ResultSet resultSet = Queries.loadAllProducts(statement) ;)
        {
            if (resultSet.isFirst()) {
                do {
                    Product product = createProduct(resultSet);
                    products.add(product);
                } while (resultSet.next());
            }

        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }*/
        return catalog ;
    }

    public Product loadProductByIsbn(Integer isbn) {
        Product product = null;
        Connection connection = Connector.getConnectorInstance().getConnection();
        try (Statement statement = connection.createStatement() ;
             ResultSet resultSet = Queries.loadProductByIsbn(statement, isbn) ;)
        {
            if (resultSet.isFirst()) {
                product = createProduct(resultSet) ;
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return product ;
    }

    private Product createProduct(ResultSet resultSet) throws SQLException {
        Integer isbn = resultSet.getInt(ISBN_COL_NAME) ;
        String name = resultSet.getString(NAME_COL_LABEL) ;
        String description = resultSet.getString(DESCRIPTION_COL_LABEL) ;
        Double price = resultSet.getDouble(PRICE_COL_LABEL) ;
        String vendorEmail = resultSet.getString(BARBER_COL_LABEL) ;

        return new Product(isbn, name, description, price, vendorEmail) ;
    }
}
