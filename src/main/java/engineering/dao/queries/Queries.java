package engineering.dao.queries;

import model.User;
import model.buy_product.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;

public class Queries { //qui vanno messe tutte le query per essere più compatti

    private Queries() {

    }


    //-+-+-+-+-+-+-+-+-+-+- Saloon -+-+-+-+-+-+-+-+-+-+-//
    public static ResultSet selectSaloonByName(Statement stmt, String name) throws SQLException{
        String sql = "SELECT * FROM Saloon where name = '" + name + "';";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet loadAllSaloon(Statement statement, String barberEmail) throws SQLException {
        String query = String.format("SELECT * FROM Saloon WHERE barber = '%s';", barberEmail);
        return statement.executeQuery(query);
    }

    public static ResultSet loadTimesOfSaloon(Statement statement, Integer saloonID) throws  SQLException {
        String query = String.format("SELECT * FROM SaloonTime WHERE Saloon = '%d';", saloonID);
        return statement.executeQuery(query);
    }

    public static ResultSet loadSaloonByName(Statement statement, String city) throws  SQLException {
        String query = String.format("SELECT * FROM Saloon WHERE city = '%s';", city);
        return statement.executeQuery(query);
    }


    //-+-+-+-+-+-+-+-+-+-+- Product -+-+-+-+-+-+-+-+-+-+-//
    public static ResultSet loadAllProducts(Statement statement) throws  SQLException {
        String query = "SELECT * from Product" ;
        return  statement.executeQuery(query) ;
    }

    public static ResultSet loadProductsByName(Statement statement, String productName) throws SQLException {
        String query = String.format("SELECT * FROM Product where name = '%s';", productName) ;
        return statement.executeQuery(query) ;
    }

    public static ResultSet loadProductByName(Statement statement, String name) throws SQLException {
        String query = String.format("SELECT * FROM Product where name = '%s';", name) ;
        return statement.executeQuery(query) ;
    }


    //-+-+-+-+-+-+-+-+-+-+- Coupon -+-+-+-+-+-+-+-+-+-+-//
    public static ResultSet loadCouponByCode(Statement statement, String couponCode, String userEmail) throws SQLException {
        String query = String.format("SELECT * FROM Coupon C where C.name = '%s' AND C.customer = '%s';", couponCode, userEmail) ;
        return statement.executeQuery(query) ;
    }

    public static void deleteCoupon(Statement statement, String couponCode) throws SQLException {
        //String delete = String.format("DELETE Coupon where name = '%s' AND customer = '%s';", couponCode, email) ;
        //statement.execute(delete) ;
        String delete = String.format("DELETE FROM Coupon WHERE name = '%s';", couponCode) ;
        statement.execute(delete) ;
    }


    //-+-+-+-+-+-+-+-+-+-+- Order -+-+-+-+-+-+-+-+-+-+-//
    public static void insertNewOrder(Statement statement, Order order, User user) {

    }


    //-+-+-+-+-+-+-+-+-+-+- SaloonTime -+-+-+-+-+-+-+-+-+-+-//
    public  static ResultSet SelectAvalaibleSaloonDateANDHour(Statement statement, Time time, LocalDate date) throws SQLException {
        String select = String.format("SELECT FROM SaloonTime where hourBooking = '%s' AND dateBooking ='%s';", time,date);
        return statement.executeQuery(select);
    }// DA fare insieme ad alessandro per chiarire


    public static ResultSet selectSaloonByCity(Statement stmt, String saloonCity) throws SQLException {
        String sql = "SELECT * FROM Saloon where city = '" + saloonCity + "';";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }


    //-+-+-+-+-+-+-+-+-+-+- Service -+-+-+-+-+-+-+-+-+-+-//
    public static ResultSet loadServiceByName(Statement statement, String serviceName) throws SQLException {
        String query = String.format("SELECT * FROM Service where name = '%s';", serviceName);
        return statement.executeQuery(query);
    }


    //-+-+-+-+-+-+-+-+-+-+- User -+-+-+-+-+-+-+-+-+-+-//
    public static ResultSet selectUserByCredentials(Statement statement, String userEmail, String password) throws SQLException {
        String query = String.format("SELECT * FROM User WHERE email = '%s' AND pass = '%s' ;", userEmail, password) ;
        return statement.executeQuery(query) ;
    }

    public static ResultSet selectBarberByEmail(Statement statement, String barberEmail) throws SQLException {
        String query = String.format("SELECT * FROM Barber where emailUser = '%s' ;", barberEmail) ;
        return statement.executeQuery(query) ;
    }

    public static ResultSet selectCustomerByEmail(Statement statement, String customerEmail) throws SQLException {
        String query = String.format("SELECT * FROM Customer where userEmail = '%s' ;", customerEmail) ;
        return statement.executeQuery(query) ;
    }
}
