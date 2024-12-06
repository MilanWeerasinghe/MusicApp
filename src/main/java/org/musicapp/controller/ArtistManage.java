package org.musicapp.controller;

import org.musicapp.model.Artist;
import org.musicapp.model.Song;
import org.musicapp.service.ArtistService;
import org.musicapp.service.SongService;
import org.musicapp.util.UserAuth;
import org.musicapp.util.ExceptionHandling;
import java.util.List;
import java.util.Scanner;

public class ArtistManage {
    private ArtistService artistService = new ArtistService();
    private SongService songService = new SongService();
    private UserAuth userAuth = new UserAuth();

    public void manageArtist(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Artist");
            System.out.println("2. Get All Artists");
            System.out.println("3. Get Artist");
            System.out.println("4. Update Artist");
            System.out.println("5. Delete Artist");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if(userAuth.isAdmin()) addArtist(scanner);
                    break;
                case 2:
                    getAllArtist();
                    break;
                case 3:
                    getArtist(scanner);
                    break;
                case 4:
                    if(userAuth.isAdmin()) updateArtist(scanner);
                    break;
                case 5:
                    if(userAuth.isAdmin()) deleteArtist(scanner);
                    break;
                case 6:
                    return;
            }
        }
    }

    public void addArtist(Scanner scanner) {
        try{
            scanner.nextLine();

            System.out.print("Enter Artist First Name: ");
            String userFName = scanner.nextLine();
            System.out.print("Enter Artist Last Name : ");
            String userLName = scanner.nextLine();
            System.out.print("Enter Artist Age : ");
            int age = scanner.nextInt();

            Artist artist = new Artist(userFName, userLName, age);
            artistService.addArtist(artist);
        }catch (Exception e) {
            ExceptionHandling.printError(e.getMessage());
        }
    }

    public void getAllArtist(){
        try{
            List<Artist> artists;
            artists = artistService.getAllArtist();
            artists.forEach(artist -> System.out.println(
                    artist.toString()+ "\n" + "---------------------"
            ));
        }catch (Exception e) {
            ExceptionHandling.printError(e.getMessage());
        }
    }

    public void getArtist(Scanner scanner){
        try{
            scanner.nextLine();
            System.out.print("Artist id: ");
            int id = scanner.nextInt();
            Artist artist = artistService.getArtistById(id);
            List<Song> songs = songService.searchASongByArtistId(id);

            System.out.println(artist);
            System.out.println("Songs:");
            for (Song song : songs) {
                System.out.println(song.toString());
            }
        }catch (Exception e) {
            ExceptionHandling.printError(e.getMessage());
        }
    }


    public void updateArtist(Scanner scanner){
        try{
            scanner.nextLine();
            System.out.print("Artist id: ");
            int id = scanner.nextInt();

            artistService.searchArtist(id);

            scanner.nextLine();
            System.out.println("Enter New Artist Name");
            System.out.print("First Name: ");
            String fName = scanner.nextLine();
            System.out.print("Last Name : ");
            String lName = scanner.nextLine();
            System.out.print("Age : ");
            int age = scanner.nextInt();

            artistService.updateArtist(new Artist(id, fName, lName, age));
        }catch (Exception e) {
            ExceptionHandling.printError(e.getMessage());
        }
    }

    public void deleteArtist(Scanner scanner){
        try{
            scanner.nextLine();
            System.out.print("Artist id: ");
            int id = scanner.nextInt();
            artistService.deleteArtist(id);
        }catch (Exception e) {
            ExceptionHandling.printError(e.getMessage());
        }
    }
}

