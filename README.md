# UnitTestWS

> SIN PRUEBAS UNITARIAS, NO ESTÁS REFACTORIZANDO. ESTÁS CAMBIANDO MIERDA.
> 
> -Hamlet D' Arcy

## Introduccion

Este taller es acerca de pruebas unitarias. Está enfocado para estudiantes y personas que no conocen acerca de test driven development y pruebas unitarias.

En este workshop usaremos git, Java, maven, Junit y easyMock. En este taller se supone que ya tienes el ambiente de Java, git y maven corriendo. 

## Objetivos

* Escribir una prueba unitaria
* Crear aserciones 
* Usar mocks para facilitar las cosas

## Como funciona este taller

Este taller está dividio en 5 niveles:

* 0: Comprobar que sirve tu ambiente.
* 1: Algo sencillo. Aserciones.
* 2: Algo complejo. Como las clases interactuan entre ellas.
* 3: Algo más complejo. Anotaciones @After y @Before.
* 4: Refactorizar.
* 5: Mocking.

Si ocupas cambiar de nivel por que estás atorado, solo escribe:

`git checkout .` 
`git checkout level-n` donde n es el nivel

Nota: para el nivel 0, escribe `master` en lugar de `level-n`

## Que haremos en este taller

En este taller crearemos una caja de música.

Crearemos 3 clases: Song (canción), Playlist (lista de reproducción) y MusicBox (caja de música). Cada una de ellas tendrá su propia responsabilidad y su propio código. Más tarde verán.

# Lets get started!

## Nivel 0:

Lee que es una prueba unitaria y que es Test Driven Development:
 
