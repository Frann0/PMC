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

    public void addMovie(String movieTitle, int imdbRating, String filePath) throws SQLException {
        myMovieManager.addMovie(movieTitle, imdbRating, filePath);
        updateAllMovies();
    }

    public void deleteMovie(String title) throws SQLException {
        myMovieManager.deleteMovie(title);
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

    public ObservableList<Movie> movieSearch(Srch search) throws IOException {
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

    private int determineSearchType(Srch search){
        int searchType = 0;

        // Only search rating
        if (search.getRating() != -1 && search.getFilterStrings().isEmpty()
        && search.getGenreTokens().isEmpty()){
            searchType = 1;
        }
        // Only search rating + genre
        else if (search.getRating() != -1 && search.getFilterStrings().isEmpty()
                && !search.getGenreTokens().isEmpty()){
            searchType = 2;
        }
        // Only search rating + title
        else if (search.getRating() != -1 && !search.getFilterStrings().isEmpty()
                && search.getGenreTokens().isEmpty()){
            searchType = 3;
        }
        // Only search title + genre
        else if (search.getRating() == -1 && !search.getFilterStrings().isEmpty()
                && !search.getGenreTokens().isEmpty()){
            searchType = 4;
        }
        // Only search title
        else if (search.getRating() == -1 && !search.getFilterStrings().isEmpty()
                && search.getGenreTokens().isEmpty()){
            searchType = 5;
        }
        // Only search genre
        else if (search.getRating() == -1 && search.getFilterStrings().isEmpty()
                && !search.getGenreTokens().isEmpty()){

            searchType = 6;
        }
        // Search rating + title + genre
        else if (search.getRating() != -1 && !search.getFilterStrings().isEmpty()
                && !search.getGenreTokens().isEmpty()){
            searchType = 7;
        }
        System.out.println(searchType);
        return searchType;
    }

    public boolean ratingMatch(Movie mov, Srch search){
        myMovieSearcher = new movieSearcher(new SrchRating());
        return myMovieSearcher.executeSearch(mov, search);
    }

    public boolean genreMatch(Movie mov, Srch search){
        myMovieSearcher = new movieSearcher(new SrchGenre());
        return myMovieSearcher.executeSearch(mov, search);
    }

    public boolean titleMatch(Movie mov, Srch search){
        myMovieSearcher = new movieSearcher(new SrchTitle());
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
