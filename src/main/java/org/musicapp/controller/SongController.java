package org.musicapp.controller;

import org.musicapp.model.Song;
import org.musicapp.service.SongService;
import org.musicapp.util.UserAuth;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SongController {
    private SongService songService = new SongService();
    private UserAuth userAuth = new UserAuth();


    public void manageSongs() throws SQLException {
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
                    if (userAuth.isAdmin()) addSong(scanner);
                    break;
                case 4:
                    if (userAuth.isAdmin()) updateSong(scanner);
                    break;
                case 5:
                    if (userAuth.isAdmin()) deleteASong(scanner);
                    break;
                case 6:
                    return;
            }
        }
    }

    public void addSong(Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Enter Song Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Duration  : ");
        String duration = scanner.nextLine();
        System.out.print("Enter Artist ID : ");
        int artistId = scanner.nextInt();
        System.out.print("Enter Album ID : ");
        int albumId = scanner.nextInt();

        songService.addSong(new Song(artistId,albumId,title,duration));
    }

    public void listAllSongs() throws SQLException {
        List<Song> songList = songService.getAllSongs();
        songList.forEach(song -> System.out.println(
                song.toString() + "\n" + "-------------------------------"
        ));
    }

    public void searchSongByTitle(Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Enter song title to search: ");
        String title = scanner.nextLine();

        Song song = songService.searchASongByTitle(title);
        if (song != null) System.out.println(song);
    }

    public void updateSong(Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.println("Enter Song ID: ");
        int songId = scanner.nextInt();
        System.out.print("Enter Song Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Duration  : ");
        String duration = scanner.nextLine();
        System.out.print("Enter Artist ID : ");
        int artistId = scanner.nextInt();
        System.out.print("Enter Album ID : ");
        int albumId = scanner.nextInt();

        songService.updateASong(new Song(songId,artistId,albumId,title,duration));
    }
    public void deleteASong(Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Enter song ID: ");
        int songId = scanner.nextInt();

        songService.deleteASong(songId);
    }
}