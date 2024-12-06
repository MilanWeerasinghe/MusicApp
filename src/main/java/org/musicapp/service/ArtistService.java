package org.musicapp.service;

import org.musicapp.repo.ArtistRepo;
import org.musicapp.model.Artist;

import java.sql.SQLException;
import java.util.List;

public class ArtistService {
    private ArtistRepo artistDAO = new ArtistRepo();

    public void addArtist(Artist artist) throws Exception {
        if(artist.getAge()<=15){
            throw new Exception("Artist age must be at least 15");
        }
        artistDAO.addArtist(artist);
    }

    public List<Artist> getAllArtist() throws Exception {
        if(artistDAO.getArtistList().isEmpty()){
            throw new Exception("No artist found");
        };
        return artistDAO.getArtistList();
    }

    public void deleteArtist(int artistId) throws Exception {
        searchArtist(artistId);
        artistDAO.removeArtist(artistId);
    }

    public void updateArtist(Artist artist) throws Exception {
        searchArtist(artist.getArtistId());
        artistDAO.updateArtist(artist);
    }
    public Artist getArtistById(int id) throws Exception {
        searchArtist(id);
        return artistDAO.getArtist(id);
    }

    public void searchArtist(int id) throws Exception {
        Artist artist = artistDAO.getArtist(id);
        if(artist == null){
            throw new Exception("No artist found in that id");
        }
    }
}
