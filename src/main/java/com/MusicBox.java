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

    public int getNumberOfPlaylist() {
        return _playlists.size();
    }

    public boolean isPlaying() {
        return _playing;
    }

    public void startSong(int index) {
        if (isAbleToChange(index, getCurrentPlaylist().getNumberOfSongs())) {
            _currentSongPlaying = index;
            play();
        }
    }

    public void nextSong() {
        int index = _currentSongPlaying + 1;
        if (isAbleToChange(index, getCurrentPlaylist().getNumberOfSongs())) {
            _currentSongPlaying = index;
            play();
        }
    }

    private boolean isAbleToChange(int index, int upperBundary) {
        return index > 0 && index <= upperBundary;
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
        if (isAbleToChange(index, _playlists.size() + 1)) {
            _currentPlaylist = index;
        }
    }

    public Playlist getCurrentPlaylist() {
        if (isAbleToChange(_currentPlaylist, _playlists.size())) {
            return _playlists.get(_currentPlaylist - 1);
        } else {
            return null;
        }
    }

    public Song getCurrentSongPlaying() {
        if (!isPlaying()) {
            return null;
        }
        return getCurrentPlaylist().getSong(_currentSongPlaying - 1);
    }
}
