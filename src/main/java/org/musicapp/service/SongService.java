package org.musicapp.service;

import org.musicapp.dao.SongDAO;
import org.musicapp.model.Song;

import java.sql.SQLException;
import java.util.List;

public class SongService {
    private SongDAO songDAO = new SongDAO();

    public boolean addSong(Song song) throws SQLException {
        return songDAO.addSong(song);
    }

    public List<Song> getAllSongs() throws SQLException{
        return songDAO.getAllSongs();
    }

    public Song searchASongByTitle(String title) throws SQLException{
        return songDAO.searchASongByTitle(title);
    }

    public boolean updateASong(Song song) throws SQLException{
        return songDAO.updateASong(song);
    }

    public boolean deleteASong(String title)throws SQLException{
        return songDAO.deleteASong(title);
    }
}
