package dal;

import be.Playlist;
import be.dbConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.beans.Statement;
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



    // TODO
    public void addGenre(String genre) throws SQLException {
        try (Connection con = dbCon.getConnection()) {

            PreparedStatement pSql = con.prepareStatement("INSERT INTO Genre VALUES(?)");
            pSql.setString(1, genre);
            pSql.execute();
        }
    }

    // TODO
    public void deleteGenre(String genre) throws SQLException {
        try (Connection con = dbCon.getConnection()) {

            PreparedStatement pSql = con.prepareStatement("DELETE FROM Genre WHERE genrename = ?");
            pSql.setString(1,genre);
            pSql.execute();
        }
    }

    // TODO
    public List<String> getAllGenres() throws SQLException {
        try (Connection con = dbCon.getConnection()) {

            ArrayList<String> allgenre = new ArrayList<>();
            String sql = "SELECT * FROM Genre;";

            PreparedStatement pSql = con.prepareStatement(sql);
            try(ResultSet rs = pSql.executeQuery("SELECT genrename FROM Genre;")) {
                while (rs.next()) {
                    allgenre.add(rs.getString("genrename"));
                }
                return allgenre;
            }


        }

    }

    // TODO
    public List<Playlist> getPlaylist(){
        return null;
    }

    // TODO
    // TODO
}
