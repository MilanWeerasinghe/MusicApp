package org.musicapp.dao;

import org.jetbrains.annotations.NotNull;
import org.musicapp.model.User;
import org.musicapp.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection conn = null;

//    User authentication
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

//    Add user to the database
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

//    Get All users from DB
    public List<User> getAllUsers() throws SQLException{
        List<User> users = new ArrayList<>();
        Statement statement = null;
        try{
            conn =DBConnection.getConnection();
            String sql = "SELECT * FROM users";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                User user = new User(
                        resultSet.getInt("userId"),
                        resultSet.getString("userName"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("email"));
                users.add(user);
            }
        }finally {
            if(statement != null) statement.close();
            DBConnection.closeConnection(conn);
        }
        return users;
    }

//    Search user by userName
    public int searchUser(String userName) throws SQLException{
        int userId = -1;
        PreparedStatement statement = null;
        try{
            conn =DBConnection.getConnection();
            String sql = "SELECT userId FROM users WHERE userName = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1,userName);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                userId = resultSet.getInt("userId");
            }
        }finally {
            if(statement != null) statement.close();
            DBConnection.closeConnection(conn);
        }
        return  userId;
    }

//    Update user by userId
    public boolean updateUser(User user) throws SQLException{
        boolean status = false;
        PreparedStatement statement = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "UPDATE users SET userName = ?, password = ?, role = ?, email = ? WHERE userId = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUserName());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getUserRole());
            statement.setString(4,user.getEmail());
            statement.setInt(5,user.getUserId());
            int rows = statement.executeUpdate();
            status = rows>0;
        }finally {
            if(statement != null) statement.close();
            DBConnection.closeConnection(conn);
        }
        return status;
    }

//    Delete a user from DB
    public boolean deleteUser(int userId) throws SQLException{
        boolean status = false;
        PreparedStatement statement = null;
        try{
            conn = DBConnection.getConnection();
            String sql = "DELETE FROM users WHERE userId = ? ";
            statement = conn.prepareStatement(sql);
            statement.setInt(1,userId);
            int rows =statement.executeUpdate();
            status = rows>0;
        }finally {
            if(statement != null) statement.close();
            DBConnection.closeConnection(conn);
        }
        return status;
    }

}
