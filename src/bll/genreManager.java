package bll;

import be.Movie;
import be.Playlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class genreManager {
    private genreManager myGenreManager;
    private ObservableList<Playlist> allPlaylists;
    private ObservableList<String> allGenres;

    public genreManager(){
        myGenreManager = new genreManager();
        allPlaylists = FXCollections.observableArrayList();
        allGenres = FXCollections.observableArrayList();
    }

    // TODO getAllGenres()
    // TODO getAllPlaylists()
    // TODO addGenre()
    // TODO deleteGenre()
    // TODO addAssociation()
    // TODO deleteAssociation()
    // TODO updateAllGenres()
    // TODO updateAllPlaylists()

}
