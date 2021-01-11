package dal;

import be.dbConnector;

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




    public void addGenre(String genre) throws SQLException {
        try (Connection con = dbCon.getConnection()) {
            PreparedStatement pSql = con.prepareStatement("INSERT INTO Genre VALUES(?)");
            pSql.setString(1, genre);
            pSql.execute();
        }
    }

    public void deleteAssociationByGenre(String genre) throws SQLException {
        try (Connection con = dbCon.getConnection()) {

            PreparedStatement pSql = con.prepareStatement("DELETE FROM GenreMovie WHERE GenreName = ?");
            pSql.setString(1, genre);
            pSql.execute();

        }
    }

    public void deleteGenre(String genre) throws SQLException{
        deleteAssociationByGenre(genre);
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement pSql = con.prepareStatement("DELETE FROM Genre WHERE genrename = ?; ");
            pSql.setString(1,genre);
            pSql.execute();
        }
    }

    public List<String> getAllGenres() throws SQLException {
        List<String> allGenres = new ArrayList<>();
        try (Connection con = dbCon.getConnection()) {
            PreparedStatement pSql = con.prepareStatement("SELECT * FROM Genre");
            pSql.execute();
            ResultSet resultSet = pSql.getResultSet();
            while(resultSet.next()){
                allGenres.add(resultSet.getString("genrename"));
            }
        }
        return allGenres;
    }
}