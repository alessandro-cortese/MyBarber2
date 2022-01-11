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

    public static ResultSet loadSaloonId(Statement statement, String barberEmail) throws SQLException {
        String query = String.format("SELECT * FROM Saloon WHERE barber = '%s';", barberEmail);
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

    public static ResultSet loadProductByName(Statement statement, String name, String barberEmail) throws SQLException {
        String query = String.format("SELECT * FROM Product where name = '%s' AND barber = '%s';", name, barberEmail) ;
        return statement.executeQuery(query) ;
    }


    //-+-+-+-+-+-+-+-+-+-+- Coupon -+-+-+-+-+-+-+-+-+-+-//
    public static ResultSet selectCouponByCode(Statement statement, Integer couponCode) throws SQLException {
        String query = String.format("SELECT * FROM Coupon C where C.id = %d ;", couponCode) ;
        return statement.executeQuery(query) ;
    }

    public static void deleteCoupon(Statement statement, Integer couponCode) throws SQLException {
        String delete = String.format("DELETE FROM Coupon WHERE id = %d ;", couponCode) ;
        statement.execute(delete) ;
    }

    public static ResultSet selectCouponsByOwner(Statement statement, String userEmail) throws SQLException {
        String query = String.format("SELECT * FROM Coupon WHERE customer = '%s' ;", userEmail) ;
        return statement.executeQuery(query) ;
    }



    //-+-+-+-+-+-+-+-+-+-+- Order -+-+-+-+-+-+-+-+-+-+-//
    public static void insertNewOrder(Statement statement, Order order, User user) {

    }


    //-+-+-+-+-+-+-+-+-+-+- SaloonTime -+-+-+-+-+-+-+-+-+-+-//
    public  static ResultSet selectAvailableSaloonDateAndHour(Statement statement, Time time, LocalDate date) throws SQLException {
        String select = String.format("SELECT FROM SaloonTime where hourBooking = '%s' AND dateBooking ='%s';", time,date);
        return statement.executeQuery(select);

    }// DA fare insieme ad alessandro per chiarire

    public static ResultSet selectSlotTimeSaloon(Statement stmt, String saloonName) throws SQLException {
        String query = String.format("SELECT openMorningTime,openAfternoonTime,closeMorningTime,closeAfternoonTime,intervalSlotTime FROM Saloon join SaloonTime on id.Saloon = Saloon.SaloonTime WHERE name.Saloon = '%s';", saloonName);
        return  stmt.executeQuery(query);
    }


    public static ResultSet selectSaloonByCity(Statement stmt, String saloonCity) throws SQLException {
        String sql = "SELECT * FROM Saloon where city = '" + saloonCity + "';";
        return stmt.executeQuery(sql);
    }


    //-+-+-+-+-+-+-+-+-+-+- Service -+-+-+-+-+-+-+-+-+-+-//
    public static ResultSet loadServiceByName(Statement statement, String serviceName) throws SQLException {
        String query = String.format("SELECT * FROM Service WHERE name = '%s';", serviceName);
        return statement.executeQuery(query);
    }

    public static int saveService(Statement statement, String barberEmail, String serviceName, String serviceDescription, Double price) throws SQLException {
        String update = String.format("INSERT INTO Service(name, description, price, barber) VALUES('%s', '%s', %d, '%s');", serviceName, serviceDescription, price, barberEmail);
        return statement.executeUpdate(update);
    }

    public static ResultSet loadServicesByBarberEmail(Statement statement, String barberEmail) throws SQLException {
        String query = String.format("SELECT * FROM Service WHERE barber = '%s';", barberEmail);
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

    //-+-+-+-+-+-+-+-+-+-+- Customer -+-+-+-+-+-+-+-+-+-+-//

    public static  ResultSet loadCustomerFromSaloon(Statement statement, Integer favoriteSaloonId) throws SQLException {
        String query = String.format("SELECT C.* FROM Customer C JOIN favorite F ON C.userEmail = F.customer WHERE F.saloon = '%d';", favoriteSaloonId);
        return statement.executeQuery(query);
    }

}
