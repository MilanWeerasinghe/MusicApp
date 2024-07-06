package org.musicapp.controller;

import org.musicapp.model.Song;
import org.musicapp.model.User;
import org.musicapp.service.SongService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SongController {
    private SongService songService = new SongService();
    private UserController userController = new UserController();


    public void manageSongs() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (!userController.isAuthenticated())
                userController.login();
            else{
                System.out.println("1. List Songs");
                System.out.println("2. Get Song by Title");
                System.out.println("3. Create Song");
                System.out.println("4. Update Song Details");
                System.out.println("5. Delete Song");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        listAllSongs();
                        break;
                    case 2:
                        searchSongByTitle(scanner);
                        break;
                    case 3:
                        if (userController.isAdmin()) addSong(scanner);
                        break;
                    case 4:
                        if (userController.isAdmin()) updateSong(scanner);
                        break;
                    case 5:
                        if (userController.isAdmin()) deleteASong(scanner);
                        break;
                    case 6:
                        return;
                }
            }
        }
    }

    public void addSong(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter Song Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Duration  : ");
        String duration = scanner.nextLine();
        System.out.print("Enter Artist ID : ");
        int artistId = scanner.nextInt();

        Song song = new Song(artistId, title, duration);

        try {
            boolean isAdded = songService.addSong(song);
            if (isAdded)
                System.out.println("The song " + "'" + title + "'" + " is successfully added.");
        } catch (SQLException e) {
            if (e.getSQLState().startsWith("23")) { // SQLState 23 indicates constraint violations
                System.out.println("Error: Duplicate record found!");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void listAllSongs() {
        try {
            List<Song> songs = songService.getAllSongs();
            for (Song song : songs) {
                if (song != null) {
                    System.out.println(song.toString());
                    System.out.println("---------------------");
                } else {
                    System.out.println("Song not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchSongByTitle(Scanner scanner){
        scanner.nextLine();
        System.out.print("Enter song title to search: ");
        String title = scanner.nextLine();
        try {
            Song song = songService.searchASongByTitle(title);
            if (song != null)
                System.out.println(song.toString());
            else
                System.out.println("Can't find a song in that name..");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateSong(Scanner scanner) {
        scanner.nextLine();
        Song song = null;
        System.out.print("Enter song title to search: ");
        String title = scanner.nextLine();
        try{
            song = songService.searchASongByTitle(title);
            if (!(song == null)) {
                int songId = song.getSongId(); // Get the Song ID of the song to be updated

                System.out.print("Enter new Song Title: ");
                String newTitle = scanner.nextLine();
                System.out.print("Enter new Duration: ");
                String duration = scanner.nextLine();
                song = new Song(songId, newTitle, duration);

                boolean isUpdated = songService.updateASong(song);
                if (isUpdated)
                    System.out.println("Song updated successfully");
                else
                    System.out.println("Update Unsuccessful");
            }
            else
                System.out.println("Can't find a song in that name..");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void deleteASong(Scanner scanner){
        scanner.nextLine();
        System.out.print("Enter song title to delete: ");
        String title = scanner.nextLine();
        try{
            boolean isDeleted = songService.deleteASong(title);
            if (isDeleted) {
                System.out.println("Song deleted successfully");
            } else {
                System.out.println("Song with title " + title + " does not exist");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}