package com;

public class Song {

    private int _seconds;
    private String _name;
    private String _artist;

    public Song() {
    }

    public Song(int seconds, String name, String artist) {
        _seconds = seconds;
        _name = name;
        _artist = artist;
    }

    public int getSeconds() {
        return _seconds;
    }

    public void setSeconds(int seconds) {
        _seconds = seconds;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getArtist() {
        return _artist;
    }

    public void setArtist(String artist) {
        _artist = artist;
    }
}
