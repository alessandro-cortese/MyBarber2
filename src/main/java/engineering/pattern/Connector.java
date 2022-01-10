package engineering.pattern;

import java.sql.*;

public class Connector { //SINGLETON CLASS

        private Connection connection ; //di classe
        private static Connector myConnector ;

        protected Connector() throws RuntimeException {

            try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String dbName = "mydb";
                    String userName="admin";
                    String password="Alessandro99";
                    String port ="3306";
                    String hostname="mybarberdb.cvgybcfusiqr.eu-west-2.rds.amazonaws.com";

                    String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                    connection = DriverManager.getConnection(jdbcUrl);

                    connection.setNetworkTimeout(E);
                }
                catch (ClassNotFoundException e) {
                    throw new RuntimeException("Cannot find the driver in the classpath!", e);
                }
                catch (SQLException e) {
                    // handle any errors
                    System.out.println("SQLException: " + e.getMessage());
                    System.out.println("SQLState: " + e.getSQLState());
                    System.out.println("VendorError: " + e.getErrorCode());
                }
           // }
        }

        public static Connector getConnectorInstance() { //di classe
            if (myConnector == null)
                myConnector = new Connector();
            return myConnector ;
        }

        public Connection getConnection() {
            return connection;
        }
}

