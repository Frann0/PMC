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

    public ObservableList<Movie> moviesByGenre(String genre){
        ObservableList<Movie> moviesByGenre = FXCollections.observableArrayList();
        for(Movie mov : allMovies){
            if(mov.getGenres().contains(genre)){
                moviesByGenre.add(mov);
            }
        }
        return moviesByGenre;
    }

    public void updateMovie(String movieTitle, String newGenres, String newPersonalRating){
        String[] tmpGenres = newGenres.split(",");
        List<String> newGenreList = new ArrayList<>();
        for (int i = 0; i < tmpGenres.length; i++){
            newGenreList.add(tmpGenres[i].toUpperCase());
        }

        for(Movie mov : allMovies){
            if (mov.getTitle().equals(movieTitle)){
                mov.setPersonalRating(newPersonalRating);
                mov.setGenres(newGenreList);
            }
        }
    }
}
