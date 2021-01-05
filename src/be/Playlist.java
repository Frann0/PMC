package be;

import java.util.List;

public class Playlist {
    String playlistName;
    List<Movie> movies;

    public Playlist(String playlistName, List<Movie> movies){
        this.playlistName = playlistName;
        this.movies = movies;
    }
}
