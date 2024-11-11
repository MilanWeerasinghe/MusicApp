package org.musicapp.dao;
import org.musicapp.model.Song;
import org.musicapp.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

//    Add a Song to the Database
    public void addSong(Song song) throws SQLException {
        String sql = "INSERT INTO song(songTitle, duration, albumId, artistId) VALUES(?, ?, ?, ?) ";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, song.getSongTitle());
            preparedStatement.setString(2, song.getDuration());
            preparedStatement.setInt(3, song.getAlbumId());
            preparedStatement.setInt(4, song.getArtistId());
            preparedStatement.executeUpdate();
        }
    }

//    Get All Available Songs from Database
    public List<Song> getAllSongs() throws SQLException {
        List<Song> songs = new ArrayList<>();

        String sql = "SELECT * FROM song";
        try (Connection connection = DBConnection.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

        while (resultSet.next()) {
            Song song = new Song(
                    resultSet.getInt("songId"),
                    resultSet.getInt("artistId"),
                    resultSet.getInt("albumId"),
                    resultSet.getString("songTitle"),
                    resultSet.getString("duration"));
            songs.add(song);
        }
        return songs;
        }
    }

//    Get a Song By Title
    public Song searchASongByTitle(String title) throws SQLException{
        String sql = "SELECT * FROM song WHERE songTitle = ?";
        try(Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,title);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                Song song = new Song(
                        resultSet.getInt("songId"),
                        resultSet.getInt("artistId"),
                        resultSet.getInt("albumId"),
                        resultSet.getString("songTitle"),
                        resultSet.getString("duration"));
                return song;
            }
        }
        return null;
    }

    public List<Song> searchASongByArtistId(int artistId) throws SQLException{
        List<Song> songList = new ArrayList<>();
        String sql = "SELECT * FROM Song WHERE artistId = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
             statement.setInt(1, artistId);
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Song song = new Song(
                        resultSet.getInt("songId"),
                        resultSet.getInt("artistId"),
                        resultSet.getInt("albumId"),
                        resultSet.getString("songTitle"),
                        resultSet.getString("duration"));
                songList.add(song);
            }
            return songList;
        }
    }

//    Update a Song Details in Database
    public void updateASong(Song song) throws SQLException{
        String sql = "UPDATE song SET songTitle= ?, duration = ? WHERE songId= ? ";
        try(Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, song.getSongTitle());
            statement.setString(2,song.getDuration());
            statement.setInt(3,song.getSongId());
            statement.executeUpdate();
        }
    }

//    Delete a Song From Database
    public void deleteASong(int sondId) throws SQLException{
        String sql = "DELETE FROM song WHERE songId = ? ";
        try(Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, sondId);
            preparedStatement.executeUpdate();
        }
    }
}
