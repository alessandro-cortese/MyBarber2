package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.pattern.Connector;
import model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private static final String CUSTOMER_COL_EMAIL = "userEmail" ;
    private static final String CUSTOMER_COL_NAME = "name" ;
    private static final String CUSTOMER_COL_SURNAME  = "surname" ;
    private static final String CUSTOMER_COL_TELEPHONE = "telephone" ;
    private static final String CUSTOMER_COL_CREDIT_CARD = "creditCard" ;
    private static final String CUSTOMER_COL_CARD_POINTS = "cardPoints" ;

    public List<Customer> loadCustomerFromFavoriteSaloon(Integer favoriteSaloonId) {

        List<Customer> customers = new ArrayList<>();
        Connection connection = Connector.getConnectorInstance().getConnection();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = Queries.loadCustomerFromSaloon(statement, favoriteSaloonId)) {


            while(resultSet.next()) {

                customers.add(createCustomer(resultSet));

            }


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return customers;
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {

        String email = resultSet.getString(CUSTOMER_COL_EMAIL);
        String password = "";
        String name = resultSet.getString(CUSTOMER_COL_NAME);
        String surname = resultSet.getString(CUSTOMER_COL_SURNAME);
        String creditCard = resultSet.getString(CUSTOMER_COL_CREDIT_CARD);
        String phone = resultSet.getString(CUSTOMER_COL_TELEPHONE);
        Integer pointsCard = resultSet.getInt(CUSTOMER_COL_CARD_POINTS);

        return new Customer(email, password, name, surname, creditCard, phone, pointsCard);

    }






}
