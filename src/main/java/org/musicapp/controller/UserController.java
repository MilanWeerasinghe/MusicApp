package org.musicapp.controller;

import org.musicapp.model.User;
import org.musicapp.service.UserService;
import org.musicapp.util.UserAuth;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class UserController {
    private UserService userService = new UserService();
    private UserAuth userAuth = new UserAuth();

    public void manageUsers() throws SQLException {
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

    public void addUser(Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Enter User name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Password : ");
        String password = scanner.nextLine();
        System.out.print("Enter user Role: ");
        String userRole = scanner.nextLine();

        userService.addUser(new User(userName,password,userRole));

    }
    public void getAllUsers() throws SQLException {
        List<User> users = userService.getAllUsers();
        if(users.isEmpty()){
            System.out.println("No users in the application");
        }else {
            users.forEach(user -> System.out.println(user));
        }
    }

    public void getUserByUsername(Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();

        userService.getUserByUsername(userName);
    }
    public void updateUser(Scanner scanner) throws SQLException {
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
    }

    public void deleteUser(Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Enter userId: ");
        int userId = scanner.nextInt();
        userService.deleteUser(userId);
    }



}
