package org.musicapp.repo;

import org.musicapp.model.User;
import org.musicapp.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    public String userAuthenticate(User user) throws SQLException{
        String sql = "SELECT userRole FROM user WHERE userName= ? AND password= ? ";
        try( Connection conn = DBConnection.getInstance();
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("userRole");
            }
        }
        return null;
    }


    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO user(username, password, userRole) VALUES (?, ?, ?) ";
        try (Connection conn = DBConnection.getInstance();
             PreparedStatement statement = conn.prepareStatement(sql);) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUserRole());
            statement.executeUpdate();
        }
    }

//    Get All users from DB
    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM user";
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnection.getInstance();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("userId"),
                        resultSet.getString("userName"),
                        resultSet.getString("password"),
                        resultSet.getString("userRole"));
                users.add(user);
            }
            return users;
        }
    }


    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        try (Connection conn = DBConnection.getInstance();
             PreparedStatement statement = conn.prepareStatement(sql);) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("userId"),
                        resultSet.getString("username"),
                        resultSet.getString("userRole"));
                return user;
            }
        }
        return null;
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET password = ?, userRole = ? WHERE userId = ?";
        try (Connection conn = DBConnection.getInstance();
             PreparedStatement statement = conn.prepareStatement(sql);) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getUserRole());
            statement.setInt(3, user.getUserId());
            statement.executeUpdate();
        }
    }


    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM user WHERE username = ? ";
        try (Connection conn = DBConnection.getInstance();
             PreparedStatement statement = conn.prepareStatement(sql);) {
            statement.setString(1, username);
            statement.executeUpdate();
        }
    }

}
