package org.musicapp.controller;

import org.musicapp.model.User;
import org.musicapp.service.UserService;

import java.sql.SQLException;
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
                    addUser(scanner);
                    break;
                case 2:
                    break;
                case 4:
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
