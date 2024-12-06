package org.musicapp;

import org.musicapp.controller.AlbumManage;
import org.musicapp.controller.ArtistManage;
import org.musicapp.controller.SongManage;
import org.musicapp.controller.UserManage;
import org.musicapp.util.UserAuth;

import java.sql.SQLException;
import java.util.Scanner;

public class ArtistManagementApp {
    public static void main(String[] args) throws Exception {
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
                            SongManage songController = new SongManage();
                            songController.manageSongs();
                            break;
                        case 2:
                            ArtistManage artist = new ArtistManage();
                            artist.manageArtist();
                            break;
                        case 3:
                            AlbumManage manageAlbum = new AlbumManage();
                            manageAlbum.manageAlbums();
                            break;
                        case 4:
                            UserManage userController = new UserManage();
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