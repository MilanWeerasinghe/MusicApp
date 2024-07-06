package org.musicapp.service;

import org.musicapp.dao.UserDAO;
import org.musicapp.model.User;

import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User userAuthenticate(String userName, String password) throws SQLException{
        return userDAO.userAuthenticate(userName, password);
    }

    public boolean addUser(User user) throws SQLException{
        return userDAO.addUser(user);
    }
}
