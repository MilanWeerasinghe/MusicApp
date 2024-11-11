package org.musicapp.service;

import org.musicapp.dao.UserDAO;
import org.musicapp.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User userAuthenticate(User user) throws SQLException{
        return userDAO.userAuthenticate(user);
    }

    public void addUser(User user) throws SQLException{
        userDAO.addUser(user);
    }

    public void deleteUser(int id) throws SQLException{
        userDAO.deleteUser(id);
    }

    public User getUserByUsername(String userName) throws SQLException{
        return userDAO.getUserByUsername(userName);
    }

    public List<User> getAllUsers() throws SQLException{
        return userDAO.getAllUsers();
    }

    public void updateUser(User user) throws SQLException{
        userDAO.updateUser(user);
    }
}
