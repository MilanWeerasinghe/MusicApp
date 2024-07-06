package org.musicapp.controller;

import org.musicapp.model.Song;
import org.musicapp.service.SongService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SongController {
    private SongService songService = new SongService();

    public void manageSongs() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
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
                    addSong(scanner);
                    break;
                case 4:
                    updateSong(scanner);
                    break;
                case 5:
                    deleteASong(scanner);
                    break;
                case 6:
                    return;
            }

        }
    }

    public void addSong(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter Song Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Duration  : ");
        String duration = scanner.nextLine();

        Song song = new Song(title, duration);

        try {
            boolean isAdded = songService.addSong(song);
            if(isAdded)
                System.out.println("The song " + "'" + title + "'" + " is successfully added.");
            else
                System.out.println("Something went Wrong...");
        } catch (SQLException e) {
            e.printStackTrace();
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
            System.out.println(song.toString());
        }catch (SQLException e){
            System.out.println("Can't find a song in that name..");
        }
    }

    public void updateSong(Scanner scanner){
        scanner.nextLine();
        System.out.print("Enter song title to search: ");
        String title = scanner.nextLine();

        try{
            Song song = songService.searchASongByTitle(title);
            if(!(song == null)) {
                int songId = song.getSongId(); // Get the Song ID of the song to be updated
                try {
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
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("Can't find a song in that name..");
        }catch (SQLException e){
            e.printStackTrace();
        }
        scanner.nextLine();
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