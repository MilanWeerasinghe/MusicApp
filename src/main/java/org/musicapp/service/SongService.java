package org.musicapp.service;

import org.musicapp.repo.SongRepo;
import org.musicapp.model.Song;

import java.sql.SQLException;
import java.util.List;

public class SongService {
    private SongRepo songDAO = new SongRepo();

    public void addSong(Song song) throws SQLException {
        songDAO.addSong(song);
    }

    public List<Song> getAllSongs() throws Exception{
        if(songDAO.getAllSongs().isEmpty()){
            throw new Exception("No songs found");
        }
        return songDAO.getAllSongs();
    }

    public void searchASongByTitle(String title) throws Exception{
        if(songDAO.searchASongByTitle(title) == null){
            throw new Exception("No song found in that name");
        }
    }
    public Song getSongByTitle(String title) throws Exception{
        searchASongByTitle(title);
        return songDAO.searchASongByTitle(title);
    }
    public List<Song> searchASongByArtistId(int songId) throws Exception{
        if(songDAO.searchASongByArtistId(songId).isEmpty()){
            throw new Exception("No song found in that artist");
        }
        return songDAO.searchASongByArtistId(songId);
    }

    public void updateASong(Song song) throws Exception{
        searchASongByTitle(song.getSongTitle());
        songDAO.updateASong(song);
    }

    public void deleteASong(String title)throws Exception{
        searchASongByTitle(title);
        songDAO.deleteASong(title);
    }
}
