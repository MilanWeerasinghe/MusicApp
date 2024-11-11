package org.musicapp.service;

import org.musicapp.dao.ArtistDAO;
import org.musicapp.model.Artist;

import java.sql.SQLException;
import java.util.List;

public class ArtistService {
    private ArtistDAO artistDAO = new ArtistDAO();

    public void addArtist(Artist artist) throws SQLException {
        artistDAO.addArtist(artist);
    }

    public List<Artist> getAllArtist() throws SQLException {
        return artistDAO.getArtistList();
    }

    public void deleteArtist(int artistId) throws SQLException {
        artistDAO.removeArtist(artistId);
    }

    public void updateArtist(Artist artist) throws SQLException {
        artistDAO.updateArtist(artist);
    }
    public Artist searchArtist(int id) throws SQLException {
        return artistDAO.getArtist(id);
    }
}
