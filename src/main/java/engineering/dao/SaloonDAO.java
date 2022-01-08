package engineering.dao;

import engineering.container.SaloonCatalogue;
import engineering.dao.queries.Queries;
import engineering.other_classes.ConvertTime;
import engineering.pattern.Connector;
import model.Saloon;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class SaloonDAO {

    private final static String SALOON_NAME_ID = "id" ;
    private static final String SALOON_NAME_COL = "name" ;
    private static final String SALOON_ADDRESS_COL = "address" ;
    private static final String SALOON_CITY_COL = "city" ;
    private static final String SALOON_TELEPHONE_COL = "telephone" ;
    private static final String SALOON_INTERVAL_SLOT_TIME_COL = "intervalSlotTime" ;
    private static final String SALOON_SEAT_NUMBER_COL = "seatNumber" ;
    private static final String SALOON_NUMBER_SLOT_TIME_MORNING_COL = "numberSlotTimeMorning" ;
    private static final String SALOON_NUMBER_SLOT_TIME_AFTERNOON_COL = "numberSlotTimeAfternoon" ;
    private final static String SALOON_OPENING_MORNING_TIME_COL = "openingMorningTime" ;
    private final static String SALOON_OPENING_AFTERNOON_TIME_COL = "openingAfternoonTime" ;
    private final static String SALOON_CLOSE_MORNING_TIME_COL = "closeMorningTime" ;
    private final static String SALOON_CLOSE_AFTERNOON_TIME = "closeAfternoonTime" ;


    private String barberEmail;
    private Integer idSaloon;


    public SaloonDAO(){

    }

    public  void setBarberEmail(String barberEmail) {
        this.barberEmail = barberEmail;
    }

    public SaloonCatalogue loadAllSaloon() {

        Saloon saloon;
        SaloonCatalogue saloonCatalogue = new SaloonCatalogue();
        ArrayList<Saloon> saloons = new ArrayList<Saloon>();
        LocalTime[][] localTimes = new LocalTime[2][2];

        Connection connection = Connector.getConnectorInstance().getConnection();

        saloon = loadSaloon(connection);

        saloonCatalogue.setSaloonList(saloons);

        return saloonCatalogue;
    }

    private Saloon loadSaloon(Connection connection) {

        LocalTime[][] localTimes = new LocalTime[2][2];
        Saloon saloon = null;

        try (Statement saloonStatement = connection.createStatement();
             ResultSet saloonResultSet = Queries.loadAllSaloon(saloonStatement, barberEmail)) {

            while (saloonResultSet.next()) {


                saloon = createSaloon(saloonResultSet);


                try (Statement timeStatements = connection.createStatement();
                     ResultSet timeResultSet = Queries.loadTimesOfSaloon(timeStatements, idSaloon)) {

                    localTimes = createTimeSchedule(timeResultSet);


                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }

                saloon.setTimeSchedule(localTimes);

            }





        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }

        return saloon;
    }


    private Saloon loadSaloonByName(Connection connection, String city) {

        LocalTime[][] localTimes = new LocalTime[2][2];
        Saloon saloon = null;

        try (Statement saloonStatement = connection.createStatement();
             ResultSet resultSet = Queries.selectSaloonByCity(saloonStatement, city)) {

            while (resultSet.next()) {


                saloon = createSaloon(resultSet);


                try (Statement timeStatements = connection.createStatement();
                     ResultSet timeResultSet = Queries.loadTimesOfSaloon(timeStatements, idSaloon)) {

                    localTimes = createTimeSchedule(timeResultSet);


                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }

                saloon.setTimeSchedule(localTimes);

            }


        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }

        return saloon;



    }

    private LocalTime[][] createTimeSchedule(ResultSet resultSet) throws SQLException {

        LocalTime[][] localTimes = new LocalTime[2][2];

        localTimes[0][0] = LocalTime.of(0,ConvertTime.convertTime(resultSet.getTime(SALOON_OPENING_MORNING_TIME_COL).toString()));
        localTimes[0][1] = LocalTime.of(0, ConvertTime.convertTime(resultSet.getTime(SALOON_CLOSE_MORNING_TIME_COL).toString()));
        localTimes[1][0] = LocalTime.of(0, ConvertTime.convertTime(resultSet.getTime(SALOON_OPENING_AFTERNOON_TIME_COL).toString()));
        localTimes[1][1] = LocalTime.of(0, ConvertTime.convertTime(resultSet.getTime(SALOON_CLOSE_AFTERNOON_TIME).toString()));

        return localTimes;
    }

    private Saloon createSaloon(ResultSet resultSet) throws SQLException {

        Integer[] numberOfSlots = new Integer[2];
        this.idSaloon = resultSet.getInt(SALOON_NAME_ID);
        String name = resultSet.getString(SALOON_NAME_COL);
        String address = resultSet.getString(SALOON_ADDRESS_COL);
        String city = resultSet.getString(SALOON_CITY_COL);
        String telephone = resultSet.getString(SALOON_TELEPHONE_COL);
        Time intervalSlotTime = resultSet.getTime(SALOON_INTERVAL_SLOT_TIME_COL);
        int seatsNumber = resultSet.getInt(SALOON_SEAT_NUMBER_COL);
        numberOfSlots[0] = resultSet.getInt(SALOON_NUMBER_SLOT_TIME_MORNING_COL) ;
        numberOfSlots[1] = resultSet.getInt(SALOON_NUMBER_SLOT_TIME_AFTERNOON_COL) ;

        return new Saloon(name, address, city, telephone, intervalSlotTime, seatsNumber, numberOfSlots);
    }


    /*
    public List<Saloon> retrieveByCityName(String saloonCity){

        List<Saloon> saloonList = new ArrayList<Saloon>();



        return saloonList;
    }

    */


    public static List<Saloon> retrieveByCityName(String SaloonCity) throws Exception{
        Statement stmt = null;
        Connection connection = Connector.getConnectorInstance().getConnection();

        List<Saloon> listOfSaloon = new ArrayList<Saloon>();

        try {
            //creazione ed esecuzione della query
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
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
                se2.printStackTrace();
            }

        }
        return listOfSaloon;
    }


    public Saloon retrieveByNameSaloon(String SaloonName) throws Exception {
        Statement stmt = null;
        Saloon saloon;
        Connection connection = Connector.getConnectorInstance().getConnection();

        try {
            //creazione ed esecuzione della query
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
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
                se2.printStackTrace();
            }
        }
        return saloon;
    }


}