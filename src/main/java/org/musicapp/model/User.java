package org.musicapp.model;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String userRole;

    public User(){}

    public User(int userId, String userName, String password, String userRole){
        setUserId(userId);
        setUserName(userName);
        setPassword(password);
        setUserRole(userRole);
    }
    public User(String userName, String password, String userRole){
        setUserName(userName);
        setPassword(password);
        setUserRole(userRole);
    }
    public User(String userName, String password){
        setUserName(userName);
        setPassword(password);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
