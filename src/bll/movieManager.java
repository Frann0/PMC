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

    public void addMovie(String movieTitle, String imdbRating, String filePath) throws SQLException {
        myMovieDAL.addMovie(movieTitle, imdbRating, filePath);
    }

    public List<Movie> getAllMovies() throws SQLException {
        return myMovieDAL.getAllMovies();
    }

    // TODO deleteMovie
    // TODO updateMovie

}
