package org.musicapp.service;

import org.musicapp.dao.SongDAO;
import org.musicapp.model.Song;

import java.sql.SQLException;
import java.util.List;

public class SongService {
    private SongDAO songDAO = new SongDAO();

    public void addSong(Song song) throws SQLException {
        songDAO.addSong(song);
    }

    public List<Song> getAllSongs() throws SQLException{
        return songDAO.getAllSongs();
    }

    public Song searchASongByTitle(String title) throws SQLException{
        return songDAO.searchASongByTitle(title);
    }
    public List<Song> searchASongByArtistId(int songId) throws SQLException{
        return songDAO.searchASongByArtistId(songId);
    }

    public void updateASong(Song song) throws SQLException{
        songDAO.updateASong(song);
    }

    public void deleteASong(int id)throws SQLException{
        songDAO.deleteASong(id);
    }
}
