package engineering.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries { //qui vanno messe tutte le query per essere più compatti

    public static ResultSet selectSaloonByName(Statement stmt, String name) throws SQLException{
        String sql = "SELECT * FROM Saloon where name = '" + name + "';";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
