package com;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertEquals;

public class MusicBoxTest {

    MusicBox _musicBox;
    private Song _beautifulSong;

    @Before
    public void setup() {
        _musicBox = new MusicBox();
        Playlist playlist = new Playlist();
        _beautifulSong = new Song(100, "1", "a");
        Song song2 = new Song(200, "2", "b");
        Song song3 = new Song(300, "3", "c");
        Song song4 = new Song(400, "4", "d");

        playlist.setSongs(Arrays.asList(_beautifulSong, song2, song3, song4));
        _musicBox.addPlaylist(playlist);

    }

    @Test
    public void testBasicStuff() {
        assertEquals(1, _musicBox.getNumberOfPlaylist());
        assertFalse(_musicBox.isPlaying());
    }

    @Test
    public void testChangeSongs() {
        _musicBox.nextSong();
        assertEquals(2, _musicBox.getCurrentSongPlayingIndex());
        assertTrue(_musicBox.isPlaying());
    }

    @Test
    public void testPlaySong() {
        _musicBox.play();
        assertEquals(1, _musicBox.getCurrentSongPlayingIndex());
        assertTrue(_musicBox.isPlaying());
        _musicBox.stop();
        assertFalse(_musicBox.isPlaying());
    }

    @Test
    public void testChangePlaylist() {
        Playlist newPlaylist = new Playlist();
        Song onlySong = new Song(123, "12", "l");
        newPlaylist.addSong(onlySong);
        _musicBox.addPlaylist(newPlaylist);
        _musicBox.changePlaylist(2);

        assertEquals(newPlaylist, _musicBox.getCurrentPlaylist());
    }

    @Test
    public void testCurrentPlayingSong() {
        _musicBox.play();
        assertEquals(_beautifulSong.getName(), _musicBox.getCurrentSongPlaying().getName());
        _musicBox.stop();
        assertNull(_musicBox.getCurrentSongPlaying());
    }

    @Test
    public void testEmptyMusicBox() {
        MusicBox newMusicBox = new MusicBox();
        assertNull(newMusicBox.getCurrentPlaylist());
    }

    @Test
    public void testChangeToValidPlaylist() {
        _musicBox.changePlaylist(5);
        //should do nothing
        assertEquals(1, _musicBox.getCurrentSongPlayingIndex());
    }

    @Test
    public void testStartSong() {
        _musicBox.startSong(3);
        assertEquals(3, _musicBox.getCurrentSongPlayingIndex());
        _musicBox.stop();
        _musicBox.nextSong();
        assertEquals(4, _musicBox.getCurrentSongPlayingIndex());
        assertTrue(_musicBox.isPlaying());
    }
}
