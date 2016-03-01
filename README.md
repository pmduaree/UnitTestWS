# UnitTestWS

> WITHOUT UNIT TESTING, YOU'RE NOT REFACTORING. YOU'RE JUST CHANGING SHIT
> 
> -Hamlet D' Arcy

## Intro

This workshops is about unit testing. It is focused to students and people who doens't know a thing about TDD and unit testing.

This WS is using: Java, maven, junit and easyMock.

Link to presentation: [slides] (http://slides.com/pedroduarte/unit-testing-and-test-driv/live)

## Objectives

* Know what is Test Driven Development philosophy
* Write a unit test
* Create assertions
* Use mock to create easier way to test.

## How this workshop goes

This workshop is divided in 5 levels:

* 0: Set everything up. 
* 1: Something simple. Assertions.
* 2: Something complex. How classes interact with them.
* 3: Something more complex. After and Before annotations
* 4: Refactoring. 
* 5: Mocks.

If you need to go back to a level or got stuck and want to switch levels, just do:

`git checkout .` 
`git checkout level-n` where n is the level

Note: for level 0, type `master` instead of `level-n`

#What are we gonna do

In this workshop we are gonna create a Music Box, or something like that. 

We will have 3 classes: Song, Playlist and MusicBox. Each of them will have a responsibility and their own code. You will see later. 

# Lets get started!

## Level 0:

Clone this repository. After that, just try

`mvn test`
 
 
 and the result should have something like:
``` shell
Results :
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
```
You can use your favorite editor: sublime, eclipse, notepad++, whatever!.

## Level 1:

Here, we will learn the power of assertions!

You have a behavior you want to test. So, when you create something you *expect* something when you create the behavior. 
 
In the test, you are gonna *assert* what you expect and what you have. For example:

``` java
assertEquals(100, cellphone.batteryLevel());
```

Here you are expecting that the object cellphone to have 100 in batteryLevel. This can happen, in a controlled scenario, of course.

We are gonna create a Song class with 3 attributes: duration, name and artist. 

## Level 2:

In this level we are gonna create something more complex: the playlist. 

The playlist will have the following behavior:

* Add, get and delete songs.
* Get total duration of all songs
* Get number of songs
* Get total of artists in the playlist.

We are gonna write the unit test first to check the behavior and then the class.

## Level 3:

It's time to write the music box! 

The music box should be able to:

* Add playlists.
* Get total playlists
* Go to next song
* Get current song playing
* Play and stop the song
* Change playlists
* The next song should not play song

After we create everything, we are gonna see a lot of repeated code. Should we be able to fix it? 

Lets see how the @Before and @After tags work.

## Level 4:

Why don't we add new behavior to the music box?

* Get current song playing
* Get current playlist
* Validate some stuff
* The next song button should play the next song


The code is starting to stink. Why not we refactor some things and add new things?

## Level 5:

Creating new songs and stuff is tiring, is there a better way? Of course! We are gonna use stubs.

First, we need to modify pom.xml:

``` xml
<dependency>
	<groupId>org.mockito</groupId>
	<artifactId>mockito-all</artifactId>
	<version>1.9.5</version>
</dependency>
```

And after that, we are gonna mock some behavior. But first, some refactor to the MusicBox class.

``` java
Playlist playlist = createMock(Playlist.class);
expect(playlist.getNumberOfSongs()).andReturn(2);
List<Playlist> playlists = new ArrayList<Playlist>() ;
playlists.add(playlist);
replay(playlist);
_musicBox = new MusicBox(playlists);
      
```

## Conclusion

And that's it! There are some more stuff to learn for you. You just have to search in the internet for it. 

And remember, try to cover your code with test so other devs doesn't screw up your work!
