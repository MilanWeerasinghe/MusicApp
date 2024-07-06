package org.musicapp.model;

public class Song  {
    private int songId;
    private String songTitle;
    private Artist artist;
    private String duration;
    private Album album;


    public Song(){}

    public Song(int id, String songTitle, String duration){
        setSongTitle(songTitle);
        setDuration(duration);
        setSongId(id);
    }

    public Song(String songTitle, String duration){
        setSongTitle(songTitle);
        setDuration(duration);
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return
                "Song Id    : " + songId + "\n" +
                "Song Title : " + songTitle + "\n" +
                "Duration   : " + duration + "\n"
                ;
    }
}
