package org.musicapp.dao;

import org.musicapp.util.DBConnection;
import org.musicapp.model.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlbumTable {
    private Connection conn = null;

    public AlbumTable(){
    }

    public void addAlbum(Album album) throws SQLException{
        conn = DBConnection.getConnection();
        String sql = "insert into album(albumName) values(?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,album.getAlbumName());
        int rows = preparedStatement.executeUpdate();

        if(rows>0){
            System.out.println("Added successfully.");
        }
        conn.close();
    }
}
