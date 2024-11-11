package org.musicapp;

import org.musicapp.controller.AlbumController;
import org.musicapp.controller.ArtistController;
import org.musicapp.controller.SongController;
import org.musicapp.controller.UserController;
import org.musicapp.util.UserAuth;

import java.sql.SQLException;
import java.util.Scanner;

public class ArtistManagementApp {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UserAuth userAuth = new UserAuth();

        greeting();
        while(true) {
            if(userAuth.login()){
                while (true){
                    printMenu();
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();

                    switch (choice){
                        case 1:
                            SongController songController = new SongController();
                            songController.manageSongs();
                            break;
                        case 2:
                            ArtistController artist = new ArtistController();
                            artist.manageArtist();
                            break;
                        case 3:
                            AlbumController manageAlbum = new AlbumController();
                            manageAlbum.manageAlbums();
                            break;
                        case 4:
                            UserController userController = new UserController();
                            userController.manageUsers();
                            break;
                        case 5:
                            return;
                    }
                }
            }
        }
    }

    public static void printMenu(){
        System.out.println("1. Manage Songs");
        System.out.println("2. Manage Artists");
        System.out.println("3. Manage Albums");
        System.out.println("4. Manage Users");
        System.out.println("5. Exit Music App");
        System.out.println();
    }

    public static void greeting(){
        System.out.println("----- Welcome to Music App -----");
    }
}