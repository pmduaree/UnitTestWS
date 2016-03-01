package com;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlaylistTest {

    Playlist _playlist;

    @Before
    public void setUp() {
        _playlist = new Playlist();

        Song song1 = new Song(100, "1", "a");
        Song song2 = new Song(200, "2", "b");
        Song song3 = new Song(300, "3", "c");
        Song song4 = new Song(400, "4", "d");

        _playlist.addSong(song1);
        _playlist.addSong(song2);
        _playlist.addSong(song3);
        _playlist.addSong(song4);
    }

    @Test
    public void testGetTotalDuration() throws Exception {
        assertEquals(1000, _playlist.getTotalDuration());
        _playlist.addSong(new Song(500, "10", "x"));
        assertEquals(1500, _playlist.getTotalDuration());
    }

    @Test
    public void testGetNumberOfSongs() throws Exception {
        assertEquals(4, _playlist.getNumberOfSongs());
        _playlist.deleteSong(1);
        assertEquals(3, _playlist.getNumberOfSongs());
        assertEquals("3", _playlist.getSong(1).getName());
    }

    @Test
    public void testGetNumberOfArtists() throws Exception {
        assertEquals(4, _playlist.getNumberOfArtists());
        _playlist.addSong(new Song(500, "15", "b"));
        assertEquals(4, _playlist.getNumberOfArtists());
    }

    @After
    public void tearDown() {
        _playlist = null;
    }
}