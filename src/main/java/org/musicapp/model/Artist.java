package org.musicapp.model;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private int artistId;
    private String artistFName;
    private String artistLName;
    private int age;
    private List<Song> songs;

    public Artist(){}

    public Artist(String artistFName, String artistLName, int age) {
        setAge(age);
        setArtistFName(artistFName);
        setArtistLName(artistLName);
    }

    public Artist(int artistId, String artistFName, String artistLName, int age) {
        setArtistId(artistId);
        setAge(age);
        setArtistFName(artistFName);
        setArtistLName(artistLName);
        this.songs = new ArrayList<>();
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistFName() {
        return artistFName;
    }

    public String getArtistLName() {
        return artistLName;
    }

    public void setArtistFName(String artistFName) {
        this.artistFName = artistFName;
    }

    public void setArtistLName(String artistLName) {
        this.artistLName = artistLName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
    }
    @Override
    public String toString() {
        return  "Artist ID : " + artistId + "\n" +
                "First Name: " + artistFName + "\n" +
                "Last Name : " + artistLName + "\n" +
                "Age       : " + age + "\n"
                ;
    }
}
