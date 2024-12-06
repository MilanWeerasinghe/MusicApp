package org.musicapp.controller;

import org.musicapp.model.User;
import org.musicapp.service.UserService;
import org.musicapp.util.ExceptionHandling;
import org.musicapp.util.UserAuth;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class UserManage {
    private UserService userService = new UserService();
    private UserAuth userAuth = new UserAuth();

    public void manageUsers() throws Exception {
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
                    if(userAuth.isAdmin()) addUser(scanner);
                    break;
                case 2:
                    getAllUsers();
                    break;
                case 3:
                    if(userAuth.isAdmin()) updateUser(scanner);
                    break;
                case 4:
                    if(userAuth.isAdmin()) deleteUser(scanner);
                    break;
                case 5:
                    return;
            }
        }
    }

    public void addUser(Scanner scanner) throws Exception {
        scanner.nextLine();
        System.out.print("Enter User name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Password : ");
        String password = scanner.nextLine();
        System.out.print("Enter user Role: ");
        String userRole = scanner.nextLine();

        userService.addUser(new User(userName,password,userRole));

    }
    public void getAllUsers(){
        try{
            List<User> users = userService.getAllUsers();
            users.forEach(user -> System.out.println(
                    user.toString() + "\n" + "-------------------------------"
            ));
        }catch (Exception e) {
            ExceptionHandling.printError(e.getMessage());
        }
    }

    public void getUserByUsername(Scanner scanner){
        try{
            scanner.nextLine();
            System.out.print("Enter username: ");
            String userName = scanner.nextLine();

            User user = userService.getUserByUsername(userName);
            System.out.println(user);
        }catch (Exception e) {
            ExceptionHandling.printError(e.getMessage());
        }
    }
    public void updateUser(Scanner scanner){
        try{
            scanner.nextLine();
            System.out.print("Enter userId: ");
            int userId = scanner.nextInt();
            System.out.print("Enter username: ");
            String userName = scanner.nextLine();
            System.out.print("Enter Password : ");
            String password = scanner.nextLine();
            System.out.print("Enter userRole: ");
            String userRole = scanner.nextLine();

            userService.updateUser(new User(userId,userName,password,userRole));
        }catch (Exception e){
            ExceptionHandling.printError(e.getMessage());
        }
    }

    public void deleteUser(Scanner scanner){
        try{
            scanner.nextLine();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            userService.deleteUser(username);
        }catch (Exception e){
            ExceptionHandling.printError(e.getMessage());
        }
    }



}
