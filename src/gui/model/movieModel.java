package gui.model;

import be.Movie;
import bll.movieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class movieModel {
    private movieManager myMovieManager;
    private ObservableList<Movie> allMovies;

    public movieModel() throws SQLException, IOException {
        myMovieManager = new movieManager();
        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(myMovieManager.getAllMovies());
    }

    public void addMovie(String movieTitle, String imdbRating, String filePath) throws SQLException {
        myMovieManager.addMovie(movieTitle, imdbRating, filePath);
    }

    public List<Movie> getAllMovies() throws SQLException {
        return myMovieManager.getAllMovies();
    }

    public void updateAllMovies() throws SQLException {
        allMovies.clear();
        allMovies.addAll(myMovieManager.getAllMovies());
    }
}
