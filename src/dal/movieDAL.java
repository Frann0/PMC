package dal;

import be.Movie;
import be.dbConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class movieDAL {
    private dbConnector dbCon = new dbConnector();

    public movieDAL() throws IOException {
    }


    public List<Movie> getAllMovies(){
        List<Movie> allMovies = new ArrayList<>();

        try (Connection con = dbCon.getConnection()) {

            // Get all movies
            PreparedStatement pSql = con.prepareStatement("SELECT * FROM Movie");
            pSql.execute();
            ResultSet resultSet = pSql.getResultSet();

            // Add movies to allMovies
            while (resultSet.next()) {
                String movieTitle = resultSet.getString("Title");
                int imdbRating = resultSet.getInt("ImdbRating");
                String filePath = resultSet.getString("Filepath");
                int personalRating = 0;
                LocalDate lastViewed = null;

                if (resultSet.getString("PersonalRating") != null) {
                    personalRating = resultSet.getInt("PersonalRating");
                }
                if (resultSet.getDate("LastViewed") != null) {
                    lastViewed = resultSet.getDate("LastViewed").toLocalDate();
                }

                Movie tempMovie = new Movie(movieTitle, imdbRating, filePath, personalRating, lastViewed);
                allMovies.add(tempMovie);
            }

            // Get all associations between movies and genres
            String tmpSQL = "SELECT * FROM GenreMovie";
            PreparedStatement pSql2 = con.prepareStatement(tmpSQL,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pSql2.execute();
            ResultSet resultSet2 = pSql2.getResultSet();

            // Add movies to allMovies
            int movieCounter = 0;
            List<String> movieTitles = new ArrayList<>();
            String tempMovieTitle = "";
            while (resultSet2.next()) {
                if (!resultSet2.getString("MovieTitle").equals(tempMovieTitle)) {
                    movieCounter++;
                    movieTitles.add(resultSet2.getString("MovieTitle"));
                    tempMovieTitle = resultSet2.getString("MovieTitle");
                }
            }

            // Add associated genres to movies
            for (int i = 0; i < movieTitles.size(); i++) {
                List<String> genres = new ArrayList<>();
                resultSet2.beforeFirst();
                while (resultSet2.next()) {
                    if (resultSet2.getString("MovieTitle").equals(movieTitles.get(i))) {
                        genres.add(resultSet2.getString("GenreName"));
                    }
                }
                for (Movie mov : allMovies) {
                    if (mov.getTitle().equals(movieTitles.get(i))) {
                        mov.setGenres(genres);
                    }
                }
            }
        } catch (SQLException throwables) {
            ErrorHandler.getAllMovieErr();
        }
        return allMovies;
    }


    public void addMovie(String movieTitle, int imdbRating, String filePath){
        try (Connection con = dbCon.getConnection()) {

            PreparedStatement pSql = con.prepareStatement("INSERT INTO Movie VALUES(?,?,?,?,?)");
            pSql.setString(1, movieTitle);
            pSql.setInt(2, 0);
            pSql.setDate(3, null);
            pSql.setString(4, null);
            pSql.setString(5, null);
            pSql.setInt(5, imdbRating);
            pSql.execute();
        } catch (SQLException throwables) {
            ErrorHandler.addMovieErr();
        }
    }

    public void deleteMovie(String title) {
        try (Connection con = dbCon.getConnection()) {
            deleteAssociations(title);

            PreparedStatement pSql2 = con.prepareStatement("DELETE FROM Movie Where Title = ?");
            pSql2.setString(1, title);
            pSql2.execute();
        } catch (SQLException throwables) {
            ErrorHandler.deleteMovieErr();
        }
    }

    public void addAssociations(String movieTitle, List<String> genreList){
        try (Connection con = dbCon.getConnection()) {

            if (genreList.size() == 1) {
                String genre = "";
                genre = genreList.get(0);
                PreparedStatement pSql = con.prepareStatement("INSERT INTO GenreMovie VALUES(?,?)");
                pSql.setString(1, movieTitle);
                pSql.setString(2, genre);
                pSql.execute();
            } else {
                PreparedStatement pSql = con.prepareStatement("INSERT INTO GenreMovie VALUES(?,?)");
                for (int i = 0; i < genreList.size(); i++) {

                    String genre = genreList.get(i);
                    pSql.setString(1, movieTitle);
                    pSql.setString(2, genre);
                    ;
                    pSql.addBatch();
                }
                pSql.executeBatch();

            }
        }catch (SQLException throwables) {
            ErrorHandler.addAssociationsErr();
        }
    }


    public void deleteAssociations(String title) {
        try (Connection con = dbCon.getConnection()) {

            PreparedStatement pSql = con.prepareStatement("DELETE FROM GenreMovie WHERE MovieTitle = ?");
            pSql.setString(1, title);
            pSql.execute();
        } catch (SQLException throwables) {
            ErrorHandler.deleteAssociationsErr();
        }
    }

    public void updateMovie(String movieTitle, List<String> newGenres, int newPersonalRating){

        try (Connection con = dbCon.getConnection()) {
            // Update personalRating
            PreparedStatement pSql = con.prepareStatement("UPDATE Movie SET PersonalRating= '" + newPersonalRating + "' WHERE Title= '" + movieTitle + "'");
            pSql.execute();

            // Update associations
            deleteAssociations(movieTitle);
            addAssociations(movieTitle, newGenres);
        } catch (SQLException throwables){
            ErrorHandler.updateMovieErr();
        }
    }


    public void updateLastViewed(String movieTitle, LocalDate now) {
        try (Connection con = dbCon.getConnection()) {
            // Update LastViewed
            PreparedStatement pSql = con.prepareStatement("UPDATE Movie SET LastViewed= '" + now + "' WHERE Title= '" + movieTitle + "'");
            pSql.execute();
        } catch (SQLException throwables) {
            ErrorHandler.updateLastViewedErr();
        }
    }

}
