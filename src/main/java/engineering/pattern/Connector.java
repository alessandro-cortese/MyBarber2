package engineering.pattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Connector { //SINGLETON CLASS

        private Connection connection ; //di classe
        private static Connector myConnector ;

        protected Connector()  {

            try {
                    /*
                    Properties prop = new Properties() ;
                    prop.load(new FileInputStream("engineering/dao/db_configuration.properties"));
                    String driver = prop.getProperty("jdbcDriver") ;
                    String dbName = prop.getProperty("dbName") ;
                    String dbUsername = prop.getProperty("dbUserName") ;
                    String dbPassword = prop.getProperty("dbPassword") ;
                    String dbPort = prop.getProperty("dbPort") ;
                    String dbHostname = prop.getProperty("dbHostName") ;
                    String dbUrlStart = prop.getProperty("dbUrlStart") ;

                    String jdbcUrl = String.format("%s://%s:%s/%s?user=%s&password=%s",dbUrlStart,dbHostname,dbPort,dbName, dbUsername, dbPassword) ;
                    connection = DriverManager.getConnection(jdbcUrl);
                    */
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

        public static Connector getConnectorInstance() { //di classe
            if (myConnector == null)
                myConnector = new Connector();
            return myConnector ;
        }

        public Connection getConnection() {
            return connection;
        }
}

