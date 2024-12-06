package org.musicapp.controller;

import org.musicapp.model.Album;
import org.musicapp.service.AlbumService;
import org.musicapp.util.ExceptionHandling;
import org.musicapp.util.UserAuth;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AlbumManage {
    private AlbumService albumService = new AlbumService();
    private UserAuth userAuth = new UserAuth();


    public void manageAlbums(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. List Album");
            System.out.println("2. Create Album");
            System.out.println("3. Update Album Details");
            System.out.println("4. Delete Album");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllAlbums();
                    break;
                case 2:
                    if (userAuth.isAdmin()) addAlbum(scanner);
                    break;
                case 3:
                    if (userAuth.isAdmin()) updateAlbum(scanner);
                    break;
                case 4:
                    if (userAuth.isAdmin()) deleteAlbum(scanner);
                    break;
                case 5:
                    return;
            }
        }
    }

    public void addAlbum(Scanner scanner){
        try{
            scanner.nextLine();
            System.out.print("Enter Album name: ");
            String albumName = scanner.nextLine();

            albumService.addAlbum(new Album(albumName));
        }catch (Exception e) {
            ExceptionHandling.printError(e.getMessage());
        }
    }

    public void listAllAlbums(){
        try{
            List<Album> albumList = albumService.getAlbumList();
            albumList.forEach(album -> System.out.println(
                    album.toString() + "\n" + "-------------------------------"
            ));
        }catch (Exception e) {
            ExceptionHandling.printError(e.getMessage());
        }
    }


    public void updateAlbum(Scanner scanner){
        try{
            scanner.nextLine();
            System.out.println("Enter Album ID: ");
            int albumId = scanner.nextInt();

            albumService.searchAlbum(albumId);

            System.out.print("Enter Album name: ");
            String name = scanner.nextLine();
            albumService.updateAlbum(new Album(albumId,name));
        }catch (Exception e) {
            ExceptionHandling.printError(e.getMessage());
        }
    }
    public void deleteAlbum(Scanner scanner){
        try{
            scanner.nextLine();
            System.out.print("Enter album ID: ");
            int albumId = scanner.nextInt();

            albumService.removeAlbum(albumId);
        }catch (Exception e) {
            ExceptionHandling.printError(e.getMessage());
        }
    }
}
