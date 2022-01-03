package engineering.dao.queries;

import model.User;
import model.buyProduct.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;

public class Queries { //qui vanno messe tutte le query per essere più compatti

    //-+-+-+-+-+-+-+-+-+-+- Saloon -+-+-+-+-+-+-+-+-+-+-//
    public static ResultSet selectSaloonByName(Statement stmt, String name) throws SQLException{
        String sql = "SELECT * FROM Saloon where name = '" + name + "';";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }


    //-+-+-+-+-+-+-+-+-+-+- Product -+-+-+-+-+-+-+-+-+-+-//
    public static ResultSet loadAllProducts(Statement statement) throws  SQLException {
        String query = "SELECT * from Product" ;
        return  statement.executeQuery(query) ;
    }

    public static ResultSet loadProductsByName(Statement statement, String productName) throws SQLException {
        String query = String.format("SELECT * FROM Product where name = '%s'", productName) ;
        return statement.executeQuery(query) ;
    }

    public static ResultSet loadProductByIsbn(Statement statement, Integer isbn) throws SQLException {
        String query = String.format("SELECT * FROM Product where id = %d", isbn) ;
        return statement.executeQuery(query) ;
    }


    //-+-+-+-+-+-+-+-+-+-+- Coupon -+-+-+-+-+-+-+-+-+-+-//
    public static ResultSet loadCouponByCode(Statement statement, String couponCode, String userEmail) throws SQLException {
        String query = String.format("SELECT * FROM Coupon C where C.name = '%s' AND C.customer = '%s'", couponCode, userEmail) ;
        return statement.executeQuery(query) ;
    }

    public static void deleteCoupon(Statement statement, String couponCode, String email) throws SQLException {
        String delete = String.format("DELETE Coupon where name = '%s' AND customer = '%s'", couponCode, email) ;
        statement.execute(delete) ;
    }


    //-+-+-+-+-+-+-+-+-+-+- Order -+-+-+-+-+-+-+-+-+-+-//
    public static void insertNewOrder(Statement statement, Order order, User user) {


    }
    //-+-+-+-+-+-+-+-+-+-+- SaloonTime -+-+-+-+-+-+-+-+-+-+-//
    public  static ResultSet SelectAvalaibleSaloonDateANDHour(Statement statement, Time time, LocalDate date) throws SQLException {
        String select = String.format("SELECT FROM SaloonTime where hourBooking = '%s' AND dateBooking ='%s'", time,date);
        return statement.executeQuery(select);
    }// DA fare insieme ad alessandro per chiarire
}
