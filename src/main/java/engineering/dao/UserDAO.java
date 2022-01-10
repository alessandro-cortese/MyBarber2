package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.pattern.Connector;
import model.Barber;
import model.Customer;
import model.User;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    private static final String EMAIL_COL_LABEL = "email" ;
    private static final String PASSWORD_COL_LABEL = "pass" ;
    private static final String NAME_COL_LABEL = "name" ;
    private static final String SURNAME_COL_LABEL = "surname" ;

    private static final String BARBER_EMAIL_COL_LABEL = "emailUser" ;
    private static final String CUSTOMER_EMAIL_COL_LABEL = "userEmail" ;
    private static final String CUSTOMER_POINTS_COL_LABEL = "cardPoints" ;

    private static final String BARBER_ENUM_TYPE = "barber" ;
    private static final String CUSTOMER_ENUM_TYPE = "customer" ;
    private static final String USER_TYPE = "type" ;

    private static final Integer CUSTOMER_TYPE = 0 ;
    private static final Integer BARBER_TYPE = 1 ;


    public Integer loadUserByCredentials(String userEmail, String userPassword) {
        Connection connection = Connector.getConnectorInstance().getConnection();

        Integer userType = -1 ;
        try(Statement statement = connection.createStatement() ;
            ResultSet resultSet = Queries.selectUserByCredentials(statement, userEmail, userPassword) ;
            ) {

            if (resultSet.next()) {
                String userEnum = resultSet.getString(USER_TYPE) ;
                if (userEnum.compareTo(BARBER_ENUM_TYPE) == 0) {
                    userType = BARBER_TYPE ;
                }
                else if (userEnum.compareTo(CUSTOMER_ENUM_TYPE) == 0) {
                    userType = CUSTOMER_TYPE ;
                }
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return userType ;
    }

    /*private User loadUser(String userEmail) throws SQLException {
        Connection connection = Connector.getConnectorInstance().getConnection();

        User user = null ;
        try (
                Statement barberStatement = connection.createStatement() ;
                Statement customerStatement = connection.createStatement() ;
                ResultSet barberResultSet = Queries.selectBarberByEmail(barberStatement, userEmail) ;
                ResultSet customerResultSet = Queries.selectCustomerByEmail(customerStatement, userEmail) ;
            ) {

            if (barberResultSet.isFirst()) {
                user = createBarber(barberResultSet) ;
            }
            else if (customerResultSet.isFirst()) {
                user = createCustomer(customerResultSet) ;
            }
        }

        return user ;
    }


    private Barber createBarber(ResultSet barberResultSet) throws SQLException {
        String barberEmail = barberResultSet.getString(BARBER_EMAIL_COL_LABEL) ;
        String barberName = barberResultSet.getString(NAME_COL_LABEL) ;
        String barberSurname = barberResultSet.getString(SURNAME_COL_LABEL) ;

        return new Barber(barberEmail, null, barberName, barberSurname) ;
    }

     */

    private Customer createCustomer(ResultSet customerResultSet) throws SQLException {
        String customerEmail = customerResultSet.getString(CUSTOMER_EMAIL_COL_LABEL) ;
        String customerName = customerResultSet.getString(NAME_COL_LABEL) ;
        String customerSurname = customerResultSet.getString(SURNAME_COL_LABEL) ;
        Integer customerPoints = customerResultSet.getInt(CUSTOMER_POINTS_COL_LABEL) ;

        return new Customer(customerEmail, null, customerName, customerSurname, customerPoints) ;
    }

    public Customer loadCustomerByEmail(String userEmail){
        Connection connection = Connector.getConnectorInstance().getConnection();
        Customer customer = null ;
        try(Statement statement = connection.createStatement() ;
            ResultSet resultSet = Queries.selectCustomerByEmail(statement, userEmail)
        )
        {
            while (resultSet.next()) {
                customer = createCustomer(resultSet) ;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return customer ;
    }
}
