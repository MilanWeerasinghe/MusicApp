package org.musicapp.model;

import java.util.ArrayList;

public class Album {
    private int albumId;
    private String albumName;
    private ArrayList<Song> songs;

    public Album(String albumName) {
        this.songs = new ArrayList<Song>();
        setAlbumName(albumName);
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Song searchSong(String title){
        for(Song checkSong: songs){
           if (checkSong.getSongTitle().equals(title))
               return checkSong;
        }
        return null;
    }
    public boolean addSong(Song song){
        boolean isExists = false;
        boolean isAdded = false;

        for(Song checkSong : songs){
            if (checkSong.getSongTitle().equals(song.getSongTitle())) {
                isExists = true;
                break;
            }
        }
        if(isExists)
            System.out.println("The song " +song.getSongTitle() + " is already exists.");
        else{
            songs.add(song);
            isAdded = true;
        }
        return isAdded;
    }
    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", songs=" + songs +
                '}';
    }
}
