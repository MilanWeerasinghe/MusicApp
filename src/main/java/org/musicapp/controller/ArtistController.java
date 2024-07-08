package org.musicapp.controller;

import org.musicapp.model.Artist;
import org.musicapp.model.Song;
import org.musicapp.service.ArtistService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArtistController {
    private ArtistService artistService = new ArtistService();
    private UserController currentUser = new UserController();

    public void manageArtist(){
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("1. Add Artist");
            System.out.println("2. Get Artists");
            System.out.println("3. Update Artist");
            System.out.println("4. Delete Artist");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if(currentUser.isAdmin()) addArtist(scanner);
                    break;
                case 2:
                    getAllArtist();
                    break;
                case 3:
                    if(currentUser.isAdmin()) updateArtist(scanner);
                    break;
                case 4:
                    if(currentUser.isAdmin()) deleteArtist(scanner);
                    break;
                case 5:
                    return;
            }
        }
    }

    public void addArtist(Scanner scanner){
        scanner.nextLine();
        Artist artist = null;
        System.out.print("Enter Artist First Name: ");
        String userFName = scanner.nextLine();
        System.out.print("Enter Artist Last Name : ");
        String userLName = scanner.nextLine();
        System.out.print("Enter Artist Age : ");
        int age = scanner.nextInt();

        try{
            artist = new Artist(userFName, userLName, age);
            boolean isAdded = artistService.addArtist(artist);
            if(isAdded)
                System.out.println("Artist Added Successfully.");
            else{
                System.out.println("Something went wrong!");
            }
        }catch (SQLException e){
            if (e.getSQLState().startsWith("23")) { // SQLState 23 indicates constraint violations
                System.out.println("Error: Duplicate record found!");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void getAllArtist(){
        List<Artist> artists = null;
        try{
            artists = artistService.getAllArtist();
            for(Artist artist : artists){
                int count = 0;
                System.out.print(artist.toString());
                for(Song song : artist.getSongs()){
                    System.out.println("Song " + ++count + " Name : " + song.getSongTitle());
                }
                System.out.println("-------------------------");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int searchArtist(Scanner scanner){
        scanner.nextLine();
        int artistId = -1;
        System.out.println("Enter Artist Name to search");
        System.out.print("First name: ");
        String fName = scanner.nextLine();
        System.out.print("Last name : ");
        String lName = scanner.nextLine();
        try{
             artistId= artistService.searchArtist(fName, lName);
            if (artistId != -1)
                System.out.println("Artist Found!");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return artistId;
    }
    public void updateArtist(Scanner scanner){
        int artistId = searchArtist(scanner);

        if(artistId !=-1) {
            System.out.println("Enter New Artist Name");
            System.out.print("First Name: ");
            String fName = scanner.nextLine();
            System.out.print("Last Name : ");
            String lName = scanner.nextLine();
            try {
                boolean isUpdated = artistService.updateArtist(artistId, fName, lName);
                if (isUpdated)
                    System.out.println("Successfully updated.");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else
            System.out.println("Artist Not Found! ");
    }
    public void deleteArtist(Scanner scanner){
        int artistId = searchArtist(scanner);
        if(artistId != -1) {
            try {
                boolean isDeleted = artistService.deleteArtist(artistId);
                if (isDeleted)
                    System.out.println("Successfully Deleted.");
                else
                    System.out.println("Something went Wrong!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else
            System.out.println("Artist Not Found! ");
    }




}
