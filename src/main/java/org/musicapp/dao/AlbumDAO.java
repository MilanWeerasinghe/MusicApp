package org.musicapp.dao;

import org.musicapp.model.Album;
import org.musicapp.model.Song;
import org.musicapp.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    public void addAlbum(Album album) throws SQLException{
        String sql = "insert into album(albumName) values(?)";
        try(Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);) {
            preparedStatement.setString(1, album.getAlbumName());
            preparedStatement.executeUpdate();
        }
    }

    public void removeAlbum(int albumId) throws SQLException{
        String sql = "DELETE album WHERE albumId = ?";
        try(Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, albumId);
            preparedStatement.executeUpdate();
        }
    }

    public void updateAlbum(Album album) throws SQLException{
        String sql = "UPDATE album SET albumName= ? WHERE albumId= ? ";
        try(Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, album.getAlbumName());
            preparedStatement.setInt(2, album.getAlbumId());
            preparedStatement.executeUpdate();
        }
    }

    public List<Album> getAlbumList() throws SQLException{
        List<Album> albumList = new ArrayList<>();
        String sql = "SELECT * FROM album";
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement=connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Album album = new Album(
                        resultSet.getInt("albumId"),
                        resultSet.getString("albumName"));
                albumList.add(album);
            }
            return albumList;
        }
    }







}
