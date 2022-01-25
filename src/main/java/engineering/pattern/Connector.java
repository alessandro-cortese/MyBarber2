package engineering.pattern;

import java.sql.*;

public class Connector {

        private Connection connection ;
        private static Connector myConnector ;

        protected Connector()  {

            try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String dbName = "mydb";
                    String userName="admin";
                    String password="Alessandro99";
                    String port ="3306";
                    String hostname="mybarberdb.cvgybcfusiqr.eu-west-2.rds.amazonaws.com";

                    String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                    connection = DriverManager.getConnection(jdbcUrl);
            }catch (SQLException e) {
                    e.getMessage();
                    e.getSQLState();
                    e.getErrorCode();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        public static Connector getConnectorInstance() {
            if (myConnector == null)
                myConnector = new Connector();
            return myConnector ;
        }

        public Connection getConnection() {
            return connection;
        }
}

