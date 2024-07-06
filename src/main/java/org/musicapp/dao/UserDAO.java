package org.musicapp.dao;

import org.jetbrains.annotations.NotNull;
import org.musicapp.model.User;
import org.musicapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection conn = null;

    public User userAuthenticate(String userName, String password) throws SQLException{
        PreparedStatement statement = null;
        User user = null;
        ResultSet resultSet = null;

        try{
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE userName= ? AND password= ? ";
            statement = conn.prepareStatement(sql);
            statement.setString(1,userName);
            statement.setString(2,password);

            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setUserRole(resultSet.getString(4));
            }
        }finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            DBConnection.closeConnection(conn);
        }
        return user;
    }

    public boolean addUser(@NotNull User user) throws SQLException{
        boolean status = false;
        PreparedStatement statement = null;
        try{
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO users(userName, password, role, email) VALUES (?, ?, ?, ?) ";
            statement = conn.prepareStatement(sql);
            statement.setString(1,user.getUserName());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getUserRole());
            statement.setString(4,user.getEmail());
            int rows = statement.executeUpdate();
            status = rows>0;
        }finally {
            if (statement != null) statement.close();
            DBConnection.closeConnection(conn);
        }
        return status;
    }
}
