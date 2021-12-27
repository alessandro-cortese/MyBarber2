package engineering.dao.queries;

import javax.sql.StatementEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries { //qui vanno messe tutte le query per essere più compatti

    public static ResultSet selectSaloonByName(Statement stmt, String name) throws SQLException{
        String sql = "SELECT * FROM Saloon where name = '" + name + "';";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet loadAllProducts(Statement statement) throws  SQLException {
        String query = "SELECT * from Product" ;
        return  statement.executeQuery(query) ;
    }

    public static ResultSet loadProductsByName(Statement statement, String productName) throws SQLException {
        String query = String.format("SELECT * FROM Product where name = '%s'", productName) ;
        return statement.executeQuery(query) ;
    }
}
