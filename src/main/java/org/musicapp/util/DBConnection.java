package org.musicapp.util;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
    private static DBConnection instance;
    private final Connection connection;

    private DBConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/musicapp";
        String user = "root";
        String password = "as2020356";

        DriverManager.registerDriver(new SQLServerDriver());
        connection =   DriverManager.getConnection(dbURL, user, password);
    }

    public static Connection getInstance() throws SQLException {
       if(instance == null || instance.connection.isClosed()) {
           instance = new DBConnection();
           return instance.connection;
       }
       return instance.connection;
    }

}



