package org.musicapp.service;

import org.musicapp.repo.UserRepo;
import org.musicapp.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserRepo userDAO = new UserRepo();

    public String userAuthenticate(User user) throws SQLException{
         if(userDAO.userAuthenticate(user) == null) {
             throw new SQLException("User not authenticated");
         }
         return userDAO.userAuthenticate(user);
    }

    public void addUser(User user) throws Exception{
        userDAO.addUser(user);
    }

    public void deleteUser(String username) throws Exception{
        searchUser(username);
        userDAO.deleteUser(username);
    }

    public User getUserByUsername(String username) throws Exception{
        searchUser(username);
        return userDAO.getUserByUsername(username);
    }
    public void searchUser(String userName) throws Exception{
        if(userDAO.getUserByUsername(userName) == null) {
            throw new Exception("User not found");
        }
    }

    public List<User> getAllUsers() throws Exception{
        if(userDAO.getAllUsers().isEmpty()) {
            throw new Exception("User not found");
        }
        return userDAO.getAllUsers();
    }

    public void updateUser(User user) throws Exception{
        searchUser(user.getUsername());
        userDAO.updateUser(user);
    }
}
