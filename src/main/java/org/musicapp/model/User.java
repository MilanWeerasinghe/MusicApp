package org.musicapp.model;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String userRole;
    private String email;

    public User(){}

    public User(int userId, String userName, String password, String userRole, String email){
        setUserId(userId);
        setUserName(userName);
        setPassword(password);
        setUserRole(userRole);
        setEmail(email);
    }
    public User(String userName, String password, String userRole, String email){
        setUserName(userName);
        setPassword(password);
        setUserRole(userRole);
        setEmail(email);
    }
    public User(String userName, String password){
        setUserName(userName);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
