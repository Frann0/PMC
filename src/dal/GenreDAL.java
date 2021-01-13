package dal;

import be.DbConnector;
import be.ErrorHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAL {
    private DbConnector dbCon;

    public GenreDAL() throws IOException {
        dbCon = new DbConnector();
    }

    /**
     * This method create a new Genre and adds it to the Database as long the Genre doesn't exists
     * @param genre this is the name of the Genre you wanna create.
     */
    public void addGenre(String genre) {
        try (Connection con = dbCon.getConnection()) {
            PreparedStatement pSql = con.prepareStatement("INSERT INTO Genre VALUES(?)");
            pSql.setString(1, genre);
            pSql.execute();
        } catch (SQLException throwables) {
            ErrorHandler.addGenreErr();
        }
    }

    /**
     * This Method Delete from GenreMovie by Genre. so all movies that have the Genre will be removed
     * from GenreMovie.
     * @param genre this is the Genre you want to delete from GenreMovie
     */
    public void deleteAssociationByGenre(String genre){
        try (Connection con = dbCon.getConnection()) {

            PreparedStatement pSql = con.prepareStatement("DELETE FROM GenreMovie WHERE GenreName = ?");
            pSql.setString(1, genre);
            pSql.execute();
        } catch (SQLException throwables) {
            ErrorHandler.deleteAssociationByGenreErr();
        }
    }

    /**
     * This method delete a Genre from the Datebase as long as Genre you try to delete exists.
     * @param genre the name of the genre you try to delete.
     */
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

    /**
     * This method create a ArrayList and take Genre From the Database and put into this list.
     * @return it returns the List you made. AKA Shows all Genre in Database.
     */
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