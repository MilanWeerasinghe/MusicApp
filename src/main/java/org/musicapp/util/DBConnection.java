package org.musicapp.util;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
    private static volatile DBConnection instance;
    private Connection connection;
    private String dbURL = "jdbc:mysql://localhost:3306/musicapp";
    private String user = "root";
    private String password = "As2020356366";

    private DBConnection() throws SQLException {
            DriverManager.registerDriver(new SQLServerDriver());
            connection =   DriverManager.getConnection(dbURL, user, password);
    }

    public static DBConnection getInstance() throws SQLException {
        DBConnection result = instance;
       if(result == null || instance.getConnection().isClosed()){
           synchronized (DBConnection.class){
               result = instance;
               if (result == null || instance.getConnection().isClosed()){
                   result =instance =  new DBConnection();
               }
           }
       }
       return result;
    }

    public Connection getConnection(){return connection;}

    public void closeConnection() throws SQLException {
        if (instance != null && instance.connection != null) {
                instance.connection.close();
                instance = null;
        }
    }
}



