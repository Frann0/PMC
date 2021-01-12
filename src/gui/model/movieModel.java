package gui.model;

import be.*;

import bll.movieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class movieModel {
    private movieManager myMovieManager;
    private ObservableList<Movie> allMovies;


    public movieModel() throws SQLException, IOException {
        myMovieManager = new movieManager();
        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(myMovieManager.getAllMovies());
    }

    /**
     * Adds a movie to the database, and updated the allMovies list.
     * @param movieTitle Title of the movie to be added.
     * @param imdbRating Imdb rating of the movie to be added.
     * @param filePath Filepath of the movie to be added.
     * @throws SQLException
     */
    public void addMovie(String movieTitle, int imdbRating, String filePath) throws SQLException {
        myMovieManager.addMovie(movieTitle, imdbRating, filePath);
        updateAllMovies();
    }

    /**
     * Delete a movie from the database.
     * @param title Title of the movie to be deleted.
     * @throws SQLException
     */
    public void deleteMovie(String title) throws SQLException {
        myMovieManager.deleteMovie(title);
    }

    /**
     * Get all movies from the database.
     * @return a list of all movies.
     * @throws SQLException
     */
    public List<Movie> getAllMovies() throws SQLException {
        return myMovieManager.getAllMovies();
    }

    /**
     * Update the allMovies list.
     * @throws SQLException
     */
    public void updateAllMovies() throws SQLException {
        allMovies.clear();
        allMovies.addAll(myMovieManager.getAllMovies());
    }

    /**
     * Get a list of all movies within specified genre.
     * @param genre The genre specifying movies to be selected.
     * @return a list of movies within the specified genre.
     */
    public ObservableList<Movie> moviesByGenre(String genre){
        ObservableList<Movie> moviesByGenre = FXCollections.observableArrayList();
        for(Movie mov : allMovies){
            if(mov.getGenres().contains(genre)){
                moviesByGenre.add(mov);
            }
        }
        return moviesByGenre;
    }

    /**
     * Updates a movie in the database.
     * @param movieTitle Title of the movie to be updated.
     * @param newGenres List of new genres of the movie.
     * @param newPersonalRating New personal rating of the movie.
     * @throws SQLException
     */
    public void updateMovie(String movieTitle, List<String> newGenres, int newPersonalRating) throws SQLException {
        // Update movie info in allMovies
        for(Movie mov : allMovies){
            if (mov.getTitle().equals(movieTitle)){
                mov.setPersonalRating(newPersonalRating);
                mov.setGenres(newGenres);
            }
        }
        // Update movie info in database
        myMovieManager.updateMovie(movieTitle, newGenres, newPersonalRating);
    }

    public String getOldMovies(){
        String oldMovies = "";

        for (Movie mov : allMovies){
            if(mov.getLastViewed() != null){
                if (mov.getLastViewed().plusYears(2).compareTo(LocalDate.now()) < 0){
                    String movieString = mov.getTitle() + " was last seen on: " + mov.getLastViewed() + ".";
                    oldMovies += movieString + "\r\n";
                }
            }
        }
        return oldMovies;
    }

    public void updateArtPath(String movieTitle, String path) throws SQLException {
        myMovieManager.updateArtPath(movieTitle, path);
    }

    public void updateLastViewed(String title, LocalDate now) throws SQLException {
        myMovieManager.updateLastViewed(title, now);
    }

    public ObservableList<Movie> movieSearch(Search search) throws IOException {
        return myMovieManager.movieSearch(search, allMovies);
    }

    /*
    public String getBadMovies(){
        String badMovies = null;

        for (Movie mov : allMovies){
            if(mov.getPersonalRating() != 0){
                int personalRating = mov.getPersonalRating();
                if(personalRating < 5){
                    String movieString = "";
                    movieString = mov.getTitle() + " has a personal rating of: " + mov.getPersonalRating() + ".";
                    badMovies += movieString + "\r\n";
                }
            }
        }
        return badMovies;
    }

     */
}
