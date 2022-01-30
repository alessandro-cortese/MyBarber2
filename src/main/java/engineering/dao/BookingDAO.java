package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.pattern.Connector;

import java.sql.*;
import java.time.LocalDate;

public class BookingDAO {

    public boolean insertBooking(String userEmail, String nameSaloon, String[] services, Time fromTime, Time toTime, Date date) {
        boolean flag = false;
        Connection conn = Connector.getConnectorInstance().getConnection();

        try (Statement statement = conn.createStatement()){
            int i=0;
            while(i< services.length  ) {
                flag = Queries.insertBookingInfo(statement, userEmail, nameSaloon, services[i], fromTime, toTime, date);
            i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
