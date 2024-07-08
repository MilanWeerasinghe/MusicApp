package org.musicapp.controller;

import org.musicapp.model.User;
import org.musicapp.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class UserController {
    private UserService userService = new UserService();
    private User currentUser = null;

    public void manageUsers(){
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("1. Add User");
            System.out.println("2. Get User Details");
            System.out.println("3. Update User Details");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if(isAdmin()) addUser(scanner);
                    break;
                case 2:
                    getAllUsers();
                    break;
                case 3:
                    if(isAdmin()) updateUser(scanner);
                    break;
                case 4:
                    if(isAdmin()) deleteUser(scanner);
                    break;
                case 5:
                    return;
            }
        }
    }
    public boolean login(){
        boolean status = false;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter UserName: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try{
            currentUser =  userService.userAuthenticate(userName, password);
            if (currentUser != null) {
                System.out.println("Login Successful... Welcome " + currentUser.getUserName());
                System.out.println("----------------------------------------");
                status = true;
            }
            else
                System.out.println("Login Unsuccessful");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;

    }
    public void addUser(Scanner scanner){
        scanner.nextLine();
        System.out.print("Enter User name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Password : ");
        String password = scanner.nextLine();
        System.out.print("Enter user Role: ");
        String userRole = scanner.nextLine();
        System.out.print("Enter user Email: ");
        String email = scanner.nextLine();
        User user = new User(userName, password, userRole, email);
        try{
            boolean isAdded = userService.addUser(user);
            if(isAdded)
                System.out.println("User Added Successfully.");
            else
                System.out.println("Something went Wrong!");
        }catch (SQLException e){
            if (e.getSQLState().startsWith("23")) { // SQLState 23 indicates constraint violations
                System.out.println("Error: Duplicate record found!");
            } else {
                e.printStackTrace();
            }
        }
    }
    public void getAllUsers(){
        List<User> users = new ArrayList<>();
        try {
            users = userService.getAllUsers();
            for(User user : users){
                if (user != null) {
                    System.out.println(user.toString());
                    System.out.println("---------------------");
                } else {
                    System.out.println("User not found");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int searchUser(Scanner scanner){
        scanner.nextLine();
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();

        int userId = -1;
        try {
            userId = userService.searchUser(userName);
            if(userId != -1)
                System.out.println("User Found");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userId;
    }
    public void updateUser(Scanner scanner){
        int userId = searchUser(scanner);
        if(userId != -1){
            try{
                System.out.println("Enter New User Details");
                System.out.print("userName: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                System.out.print("user Role: ");
                String userRole = scanner.nextLine();
                System.out.print("Email   : ");
                String email = scanner.nextLine();
                User user = new User(userId, username, password, userRole, email);
                boolean isUpdated = userService.updateUser(user);
                if (isUpdated)
                    System.out.println("Successfully Deleted.");
                else
                    System.out.println("Something went wrong!");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        else
            System.out.println("User Not Found!");
    }
    public void deleteUser(Scanner scanner){
        int userId = searchUser(scanner);
        if(userId != -1){
            try{
                boolean isDeleted = userService.deleteUser(userId);
                if (isDeleted)
                    System.out.println("Successfully Deleted.");
                else
                    System.out.println("Something went wrong!");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        else
            System.out.println("User Not Found!");
    }
    public boolean isAdmin(){
        boolean status = currentUser != null && currentUser.getUserRole().equals("admin");
        if (!status)
            System.out.println("Access denied..");
        return status;
    }
    public boolean isAuthenticated(){
        return currentUser != null;
    }

}
