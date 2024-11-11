package org.musicapp.util;

import org.musicapp.model.User;
import org.musicapp.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class UserAuth {
    private static User currentUser;
    private UserService userService = new UserService();

    public boolean login(){
        boolean status = false;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter UserName: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try{
            currentUser =  userService.userAuthenticate(new User(userName, password));
            if (currentUser != null) {
                System.out.println("Login Successful... Welcome " + currentUser.getUsername());
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
