package org.musicapp.controller;

import org.musicapp.model.Artist;
import org.musicapp.model.Song;
import org.musicapp.service.ArtistService;
import org.musicapp.service.SongService;
import org.musicapp.util.UserAuth;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ArtistController {
    private ArtistService artistService = new ArtistService();
    private SongService songService = new SongService();
    private UserAuth userAuth = new UserAuth();

    public void manageArtist() throws SQLException {
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

    public void addArtist(Scanner scanner) throws SQLException {
        scanner.nextLine();

        System.out.print("Enter Artist First Name: ");
        String userFName = scanner.nextLine();
        System.out.print("Enter Artist Last Name : ");
        String userLName = scanner.nextLine();
        System.out.print("Enter Artist Age : ");
        int age = scanner.nextInt();

        Artist artist = new Artist(userFName, userLName, age);
        artistService.addArtist(artist);


    }

    public void getAllArtist() throws SQLException {
        List<Artist> artists;
        artists = artistService.getAllArtist();
        artists.forEach(artist -> System.out.println(
                artist.toString()+ "\n" + "---------------------"
        ));
    }

    public void getArtist(Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Artist id: ");
        int id = scanner.nextInt();
        Artist artist = artistService.searchArtist(id);
        List<Song> songs = songService.searchASongByArtistId(id);

        if(artist != null) {
            System.out.println(artist);
        }else System.out.println("not found");

        if (songs.isEmpty()) {
            System.out.println("No songs found for this artist.");
        } else {
            System.out.println("Songs:");
            for (Song song : songs) {
                System.out.println(song.toString());
            }
        }
    }

    public Artist searchArtist(Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Artist id: ");
        int id = scanner.nextInt();

        return artistService.searchArtist(id);
    }

    public void updateArtist(Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Artist id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter New Artist Name");
        System.out.print("First Name: ");
        String fName = scanner.nextLine();
        System.out.print("Last Name : ");
        String lName = scanner.nextLine();
        System.out.print("Age : ");
        int age = scanner.nextInt();

        artistService.updateArtist(new Artist(id, fName, lName, age));

    }

    public void deleteArtist(Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Artist id: ");
        int id = scanner.nextInt();
        artistService.deleteArtist(id);
    }
}

