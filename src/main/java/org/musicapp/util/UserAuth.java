package org.musicapp.util;

import org.musicapp.model.User;
import org.musicapp.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class UserAuth {
    private static String currentUserRole;
    private UserService userService = new UserService();

    public boolean login(){
        boolean status = false;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter UserName: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try{
            currentUserRole = userService.userAuthenticate(new User(userName, password));
                System.out.println("Login as "+ currentUserRole + "\nWelcome " + userName);
                System.out.println("----------------------------------------");
                status = true;
        }catch (Exception e){
            ExceptionHandling.printError(e.getMessage());
        }
        return status;

    }
    public boolean isAdmin(){
        boolean status = currentUserRole.equals("Admin");
        if (!status)
            System.out.println("Access denied..");
        return status;
    }

}
