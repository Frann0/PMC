package bll;

import be.*;
import dal.movieDAL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class movieManager {

    private movieDAL myMovieDAL;
    private movieSearcher myMovieSearcher;

    public movieManager() throws IOException {
        myMovieDAL = new movieDAL();
    }

    /**
     * Add a movie to the database.
     * @param movieTitle Title of the movie to be added.
     * @param imdbRating Imdb rating of the movie to be added.
     * @param filePath Filepath of the movie to be added.
     * @throws SQLException
     */
    public void addMovie(String movieTitle, int imdbRating, String filePath) throws SQLException {
        myMovieDAL.addMovie(movieTitle, imdbRating, filePath);
    }

    /**
     * Get all movies in the database.
     * @return a List<Movie> with all movies.
     * @throws SQLException
     */
    public List<Movie> getAllMovies() throws SQLException {
        return myMovieDAL.getAllMovies();
    }

    /**
     * Update a selected movie in the database.
     * @param movieTitle Title of the movie to be updated.
     * @param newGenres All genres to be added to the movie.
     * @param newPersonalRating New personal rating of the movie to be updated.
     * @throws SQLException
     */
    public void updateMovie(String movieTitle, List<String> newGenres, int newPersonalRating) throws SQLException {
        myMovieDAL.updateMovie(movieTitle, newGenres, newPersonalRating);
    }

    /**
     * Delete a movie from the database.
     * @param title Title of the movie to be deleted.
     * @throws SQLException
     */
    public void deleteMovie(String title) throws SQLException {
        myMovieDAL.deleteMovie(title);
    }

    public void updateArtPath(String movieTitle, String path) throws SQLException {
        myMovieDAL.updateArtPath(movieTitle, path);
    }

    public void updateLastViewed(String title, LocalDate now) throws SQLException {
        myMovieDAL.updateLastViewed(title, now);
    }

    /**
     * Generates a list of all movies that match the search.
     * @param search Search object containing all search criteria.
     * @return a list of all matching movies.
     * @throws IOException
     */
    public ObservableList<Movie> movieSearch(Search search, List<Movie> allMovies) throws IOException {
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
}
