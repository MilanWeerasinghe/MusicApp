package org.musicapp.util;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;
    private static String dbURL = "jdbc:mysql://localhost:3306/musicapp";
    private static String user = "root";
    private static String password = "As2020356366@";

    static {
        try {
            DriverManager.registerDriver(new SQLServerDriver());
        } catch (SQLException ex) {
            System.out.println("An error occurred while establishing the connection:");
            ex.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        try {
            conn =   DriverManager.getConnection(dbURL, user, password);
//            if (conn != null) {
//                System.out.println("The connection has been successfully established.");
//
//                DatabaseMetaData dm = conn.getMetaData();
//                System.out.println("Driver name: " + dm.getDriverName());
//                System.out.println("Driver version: " + dm.getDriverVersion());
//                System.out.println("Product name: " + dm.getDatabaseProductName());
//                System.out.println("Product version: " + dm.getDatabaseProductVersion());
//            }
            return conn;
        }catch (SQLException e){
            throw new SQLException();
        }

    }
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}



