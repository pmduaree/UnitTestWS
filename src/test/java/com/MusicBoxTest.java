package com;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class MusicBoxTest {

    MusicBox _musicBox;

    @Before
    public void setup() {
        _musicBox = new MusicBox();
        Playlist playlist = new Playlist();
        Song song1 = new Song(100, "1", "a");
        Song song2 = new Song(200, "2", "b");
        Song song3 = new Song(300, "3", "c");
        Song song4 = new Song(400, "4", "d");

        playlist.setSongs(Arrays.asList(song1, song2, song3, song4));
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
    public void changePlaylist() {
        Playlist newPlaylist = new Playlist();
        Song onlySong = new Song(123, "12", "l");
        newPlaylist.addSong(onlySong);
        _musicBox.addPlaylist(newPlaylist);
        _musicBox.changePlaylist(2);

        assertEquals(newPlaylist, _musicBox.getCurrentPlaylist());
    }
}
