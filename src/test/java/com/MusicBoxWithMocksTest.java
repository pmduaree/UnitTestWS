package com;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;


public class MusicBoxWithMocksTest {

    MusicBox _musicBox;

    @Before
    public void setup() {
        Playlist playlist = createMock(Playlist.class);
        expect(playlist.getNumberOfSongs()).andReturn(2);
        List<Playlist> playlists = new ArrayList<Playlist>() ;
        playlists.add(playlist);
        replay(playlist);
        _musicBox = new MusicBox(playlists);
    }

    @Test
    public void testBasicStuff() {
        assertEquals(1, _musicBox.getNumberOfPlaylist());
        assertFalse(_musicBox.isPlaying());
        assertEquals(2, _musicBox.getCurrentPlaylist().getNumberOfSongs());
    }
}
