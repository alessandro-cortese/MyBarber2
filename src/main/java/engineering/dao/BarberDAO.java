package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.pattern.Connector;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BarberDAO {
    private static  final String BARBER = "barber";
    public void insertBarber(User barber) {
        Connection connection = Connector.getConnectorInstance().getConnection();
        try {

            Statement statement = connection.createStatement();
            System.out.println(barber.getPass());
            boolean resultSet2 = Queries.insertIntoUser(statement,barber.getEmail(),barber.getPass(),BARBER);
            boolean resultSet = Queries.insertIntoBarber(statement, barber.getName(), barber.getSurname(), barber.getEmail());

        } catch (SQLException e) {// fare conversione dell'eccezione
            e.printStackTrace();

        }
    }
}