* [Prueba Unitaria](https://es.wikipedia.org/wiki/Prueba_unitaria)
* [Test Driven Development](https://es.wikipedia.org/wiki/Desarrollo_guiado_por_pruebas) 

Ya que entiendas estos conceptos nos movemos a la consola (la que gustes). Clona este repositorio en el directorio que gustes:

`git clone https://github.com/pmduaree/UnitTestWS`

Ya que lo tengas, nos movemos a la carpeta que se acaba de crear y corremos:

`mvn test`
 
 y después de un rato, la consola debe de desplegar algo así:
``` shell
Results :
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
```

Si sale esto, ya nos podemos mover a escribir código. Nos movemos a tu editor favorito y comenzamos.

## Nivel 1:

En este nivel aprenderás el podel de las aserciones!

Tu tienes un comportamiento que quiere probar. Entonces, cuando tu creas algo tu *esperas* un resultado cuando programas el comportamiento. Por ejemplo, si tienes una función llamada ```getName``` (obtener nombre), tu esperas que la función regrese una cadena de caracteres con el nombre.
 
En esta prueba, tu vas a *asertar* lo que esperas y lo que tienes. Por ejemplo:

``` java
assertEquals(100, celular.nivelDeBateria());
```
Aquí, tu esperas que el objeto celular  tenga un nivel de bateria de 100. Esto puede pasar, en un escenario controlado. 

Vamos a crear una clase llamada Song (canción) con 3 atributos diferentes: duration (duración de la canción), name (nombre de la canción) y artist (artista de la canción).

Antes de crear la clase song, primero escribiremos la prueba. De esta manera estamos aplicando test driven development. 

El archivo de prueba se va a llamar ```SongTest.java``` y estará en la carpeta ```UnitTestWS/src/test/java/com/```

Esta es una clase que tiene adentro las pruebas. Usaremos este archivo como plantilla:

``` java
    
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
```

Analicemos lo que puede causar ruido:

* 1: Las librerías
```java 
import org.junit.Test;
import static org.junit.Assert.assertEquals;
```

Aquí estamos importando JUnit, que es la librería que usaremos para controlar cuales son las pruebas y las aserciones. 

* 2: @Test
``` java
    @Test
    public void testSong() {
```

@Test es una notación de la librería JUnit para especificarle al sistema cuales son las funciones a probar. Con solo especificar esto, ya le damos una entrada a que se corra esta función.

* 3: assertEquals

``` java
assertEquals(200, song.getSeconds());
```

La función ``` assertEquals``` es la función de la librería JUnit que toma 2 parámetros: el valor esperado y el valor real. Si en este caso no llegaran a ser iguales, tiraría una excepción y nos diría que hay algo mal.

Después de entender acerca de esto, vamos a programas la clase Song. Creamos un archivo ```Song.java``` en la carpeta ```UnitTestWS/src/main/java/com/```. Te la puedes aventar solo, solo debe de tener las funciones de setear y obtener las 3 propiedades que mencionamos antes: 
``` java
private int _seconds;
private String _name;
private String _artist
```

Inténtalo por tu cuenta, si no puedes, aquí hay una clase base:

``` java
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
```

Ahora, ejecuta en la terminal ```mvn test``` y veras ahora que hay 3 pruebas. Si no marca error, pasemos al nivel 2.

## Nivel 2:

En este nivel, vamos a crear algo más complicado: la lista de reproducción.

La lista de reproducción debe de tener el siguiente comportamiento:

* Agregar, obtener y borrar canciones.
* Obtener la duración de la playlist.
* Obtener el número de canciones.
* Obtener el número de artistas.

Igual que en el nivel 1, escribiremos primero la prueba para comprobar el comportamiento y después la clase.

La prueba debe de probar este comportamiento. Va a estar en el mismo directorio que la prueba pasada.

En esta prueba, vamos a agregar estas canciones antes de cada prueba:

``` java
Song song1 = new Song(100, "1", "a");
Song song2 = new Song(200, "2", "b");
Song song3 = new Song(300, "3", "c");
Song song4 = new Song(400, "4", "d");
```

Y algunos casos: 

* 1: Probar el agregar canciones y al mismo tiempo el numero total de canciones:

``` java
assertEquals(3, _playlist.getNumberOfSongs());
_playlist.addSong(new Song(500, "10", "x"));
assertEquals(4, _playlist.getNumberOfSongs());
```

* 2: Probar la duración:

``` java
assertEquals(1000, _playlist.getTotalDuration());
_playlist.addSong(new Song(500, "10", "x"));
assertEquals(1500, _playlist.getTotalDuration());
```

* 3: Probar número de artistas

``` java
assertEquals(4, _playlist.getNumberOfArtists());
_playlist.addSong(new Song(500, "15", "b"));
assertEquals(4, _playlist.getNumberOfArtists());
```

Íntentalo primero. Si no puedes, esta es la prueba: 

``` java

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
```
Ignora las anotaciones @Before y @After, lo veremos en el siguiente nivel.

Ahora, crearemos el comportamiento de la clase playlist. 

Inténtalo. Si no puedes, aquí está la clase:

``` java
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
```

Corremos en consola ```mvn test``` y deben de estar pasando 7 pruebas ahora. Si es así, pasamos al nivel 3

## Nivel 3:

En este nivel escribiremos la caja de música. Esta sera capaz de:

* Agregar listas de reproducciones
* Obtener el numero total de listas de reproducciones
* Tocar la siguiente canción
* Obtener cual es la canción que está tocando actualmente
* Tocar y detener la canción actual
* Cambiar la lista de reproduccion

Igual que en el nivel anterior, vamos a crear aserciones para cada regla que pusimos arriba, es decir (si quieres intentar crear tu propia prueba, adelante!)

* 1: Cosas básicas

``` java
assertEquals(1, _musicBox.getNumberOfPlaylist());
assertFalse(_musicBox.isPlaying());
```

* 2: Cambiar canciones
``` java
_musicBox.nextSong();
assertEquals(2, _musicBox.getCurrentSongPlayingIndex());
assertFalse(_musicBox.isPlaying());
```

* 3: Tocar y detener canciones
``` java
_musicBox.play();
assertEquals(1, _musicBox.getCurrentSongPlayingIndex());
assertTrue(_musicBox.isPlaying());
_musicBox.stop();
assertFalse(_musicBox.isPlaying());
``` 

* 4: Cambiar listas de reproducciones
``` java
Playlist newPlaylist = new Playlist();
Song onlySong = new Song(123, "12", "l");
newPlaylist.addSong(onlySong);
_musicBox.addPlaylist(newPlaylist);
_musicBox.changePlaylist(2);
assertEquals(newPlaylist, _musicBox.getCurrentPlaylist());
```

Primero, recuerdas las anotaciones ```@Before``` y ```@After```? Las funciones que especifiquemos con ```@Before``` se va a ejecutar antes de cada prueba. Es decir, si tenemos 3 ```@Test``` en la clase, quiere decir que la función que especificamos con estas anotaciones se ejecutarán 3 veces. Esto llega a ser útil cuando ocupamos utilizar lo mismo en cada prueba, como un set up. En este caso, inicializaremos las canciones y las listas de reproducción en la función ```@Before```. 

Ya tenenmos las pruebas, ahora a escribir el código de la clase. Tómate tu tiempo para escribirla. Si no puedes, aquí hay una implementación:

``` java
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
        return _playlists.get(_currentPlaylist - 1);
    }
}
```

A partir de este punto, ya tienes lo básico para sobre unit testing. Si quieres continuar para algo un poco más avanzado, adelante!

## Nivel 4:

Vamos a agregar cosas nuevas a la caja de música. No se explicará nada de pruebas unitarias, pero si sobre refactorizar un poco lo que ya tenemos.

Agregaremos el siguiente comportamiento a la caja de música:

* Obtener la canción que se está tocando
* Obtener la lista de musica que se está tocando
* Validaciones

El punto importante es que ahora tenemos un poco más de trabajo: Qué pasa si queremos escoger una lista de reproducción que no existe? Entonces, nuestro trabajo es validar estos puntos y regresar algo que sea válido. Para ello tendremos que mejorar un poco nuestras pruebas para que cubran con esos casos. 

Escribamos una pruebas que cubran esto: 

``` java
_musicBox.changePlaylist(5); // no existe, no debe de cambiar de playlist
assertEquals(1, _musicBox.getCurrentSongPlayingIndex());
```

Puedes agregar las pruebas que quieras que cubran todos los casos. Pero recuerda: Si escribes pruebas de más que ya están cubriendo estos casos, al momento de correrlas será tardado!

Ahora vamos a agregar validaciones para esto: (ya saben, primero inténtenlo y luego pueden copiar el código si no lo lograron). Por ejemplo, para este caso sería:

``` java
private boolean isAbleToChange(int index, int upperBundary) {
    return index > 0 && index <= upperBundary;
}

public void changePlaylist(int index) {
    if (isAbleToChange(index, _playlists.size() + 1)) {
        _currentPlaylist = index;
    }
}
```

Ya con este nivel, ya tienen una mejor idea de como funciona el agregar nuevos casos en las pruebas que ya tenemos. No olviden de correr las pruebas. 
Si llega a fallar una, las tenemos que arreglar!

## Nivel 5:

Agregar canciones una por una puede ser tedioso. Habrá una forma de hacerse más fácil? Claro que si, y se llama **mocking**. 

Un mock es el simular un resultado cuando no tenemos el comportamiento. Es como una caja negra que no sabemos como funciona pero si sabemos el resultado. Puedes leer un poco más sobre eso [aquí](http://www.vogella.com/tutorials/EasyMock/article.html). 

Para ello, primero vamos a modificar el archivo ```pom.xml``` que está en su carpeta raíz y le agregamos esto dentro de las etiquetas ```<dependencies></dependencies>```

``` xml
<dependency>
	<groupId>org.mockito</groupId>
	<artifactId>mockito-all</artifactId>
	<version>1.9.5</version>
</dependency>
```

Despueés de esto, simularemos el comportamiento de la lista de reproducciones. Pero primero, haremos un refactor a la caja de música. Agregaremos un constructor que acepte como parámetro una lista de reproducciones:

``` java 
public MusicBox(List<Playlist> playlists) {
    _playlists = playlists;
    _playing = false;
    _currentSongPlaying = 1;
    _currentPlaylist = 1;
}
```

Todos los demás datos son de control.

Ahora, crearemos un nuevo archivo de pruebas (para tener un poco más limpio y organizado todo el sistema de pruebas). Dentro de ella pondremos una función con anotación ```@Befoe``` y metemos este código.

``` java
Playlist playlist = createMock(Playlist.class);
expect(playlist.getNumberOfSongs()).andReturn(2).anyTimes();
List<Playlist> playlists = new ArrayList<Playlist>() ;
playlists.add(playlist);
replay(playlist);
_musicBox = new MusicBox(playlists);
```

Con esto tendremos el comportamiento simulado de una lista de reproducción cada vez que corramos la prueba. La función de ```createMock``` "instanciará" un objeto de tipo Playlist. Con ```expect``` le daremos el comportamiento que deseamos, es decir, si llamaramos el método de ```getNumberOfSongs``` de esta instancia simulada, siempre nos regresaría 2, sin importar cuantas canciones le metamos. 

Ahora escribamos la prueba de esto:

``` java
@Test
public void testBasicStuff() {
    assertEquals(1, _musicBox.getNumberOfPlaylist());
    assertFalse(_musicBox.isPlaying());
    assertEquals(2, _musicBox.getCurrentPlaylist().getNumberOfSongs());
}
```

Viste como si pasaron las pruebas? Ésto es gracias a que internamente musicBox está mandando a llamar la función que nostros hemos simulado.

El simular estas funciones nos es útil cuando la interacción entre clases es complicada. 

## Conclusión

Recuerda que las pruebas unitarias solo deben de probar una sola cosa, como lo dice su nombre. Entonces, te preguntarás, lo que hicimos en los niveles 1 al 4 está mal? La respuesta corta es: si. La larga es: si, por que estamos probando la interacción entre clases y, como te habrás dado cuenta, ya es más de 1 cosa. Es aquí donde los mocks nos son útiles.

Y estas son las pruebas unitarias! De que te pueden servir? Esto te llega a ser útil cuando escribes código y sabes que alguien más le va a meter mano. Le pones una especie de candado en el que sabes que lo que tu escribas tendrá el comportamiento que desees.

Hay muchas cosas más por aprender, solo tienes que buscar en el internet. 

> And remember, try to cover your code with test so other devs doesn't screw up your work!
