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
    private movieSearcher myMovieSearcher;

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

    /**
     * Generates a list of all movies that match the search.
     * @param search Search object containing all search criteria.
     * @return a list of all matching movies.
     * @throws IOException
     */
    public ObservableList<Movie> movieSearch(Search search) throws IOException {
        ObservableList<Movie> searchedMovies = FXCollections.observableArrayList();
        int searchtype = determineSearchType(search);

        for(Movie mov: allMovies) {
            switch (searchtype) {
                //Rating
                case 1:
                    if(ratingMatch(mov, search)){
                        searchedMovies.add(mov);
                    }
                    break;
                //Rating + genre
                case 2:
                    if(ratingMatch(mov, search) && genreMatch(mov, search)){
                        searchedMovies.add(mov);
                    }
                    break;
                //Rating + title
                case 3:
                    if(ratingMatch(mov, search) && titleMatch(mov, search)){
                        searchedMovies.add(mov);
                    }
                    break;
                //Title + genre
                case 4:
                    if(titleMatch(mov, search) && genreMatch(mov, search)){
                        searchedMovies.add(mov);
                    }
                    break;
                //Title
                case 5:
                    if(titleMatch(mov, search)){
                        searchedMovies.add(mov);
                    }
                    break;
                //Genre
                case 6:
                    if(genreMatch(mov, search)){
                        searchedMovies.add(mov);
                    }
                    break;
                //Rating + genre + title
                case 7:
                    if(titleMatch(mov, search) && genreMatch(mov, search) &&
                            ratingMatch(mov, search)){
                        searchedMovies.add(mov);
                    }
                    break;
                default:
                    System.out.println("Search failed");
            }
        }
        return searchedMovies;
    }

    /**
     * Determines if the user is searching for title, genre, rating,
     * or any combination of these.
     * @param search Search object containing search criteria.
     * @return an int indicating what type of search the user made.
     */
    private int determineSearchType(Search search){
        int searchType = 0;

        // Only search rating
        if (search.getRating() != -1 && search.getTitleTokens().isEmpty()
        && search.getGenreTokens().isEmpty()){
            searchType = 1;
        }
        // Only search rating + genre
        else if (search.getRating() != -1 && search.getTitleTokens().isEmpty()
                && !search.getGenreTokens().isEmpty()){
            searchType = 2;
        }
        // Only search rating + title
        else if (search.getRating() != -1 && !search.getTitleTokens().isEmpty()
                && search.getGenreTokens().isEmpty()){
            searchType = 3;
        }
        // Only search title + genre
        else if (search.getRating() == -1 && !search.getTitleTokens().isEmpty()
                && !search.getGenreTokens().isEmpty()){
            searchType = 4;
        }
        // Only search title
        else if (search.getRating() == -1 && !search.getTitleTokens().isEmpty()
                && search.getGenreTokens().isEmpty()){
            searchType = 5;
        }
        // Only search genre
        else if (search.getRating() == -1 && search.getTitleTokens().isEmpty()
                && !search.getGenreTokens().isEmpty()){

            searchType = 6;
        }
        // Search rating + title + genre
        else if (search.getRating() != -1 && !search.getTitleTokens().isEmpty()
                && !search.getGenreTokens().isEmpty()){
            searchType = 7;
        }
        return searchType;
    }

    /**
     * Checks to see if the movie's rating is >= the rating specified in search.
     * @param mov The movie to be compared.
     * @param search Search object containing specified rating.
     * @return true if there is a match, else false.
     */
    public boolean ratingMatch(Movie mov, Search search){
        myMovieSearcher = new movieSearcher(new SearchRating());
        return myMovieSearcher.executeSearch(mov, search);
    }

    /**
     * Checks to see if the movie's genre matches the genres specified in search.
     * @param mov The movie to be compared.
     * @param search Search object containing specified genre(s).
     * @return true if there is a match, else false.
     */
    public boolean genreMatch(Movie mov, Search search){
        myMovieSearcher = new movieSearcher(new SearchGenre());
        return myMovieSearcher.executeSearch(mov, search);
    }

    /**
     * Checks to see if the movie's title matches the title string(s) specified in search.
     * @param mov The movie to be compared.
     * @param search Search object containing specified title string(s).
     * @return true if there is a match, else false.
     */
    public boolean titleMatch(Movie mov, Search search){
        myMovieSearcher = new movieSearcher(new SearchTitle());
        return myMovieSearcher.executeSearch(mov, search);
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
