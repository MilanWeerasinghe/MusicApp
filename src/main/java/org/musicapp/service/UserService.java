package org.musicapp.service;

import org.musicapp.dao.UserDAO;
import org.musicapp.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User userAuthenticate(String userName, String password) throws SQLException{
        return userDAO.userAuthenticate(userName, password);
    }

    public boolean addUser(User user) throws SQLException{
        return userDAO.addUser(user);
    }

    public boolean deleteUser(int id) throws SQLException{
        return userDAO.deleteUser(id);
    }

    public int searchUser(String userName) throws SQLException{
        return userDAO.searchUser(userName);
    }

    public List<User> getAllUsers() throws SQLException{
        return userDAO.getAllUsers();
    }

    public boolean updateUser(User user) throws SQLException{
        return userDAO.updateUser(user);
    }
}
