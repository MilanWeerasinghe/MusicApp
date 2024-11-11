package org.musicapp.model;

public class Artist {
    private int artistId;
    private String artistFName;
    private String artistLName;
    private int age;

    public Artist(){}


    public Artist(String artistFName, String artistLName, int age) {
        this.artistLName = artistLName;
        this.artistFName = artistFName;
        this.age = age;
    }

    public Artist(int artistId, String fName, String lName, int age) {
        this.artistId = artistId;
        this.artistFName = fName;
        this.artistLName = lName;
        this.age = age;
    }

    public int getArtistId() {
        return artistId;
    }

    public String getArtistFName() {
        return artistFName;
    }

    public String getArtistLName() {
        return artistLName;
    }

    public int getAge() {return age;}


    public void setArtistFName(String artistFName) {
        this.artistFName = artistFName;
    }
    public void setArtistLName(String artistLName) {
        this.artistLName = artistLName;
    }
    public void setAge(int age) {
        this.age = age;
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
