package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.pattern.Connector;
import model.Saloon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SaloonDAO {
    static Connection conn;

    public SaloonDAO(){ //CONSTRUCTOR NO_ARGS --> prendo la connessione dal Connector (che è la singleton class)
        try {
            System.out.println("cerco di ottenere la conn");
            conn = Connector.getConnectorInstance().getConnection();
            if (conn == null)
                System.out.println("unable to create a connection with DBMS");
        }
        catch (Exception se){
            se.printStackTrace();
        }
    }

    public static List<Saloon> retreiveByCityName(String SaloonCity) throws Exception{
        Statement stmt = null;
        List<Saloon> listOfSaloon = new ArrayList<Saloon>();

        try {
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            if (stmt==null)
                System.out.println("unable to execute query");

            ResultSet rs = Queries.selectSaloonByCity(stmt,SaloonCity);

            if (!rs.first()) { //rs empty
                Exception e = new Exception("No Saloon found matching with city" + SaloonCity);
                throw e;
            }

            //riposiz. del cursore
            rs.first();
            do {
                // lettura delle colonne "by city"
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("telephone");
                Time slotTime = rs.getTime("slotTime");
                int seatNumber = rs.getInt("seatNumber");

                Saloon s = new Saloon(SaloonCity, name,address, phone, slotTime, seatNumber);
                listOfSaloon.add(s);
            }while (rs.next());
            //chiudo rs
            rs.close();
        }
        finally {
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
        return listOfSaloon;
    }


    public static Saloon retreiveByNameSaloon(String SaloonName) throws Exception {
        Statement stmt = null;
        Saloon saloon;

        try {
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            if (stmt==null)
                System.out.println("unable to execute query");

            ResultSet rs = Queries.selectSaloonByCity(stmt,SaloonName);

            if (!rs.first()) { //rs empty
                Exception e = new Exception("No Saloon found matching with city" + SaloonName);
                throw e;
            }

            //riposiz. del cursore
            rs.first();
            do {
                // lettura delle colonne "by name"
                String city = rs.getString("city");
                String address = rs.getString("address");
                String phone = rs.getString("telephone");
                Time slotTime = rs.getTime("slotTime");
                int seatNumber = rs.getInt("seatNumber");

               saloon = new Saloon(city, SaloonName,address, phone, slotTime, seatNumber);
            }while (rs.next());
            //chiudo rs
            rs.close();
        }
        finally {
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
        return saloon;
    }

}
