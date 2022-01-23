package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.exception.ProductNotFoundException;
import engineering.pattern.Connector;
import model.buy_product.Product;
import model.buy_product.containers.ProductCatalog;
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
        ArrayList<Product> products = new ArrayList<>() ;

        Connection connection = Connector.getConnectorInstance().getConnection();
        try (Statement statement = connection.createStatement() ;
             ResultSet resultSet = Queries.loadAllProducts(statement) ;)
        {

            while (resultSet.next()) {
                Product product = createProduct(resultSet);
                products.add(product);
            }

        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return new ProductCatalog(products) ;
    }

    public Product loadProductByName(String name, String barberEmail) throws ProductNotFoundException{
        Product product = null;
        Connection connection = Connector.getConnectorInstance().getConnection();
        try (Statement statement = connection.createStatement() ;
             ResultSet resultSet = Queries.loadProductByName(statement, name, barberEmail) )
        {
            resultSet.next();
            if (resultSet.isFirst()) {
                product = createProduct(resultSet) ;
            }
            else{
                throw new ProductNotFoundException();
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return product ;
    }

    public int loadIsbnOfUsedProduct(Integer serviceId) throws SQLException{

        int productId = -1;
        Connection connection = Connector.getConnectorInstance().getConnection();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = Queries.loadProductIdFromServiceProduct(statement, serviceId)){

            resultSet.next();
            if(resultSet.first()){
                productId = resultSet.getInt(1);
            }

        }
        return productId;

    }

    public Product loadProductByIsbn(int productIsbn) throws SQLException{

        Product product = null;
        Connection connection = Connector.getConnectorInstance().getConnection();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = Queries.loadProductByIsbn(statement, productIsbn)){


            if(resultSet.next()){
                product = createProduct(resultSet);
            }

        }

        return product;

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
