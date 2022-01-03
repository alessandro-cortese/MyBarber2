package engineering.dao.queries;

import engineering.pattern.Connector;

import java.sql.*;
import java.time.LocalDate;

public class BookingDAO {
    static Connection conn;

    public BookingDAO() { //CONSTRUCTOR NO_ARGS --> prendo la connessione dal Connector (che è la singleton class)
        try {
            System.out.println("cerco di ottenere la conn");
            conn = Connector.getConnectorInstance().getConnection();
            if (conn == null)
                System.out.println("unable to create a connection with DBMS");
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public static void VerifyDateHour(Time time, LocalDate date) throws Exception { // DA COMPLETARE CHIARIRE SLOT TIME BARBIERE
        Statement stmt = null;

        try {
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if (stmt == null)
                System.out.println("unable to execute query");

            ResultSet rs = Queries.SelectAvalaibleSaloonDateANDHour(stmt, time, date);

            if (!rs.first()) { //rs empty
                Exception e = new Exception("Not found a availability booking's slotTime");
                throw e;
            }

            //riposiz. del cursore
            rs.first();
        } finally {
            // Clean-up dell'ambiente
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }


        }
    }
}
