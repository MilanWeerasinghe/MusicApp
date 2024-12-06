package org.musicapp.repo;

import org.musicapp.util.DBConnection;
import org.musicapp.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepo {

    public List<Artist> getArtistList() throws SQLException{
        List<Artist> artistList = new ArrayList<>();
        String sql = "SELECT * FROM artist";

        try (Connection conn = DBConnection.getInstance();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()) {
                Artist artist =  new Artist(
                        resultSet.getInt("artistId"),
                        resultSet.getString("fName"),
                        resultSet.getString("lName"),
                        resultSet.getInt("age"));

                artistList.add(artist);
            }
            return artistList;
        }

    }


    public Artist getArtist(int id) throws SQLException{
        String sql = "SELECT * from artist WHERE artistId = ? ";

        try(Connection conn = DBConnection.getInstance();
            PreparedStatement statement = conn.prepareStatement(sql)){

            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return new Artist(
                        resultSet.getInt("artistId"),
                        resultSet.getString("fName"),
                        resultSet.getString("lName"),
                        resultSet.getInt("age")
                );
            }
        }
        return null;
    }

    public void addArtist(Artist artist) throws SQLException {
        String sql = "insert into artist(fName, lName, age) values(?, ?, ?) ";
        try (Connection conn = DBConnection.getInstance();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, artist.getArtistFName());
            preparedStatement.setString(2, artist.getArtistLName());
            preparedStatement.setInt(3, artist.getAge());
            preparedStatement.executeUpdate();
        }
    }

    public void removeArtist(int artistId) throws SQLException {
        String sql = "DELETE FROM artist WHERE artistId = ?";
        try (Connection conn = DBConnection.getInstance();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, artistId);
            statement.executeUpdate();
        }
    }

    public void updateArtist(Artist artist) throws SQLException {
        String sql = "UPDATE artist SET fName = ? ,lName = ? ,age=? WHERE artistId = ?";
        try (Connection conn = DBConnection.getInstance();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, artist.getArtistFName());
            statement.setString(2, artist.getArtistLName());
            statement.setInt(3, artist.getAge());
            statement.setInt(4, artist.getArtistId());
            statement.executeUpdate();
        }
    }



}
