package org.musicapp.model;


public class Album {
    private int albumId;
    private String albumName;

    public Album() {}

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public Album(int albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }


    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                '}';
    }
}
