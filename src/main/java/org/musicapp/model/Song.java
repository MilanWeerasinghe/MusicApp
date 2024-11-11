package org.musicapp.model;

public class Song  {
    private int songId;
    private int artistId;
    private int albumId;
    private String songTitle;
    private String duration;

    public Song(int artistId, int albumId, String songTitle, String duration) {
        this.artistId = artistId;
        this.albumId = albumId;
        this.songTitle = songTitle;
        this.duration = duration;
    }

    public Song(int songId, int artistId, int albumId, String songTitle, String duration) {
        this.songId = songId;
        this.artistId = artistId;
        this.albumId = albumId;
        this.songTitle = songTitle;
        this.duration = duration;
    }

    public Song(){}


    public int getSongId() {
        return songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getDuration() {return duration;}

    public int getArtistId() {return artistId;}

    public int getAlbumId() {return albumId;}


    public void setDuration(String duration) {
        this.duration = duration;
    }
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
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
