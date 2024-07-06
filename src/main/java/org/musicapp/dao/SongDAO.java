package org.musicapp.dao;
import org.jetbrains.annotations.NotNull;
import org.musicapp.model.Song;
import org.musicapp.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {
    private Connection connection = null;

//    Add a Song to the Database
    public boolean addSong(@NotNull Song song) throws SQLException {
        boolean status = false;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "INSERT INTO songs(songTitle, duration, artistId) VALUES(?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, song.getSongTitle());
            preparedStatement.setString(2, song.getDuration());
            preparedStatement.setInt(3, song.getSongId());
            int rows = preparedStatement.executeUpdate();
            status = rows > 0;
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            DBConnection.closeConnection(connection);
        }
        return status;
    }

//    Get All Available Songs from Database
    public List<Song> getAllSongs() throws SQLException {
        connection = DBConnection.getConnection();
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM songs";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            Song song = new Song();
            song.setSongId(resultSet.getInt("songId"));
            song.setSongTitle(resultSet.getString("songTitle"));
            song.setDuration(resultSet.getString("duration"));
            songs.add(song);
        }
        return songs;
    }

//    Get a Song By Title
    public Song searchASongByTitle(String title) throws SQLException{
        connection = DBConnection.getConnection();
        Song song = null;
        String sql = "SELECT * FROM songs WHERE songTitle = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,title);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            song = new Song();
            song.setSongId(resultSet.getInt("songId"));
            song.setSongTitle(resultSet.getString("songTitle"));
            song.setDuration(resultSet.getString("duration"));
        }
        return song;
    }

//    Update a Song Details in Database
    public boolean updateASong(Song song) throws SQLException{
        connection = DBConnection.getConnection();
        boolean status = false;

        String sql = "UPDATE songs SET songTitle= ?, duration = ? WHERE songId= ? ";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, song.getSongTitle());
            statement.setString(2,song.getDuration());
            statement.setInt(3,song.getSongId());
            int rows = statement.executeUpdate();

            status = rows>0;

        }
        return status;
    }

//    Delete a Song From Database
    public boolean deleteASong(String title) throws SQLException{
        connection = DBConnection.getConnection();
        boolean status = false;
        String sql = "DELETE FROM songs WHERE songTitle = ? ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            int rows = preparedStatement.executeUpdate();
            status = rows>0;
        }
        return status;
    }
}
