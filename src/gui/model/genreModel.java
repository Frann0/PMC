package gui.model;

import be.Playlist;
import bll.genreManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;


public class genreModel {
    private genreManager myGenreManager;
    private ObservableList<Playlist> allPlaylists;
    private ObservableList<String> allGenres;


    public genreModel() throws IOException, SQLException {
        myGenreManager = new genreManager();
        allPlaylists = FXCollections.observableArrayList();
        allGenres = FXCollections.observableArrayList();
        allGenres.addAll(myGenreManager.getAllGenres());
    }

    public void addGenre(String genre) throws SQLException {
        myGenreManager.addGenre(genre);
        allGenres.add(genre);
    }

    public ObservableList<String> getAllGenres() throws SQLException {
        return allGenres;
    }

    public void updateAllGenres() throws SQLException {
        allGenres.clear();
        allGenres.addAll(myGenreManager.getAllGenres());
    }

    public void deleteGenre(String genre){
        allGenres.remove(genre);
        try {
            myGenreManager.deleteGenre(genre);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
