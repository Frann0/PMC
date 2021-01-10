package bll;

import be.Movie;
import dal.movieDAL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class movieManager {

    private movieDAL myMovieDAL;

    public movieManager() throws IOException {
        myMovieDAL = new movieDAL();
    }

    public void addMovie(String movieTitle, int imdbRating, String filePath) throws SQLException {
        myMovieDAL.addMovie(movieTitle, imdbRating, filePath);
    }

    public List<Movie> getAllMovies() throws SQLException {
        return myMovieDAL.getAllMovies();
    }

    public void updateMovie(String movieTitle, List<String> newGenres, int newPersonalRating) throws SQLException {
        myMovieDAL.updateMovie(movieTitle, newGenres, newPersonalRating);
    }

    public void deleteMovie(String title) throws SQLException {
        myMovieDAL.deleteMovie(title);
    }

    // TODO deleteMovie
    // TODO updateMovie

}
