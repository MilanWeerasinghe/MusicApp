package org.musicapp.service;

import org.musicapp.dao.AlbumDAO;
import org.musicapp.model.Album;

import java.sql.SQLException;
import java.util.List;

public class AlbumService {
    private AlbumDAO albumDAO = new AlbumDAO();

    public void addAlbum(Album album) throws SQLException {
        albumDAO.addAlbum(album);
    }
    public void removeAlbum(int albumId) throws SQLException {
        albumDAO.removeAlbum(albumId);
    }
    public List<Album> getAlbumList() throws SQLException {
        return albumDAO.getAlbumList();
    }
    public void updateAlbum(Album album) throws SQLException{
        albumDAO.updateAlbum(album);
    }
}
