package org.musicapp.model;

public class User {
    private int userId;
    private String username;
    private String password;
    private String userRole;

    public User(int userId, String username, String password, String userRole){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public User(String username, String password, String userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(int userId, String username,String userRole) {
        this.username = username;
        this.userId = userId;
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
