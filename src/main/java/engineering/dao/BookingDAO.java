package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.pattern.Connector;

import java.sql.*;
import java.time.LocalDate;

public class BookingDAO {
    private final Connection conn;

    public BookingDAO() { //CONSTRUCTOR NO_ARGS --> prendo la connessione dal Connector (che è la singleton class)
            conn = Connector.getConnectorInstance().getConnection();
    }

    /*public void verifyDateHour(Time time, LocalDate date) throws Exception { // DA COMPLETARE CHIARIRE SLOT TIME BARBIERE

        Statement stmt = null;

        try {
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if (stmt == null)
                System.out.println("unable to execute query");

            ResultSet rs = Queries.selectAvailableSaloonDateAndHour(stmt, time, date);

            if (!rs.first()) { //rs empty
                throw new Exception("Not found a availability booking's slotTime");
            }

            //riposiz. del cursore
            rs.first();
        } finally {
            // Clean-up dell'ambiente
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }

        }
    }*/
}
