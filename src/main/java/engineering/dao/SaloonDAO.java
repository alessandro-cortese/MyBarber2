package engineering.dao;

import engineering.container.SaloonCatalogue;
import engineering.dao.queries.Queries;
import engineering.pattern.Connector;
import model.Saloon;
import java.sql.*;
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
    private static final String SALOON_OPENING_MORNING_TIME_COL = "openMorningTime" ;
    private static final String SALOON_OPENING_AFTERNOON_TIME_COL = "openAfternoonTime" ;
    private static final String SALOON_CLOSE_MORNING_TIME_COL = "closeMorningTime" ;
    private static final String SALOON_CLOSE_AFTERNOON_TIME = "closeAfternoonTime" ;

    
    public SaloonCatalogue loadAllSaloonByBarberEmail(String barberEmail) {

        Connection connection = Connector.getConnectorInstance().getConnection();
        SaloonCatalogue saloonCatalogue = new SaloonCatalogue();
        ArrayList<Saloon> saloons = new ArrayList<>();
        Saloon saloon = null;


        try(Statement statement = connection.createStatement();
            ResultSet resultSet = Queries.loadAllSaloon(statement, barberEmail)){

            while(resultSet.next()) {

                saloon = createSaloon(resultSet);
                saloons.add(saloon);

            }


        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }

        saloonCatalogue.setSaloonList(saloons);

        return saloonCatalogue;
    }

    public Saloon loadSaloonByBarberEmail(String barberEmail){

        Connection connection = Connector.getConnectorInstance().getConnection();
        Saloon saloon = null;

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = Queries.loadAllSaloon(statement, barberEmail)) {

            if(resultSet.isFirst()) {

                saloon = createSaloon(resultSet);

            }


        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }


        return saloon;
    }

    public List<Saloon> retrieveByCityName(String saloonCity) {

        Connection connection = Connector.getConnectorInstance().getConnection();
        ArrayList<Saloon> saloons = new ArrayList<>();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = Queries.loadSaloonByName(statement, saloonCity)){

            while(resultSet.next()){

                Saloon saloon = createSaloon(resultSet);
                saloons.add(saloon);
            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }

        return saloons;

    }

    public Saloon retrieveByNameOfSaloon(String saloonName) {

        Connection connection = Connector.getConnectorInstance().getConnection();
        Saloon saloon = null;

        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = Queries.selectSaloonByName(statement, saloonName)){

            if(resultSet.first()){

                saloon = createSaloon(resultSet);

            }


        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }

        return saloon;

    }

    public Saloon retrieveTimeSlots(String saloonName) {

        Connection connection = Connector.getConnectorInstance().getConnection();
        Saloon saloon = null;
        Time[][] times = new Time[2][2];
        Time intervalSlotTime;
        int[] numberSlotTime = new int[2];

        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = Queries.selectSlotTimeSaloon(statement, saloonName)) {

            if(resultSet.first()) {

                times[0][0] = resultSet.getTime(SALOON_OPENING_MORNING_TIME_COL);
                times[0][1] = resultSet.getTime(SALOON_CLOSE_MORNING_TIME_COL);
                times[1][0] = resultSet.getTime(SALOON_OPENING_AFTERNOON_TIME_COL);
                times[1][1] = resultSet.getTime(SALOON_CLOSE_AFTERNOON_TIME);
                intervalSlotTime = resultSet.getTime(SALOON_INTERVAL_SLOT_TIME_COL);
                numberSlotTime[0] = resultSet.getInt(SALOON_NUMBER_SLOT_TIME_MORNING_COL);
                numberSlotTime[1] = resultSet.getInt(SALOON_NUMBER_SLOT_TIME_AFTERNOON_COL);

                saloon = new Saloon(saloonName, times, intervalSlotTime, numberSlotTime);

            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }

        return saloon;
    }

    public Integer loadIdOfSaloon(String barberEmail){

        Connection connection = Connector.getConnectorInstance().getConnection();

        Integer saloonId = null;

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = Queries.loadAllSaloon(statement, barberEmail)) {

            if (resultSet.isFirst()) {

                saloonId = resultSet.getInt(SALOON_NAME_ID);

            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }

        return saloonId;
    }

    private Saloon createSaloon(ResultSet resultSet) throws SQLException {

        Integer[] numberOfSlots = new Integer[2];
        String[] cityAndAddress = new String[2];
        String name = resultSet.getString(SALOON_NAME_COL);
        String address = resultSet.getString(SALOON_ADDRESS_COL);
        String city = resultSet.getString(SALOON_CITY_COL);
        String telephone = resultSet.getString(SALOON_TELEPHONE_COL);
        int seatsNumber = resultSet.getInt(SALOON_SEAT_NUMBER_COL);
        numberOfSlots[0] = resultSet.getInt(SALOON_NUMBER_SLOT_TIME_MORNING_COL) ;
        numberOfSlots[1] = resultSet.getInt(SALOON_NUMBER_SLOT_TIME_AFTERNOON_COL) ;
        Time intervalSlotTime = resultSet.getTime(SALOON_INTERVAL_SLOT_TIME_COL);
        cityAndAddress[0] = city;
        cityAndAddress[1] = address;

        return new Saloon(name, cityAndAddress, telephone, intervalSlotTime, seatsNumber, numberOfSlots);
    }

}