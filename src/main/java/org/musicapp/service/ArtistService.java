package org.musicapp.service;

import org.musicapp.dao.ArtistDAO;
import org.musicapp.model.Artist;

import java.sql.SQLException;
import java.util.List;

public class ArtistService {
    private ArtistDAO artistDAO = new ArtistDAO();

    public boolean addArtist(Artist artist) throws SQLException{
        return artistDAO.addArtist(artist);
    }

    public List<Artist> getAllArtist() throws SQLException{
        return artistDAO.getAllArtist();
    }

    public boolean deleteArtist(String fName, String lName) throws SQLException{
        return artistDAO.deleteArtist(fName, lName);
    }
}
