package com;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SongTest {

    @Test
    public void testSong() {
        Song song = new Song(200, "Never gonna give you up", "Rick Astley");
        assertEquals(200, song.getSeconds());
        assertEquals("Never gonna give you up", song.getName());
        assertEquals("Rick Astley", song.getArtist());
    }

    @Test
    public void testSong2() {
        Song song = new Song();
        song.setArtist("Rick Astley");
        song.setName("Never gonna give you up");
        song.setSeconds(200);

        assertEquals("Rick Astley", song.getArtist());
        assertEquals("Never gonna give you up", song.getName());
        assertEquals(200, song.getSeconds());
    }
}