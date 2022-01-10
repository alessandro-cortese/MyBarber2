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

    private static final String SALOON_NAME_ID = "id" ;
    private static final String SALOON_NAME_COL = "name" ;
    private static final String SALOON_ADDRESS_COL = "address" ;
    private static final String SALOON_CITY_COL = "city" ;
    private static final String SALOON_TELEPHONE_COL = "telephone" ;
    private static final String SALOON_INTERVAL_SLOT_TIME_COL = "intervalSlotTime" ;
    private static final String SALOON_SEAT_NUMBER_COL = "seatNumber" ;
    private static final String SALOON_NUMBER_SLOT_TIME_MORNING_COL = "numberSlotTimeMorning" ;
    private static final String SALOON_NUMBER_SLOT_TIME_AFTERNOON_COL = "numberSlotTimeAfternoon" ;
    private static final String SALOON_OPENING_MORNING_TIME_COL = "openingMorningTime" ;
    private static final String SALOON_OPENING_AFTERNOON_TIME_COL = "openingAfternoonTime" ;
    private static final String SALOON_CLOSE_MORNING_TIME_COL = "closeMorningTime" ;
    private static final String SALOON_CLOSE_AFTERNOON_TIME = "closeAfternoonTime" ;


    private String barberEmail;
    private Integer idSaloon;


    public  void setBarberEmail(String barberEmail) {
        this.barberEmail = barberEmail;
    }

    public SaloonCatalogue loadAllSaloon() {

        Saloon saloon;
        SaloonCatalogue saloonCatalogue = new SaloonCatalogue();
        List<Saloon> saloons = new ArrayList<>();
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


    public static List<Saloon> retrieveByCityName(String saloonCity) throws Exception{
        Statement stmt = null;
        Connection connection = Connector.getConnectorInstance().getConnection();

        List<Saloon> listOfSaloon = new ArrayList<>();

        try {
            //creazione ed esecuzione della query
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            if (stmt==null)
                System.out.println("unable to execute query");

            ResultSet rs = Queries.selectSaloonByCity(stmt,saloonCity);

            if (!rs.first()) { //rs empty
                throw new Exception("No Saloon found matching with city: " + saloonCity);
            }

            //riposiz. del cursore
            rs.first();
            do {
                // lettura delle colonne "by city"
                String name = rs.getString(SALOON_NAME_COL);
                String address = rs.getString(SALOON_ADDRESS_COL);
                String phone = rs.getString(SALOON_TELEPHONE_COL);
                Time slotTime = rs.getTime(SALOON_INTERVAL_SLOT_TIME_COL);
                int seatNumber = rs.getInt(SALOON_SEAT_NUMBER_COL);

                Saloon s = new Saloon(saloonCity, name,address, phone, slotTime, seatNumber);
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


    public Saloon retrieveByNameSaloon(String saloonName) throws Exception {
        Statement stmt = null;
        Saloon saloon;
        Connection connection = Connector.getConnectorInstance().getConnection();

        try {
            //creazione ed esecuzione della query
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            if (stmt==null)
                System.out.println("unable to execute query");

            ResultSet rs = Queries.selectSaloonByCity(stmt,saloonName);

            if (!rs.first()) { //rs empty
                throw new Exception("No Saloon found matching with city" + saloonName);
            }

            //riposiz. del cursore
            rs.first();
            do {
                // lettura delle colonne "by name"
                String city = rs.getString(SALOON_CITY_COL);
                String address = rs.getString(SALOON_ADDRESS_COL);
                String phone = rs.getString(SALOON_TELEPHONE_COL);
                Time slotTime = rs.getTime(SALOON_INTERVAL_SLOT_TIME_COL);
                int seatNumber = rs.getInt(SALOON_SEAT_NUMBER_COL);

               saloon = new Saloon(city, saloonName,address, phone, slotTime, seatNumber);
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


    public static Saloon retreiveTimeSlots(String saloonName) throws Exception {
        Statement stmt = null;
        Saloon saloon;
        Connection connection = Connector.getConnectorInstance().getConnection();

        try {
            //creazione ed esecuzione della query
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            if (stmt==null)
                System.out.println("unable to execute query");

            ResultSet rs = Queries.selectSlotTimeSaloon(stmt, saloonName);

            if (!rs.first()) { //rs empty
                throw new Exception("No Saloon time-slots found matching with saloon: " + saloonName);
            }

            //riposiz. del cursore
            rs.first();
            do {
                // lettura delle colonne "by name"
                Time  openMorningTime = rs.getTime("openMorningTime");
                Time openAfternoonTime = rs.getTime("openAfternoonTime");
                Time closeMorningTime = rs.getTime("closeMorningTime");
                Time closeAfternoonTime = rs.getTime("closeAfternoonTime");
                Time intervalSlotTime = rs.getTime("intervalSlotTime");

                saloon = new Saloon(saloonName, openMorningTime, openAfternoonTime,closeMorningTime, closeAfternoonTime,intervalSlotTime);
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