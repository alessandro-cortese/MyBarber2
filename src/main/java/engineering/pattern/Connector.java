package engineering.pattern;

import java.sql.*;

public class Connector { //SINGLETON CLASS

        private static Connection connection = null; //di classe

        protected Connector() {
            if (System.getenv("RDS_HOSTNAME") != null) {
                try {
                    System.out.println("Loading driver...");
                    Class.forName("com.mysql.jdbc.Driver.");
                    System.out.println("Driver loaded!");
                    /*String dbName = System.getenv("RDS_DB_NAME");
                    String userName = System.getenv("RDS_USERNAME");
                    String password = System.getenv("RDS_PASSWORD");
                    String hostname = System.getenv("RDS_HOSTNAME");
                    String port = System.getenv("RDS_PORT");*/

                    String dbName = "my_db";
                    String userName="admin";
                    String password="Alessandro99";
                    String port ="3306";
                    String hostname="mybarberdb.cvgybcfusiqr.eu-west-2.rds.amazonaws.com";

                    String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                    Connection con = DriverManager.getConnection(jdbcUrl);

                    this.connection =  con; // lo posso fare?? BOH

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
            }
        }

        public static Connection getConnectorInstance() { //di classe
            if (Connector.connection == null)
                Connector.connection = (Connection) new Connector();
            return connection;
        }

    }

