package com;

import java.util.ArrayList;
import java.util.List;

public class MusicBox {
    List<Playlist> _playlists;
    private boolean _playing;
    private int _currentSongPlaying;
    private int _currentPlaylist;

    public MusicBox() {
        _playlists = new ArrayList<Playlist>();
        _playing = false;
        _currentSongPlaying = 1;
        _currentPlaylist = 1;
    }

    public void addPlaylist(Playlist playlist) {
        _playlists.add(playlist);
    }

    public void setPlaylists(List<Playlist> playlists){
        _playlists = playlists;
    }

    public List<Playlist> getPlaylist() {
        return _playlists;
    }

    public int getNumberOfPlaylist() {
        return _playlists.size();
    }

    public boolean isPlaying() {
        return _playing;
    }

    public void setPlaying(boolean playing) {
        _playing = playing;
    }

    public void startSong(int index) {
        _currentSongPlaying = index;
    }

    public void nextSong() {
        _currentSongPlaying++;
    }

    public int getCurrentSongPlayingIndex() {
        return _currentSongPlaying;
    }

    public void play() {
        _playing = true;
    }

    public void stop() {
        _playing = false;
    }

    public void changePlaylist(int index) {
        _currentPlaylist = index;
    }

    public Playlist getCurrentPlaylist() {
        return _playlists.get(_currentPlaylist-1);
    }
}
