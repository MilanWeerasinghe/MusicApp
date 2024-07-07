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
                    addArtist(scanner);
                    break;
                case 2:
                    getAllArtist();
                    break;
                case 4:
                    deleteArtist(scanner);
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

    public void deleteArtist(Scanner scanner){
        scanner.nextLine();
        String firstName = null;
        String lastName = null;
        System.out.print("Enter Artist first name and last name to delete: ");
        String artistName = scanner.nextLine();
        // Split the string by space and assign to variables
        String[] parts = artistName.split("\\s+", 2); // Limit to 2 parts

        // Check if the input contains at least two parts
        if (parts.length >= 1) {
            firstName = parts[0];
//            lastName = parts[1];
        }
        System.out.println(firstName);
        System.out.println(lastName);

        try {
            boolean isDeleted = artistService.deleteArtist(firstName, lastName);
            if(isDeleted)
                System.out.println("Artist Successfully Deleted.");
            else
                System.out.println("Something went Wrong!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }




}
