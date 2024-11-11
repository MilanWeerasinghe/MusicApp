package org.musicapp.dao;

import org.musicapp.model.Song;
import org.musicapp.util.DBConnection;
import org.musicapp.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistDAO {
    private Connection conn= null;

//    ADD artist to the Database
    public boolean addArtist(Artist artist) throws SQLException {
        PreparedStatement preparedStatement = null;
        boolean status= false;
        try{
            conn = DBConnection.getConnection();
            String sql = "insert into artist(fName, lName, age) values(?, ?, ?) ";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,artist.getArtistFName());
            preparedStatement.setString(2,artist.getArtistLName());
            preparedStatement.setInt(3,artist.getAge());
            int rows = preparedStatement.executeUpdate();

            status = rows>0;

        }finally {
            if (preparedStatement != null) preparedStatement.close();
            DBConnection.closeConnection(conn);
        }
        return status;
    }
//    Search artist by title and return Artist ID
    public int searchArtist(String fName, String lName) throws SQLException{
        int artistId = -1;
        PreparedStatement statement = null;
        try{
            conn = DBConnection.getConnection();
            String sql = "SELECT artistId FROM artist WHERE fName = ? AND lName = ? ";
            statement = conn.prepareStatement(sql);
            statement.setString(1,fName);
            statement.setString(2,lName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                artistId = resultSet.getInt("artistId");
            }
        }finally {
            if(statement!= null) statement.close();
            DBConnection.closeConnection(conn);
        }
        return artistId;
    }
//    Update artist details in Database
    public boolean updateArtist(int id, String fName, String lName) throws SQLException{
        boolean status = false;
        PreparedStatement statement = null;
        try{
            conn = DBConnection.getConnection();
            String sql = "UPDATE artist SET fName = ? , lName = ? WHERE artistId = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1,fName);
            statement.setString(2,lName);
            statement.setInt(3,id);
            int rows = statement.executeUpdate();
            status = rows>0;
        }finally {
            if(statement!=null) statement.close();
            DBConnection.closeConnection(conn);
        }
        return status;
    }
//    Get the artist's details with Songs
    public List<Artist> getAllArtist() throws SQLException{
        Map<Long, Artist> artistMap = new HashMap<>();

        Statement statement = null;
        try{
            conn = DBConnection.getConnection();
            String sql = " SELECT artist.artistId,artist.fName, artist.Lname, artist.age, " +
                    "songs.songTitle, songs.songId, songs.duration " +
                    "FROM artist LEFT JOIN songs ON artist.artistId = songs.artistId;";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                long artistId = resultSet.getInt("artistId");
                String fName = resultSet.getString("fName");
                String lName = resultSet.getString("lName");
                int age = resultSet.getInt("age");
                // Create a Song object to get the data from DB
                Song song = new Song();
                song.setSongId(resultSet.getInt("songId"));
                song.setSongTitle(resultSet.getString("songTitle"));
                song.setDuration(resultSet.getString("duration"));

                // Create or retrieve the artist
                Artist artist = artistMap.computeIfAbsent(artistId, id -> new Artist((int) artistId,fName,lName,age));
                // Add song to the artist's list
                artist.addSong(song);
            }
            return new ArrayList<>(artistMap.values());
        }finally {
            if (statement != null) statement.close();
            DBConnection.closeConnection(conn);
        }
    }

    public boolean deleteArtist(int artistId) throws SQLException{
        boolean status = false;
        PreparedStatement statement = null;
        try{
            conn = DBConnection.getConnection();
            String sql = "DELETE FROM artist WHERE artistId = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1,artistId);
            int rows = statement.executeUpdate();
            status = rows>0;
        }finally {
            if(statement != null) statement.close();
            DBConnection.closeConnection(conn);
        }
        return status;
    }



}
