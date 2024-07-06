package org.musicapp.dao;

import org.musicapp.util.DBConnection;
import org.musicapp.model.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArtistTable {
    private Connection conn= null;

    public ArtistTable(){
    }

    public void addArtist(Artist artist) throws SQLException {
        conn = DBConnection.getConnection();
        String sql = "insert into artist(name, age) values(?, ?) ";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,artist.getArtistName());
        preparedStatement.setInt(2,artist.getAge());
        int rows = preparedStatement.executeUpdate();

        if(rows>0){
            System.out.println("Added Successfully.");
        }
        conn.close();

    }
}
