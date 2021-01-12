package bll;

import be.Movie;
import dal.MovieDAL;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MovieManager {

    private MovieDAL myMovieDAL;

    public MovieManager() throws IOException {
        myMovieDAL = new MovieDAL();
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
}
