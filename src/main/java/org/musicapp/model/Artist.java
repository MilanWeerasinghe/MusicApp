package org.musicapp.model;

import java.util.ArrayList;

public class Artist {
    private int artistId;
    private String artistName;
    private int age;
    private ArrayList<Song> songs;

    public Artist(String artistName, int age) {
        this.songs = new ArrayList<Song>();
        setAge(age);
        setArtistName(artistName);
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", age=" + age +
                ", songs=" + songs +
                '}';
    }
}
