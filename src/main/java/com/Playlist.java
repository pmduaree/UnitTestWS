package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Playlist {
    List<Song> _songs;

    public Playlist() {
        _songs = new ArrayList<Song>();
    }

    public void setSongs(List<Song> songs) {
        _songs = songs;
    }

    public void addSong(Song song) {
        _songs.add(song);
    }

    public Song getSong(int index) {
        return _songs.get(index);
    }

    public void deleteSong(int index) {
        _songs.remove(index);
    }

    public int getTotalDuration() {
        int time = 0;
        for (Song song : _songs) {
            time += song.getSeconds();
        }
        return time;
    }

    public int getNumberOfSongs() {
        return _songs.size();
    }

    public int getNumberOfArtists() {
        HashSet<String> artists = new HashSet<String>();
        for (Song song : _songs) {
            artists.add(song.getArtist());
        }
        return artists.size();
    }
}
