package org.musicapp.service;

import org.musicapp.repo.AlbumRepo;
import org.musicapp.model.Album;

import java.sql.SQLException;
import java.util.List;

public class AlbumService {
    private AlbumRepo albumDAO = new AlbumRepo();

    public void addAlbum(Album album) throws SQLException {
        albumDAO.addAlbum(album);
    }
    public void removeAlbum(int albumId) throws SQLException {
        searchAlbum(albumId);
        albumDAO.removeAlbum(albumId);
    }
    public List<Album> getAlbumList() throws SQLException {
        if(albumDAO.getAlbumList().isEmpty()){
            throw new SQLException("No albums found");
        }
        return albumDAO.getAlbumList();
    }
    public void updateAlbum(Album album) throws SQLException{
        searchAlbum(album.getAlbumId());
        albumDAO.updateAlbum(album);
    }
    public void searchAlbum(int id) throws SQLException {
        Album album = albumDAO.getAlbum(id);
        if(album == null) {
            throw new SQLException("Album not found");
        }
    }
}
