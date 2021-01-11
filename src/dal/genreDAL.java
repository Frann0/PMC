package dal;

import be.dbConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class genreDAL {
    private dbConnector dbCon;

    public genreDAL() throws IOException {
        dbCon = new dbConnector();
    }


    public void addGenre(String genre) {
        try (Connection con = dbCon.getConnection()) {
            PreparedStatement pSql = con.prepareStatement("INSERT INTO Genre VALUES(?)");
            pSql.setString(1, genre);
            pSql.execute();
        } catch (SQLException throwables) {
            ErrorHandler.addGenreErr();
        }
    }

    public void deleteAssociationByGenre(String genre){
        try (Connection con = dbCon.getConnection()) {

            PreparedStatement pSql = con.prepareStatement("DELETE FROM GenreMovie WHERE GenreName = ?");
            pSql.setString(1, genre);
            pSql.execute();
        } catch (SQLException throwables) {
            ErrorHandler.deleteAssociationByGenreErr();
        }
    }

    public void deleteGenre(String genre){
        deleteAssociationByGenre(genre);
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement pSql = con.prepareStatement("DELETE FROM Genre WHERE genrename = ?; ");
            pSql.setString(1,genre);
            pSql.execute();
        } catch (SQLException throwables) {
            ErrorHandler.deleteGenreErr();
        }
    }

    public List<String> getAllGenres() {
        List<String> allGenres = new ArrayList<>();
        try (Connection con = dbCon.getConnection()) {
            PreparedStatement pSql = con.prepareStatement("SELECT * FROM Genre");
            pSql.execute();
            ResultSet resultSet = pSql.getResultSet();
            while(resultSet.next()){
                allGenres.add(resultSet.getString("genrename"));
            }
        } catch (SQLException throwables) {
            ErrorHandler.getAllGenresErr();
        }
        return allGenres;
    }
}