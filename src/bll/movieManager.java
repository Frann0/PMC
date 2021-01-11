package bll;

import be.Movie;
import dal.movieDAL;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public void updateLastViewed(String movieTitle, LocalDate now) throws SQLException {
        myMovieDAL.updateLastViewed(movieTitle, now);
    }


}
