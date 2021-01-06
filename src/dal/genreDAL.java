package dal;

import be.Playlist;
import be.dbConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public void deleteGenre(){}

    // TODO
    public List<String> getAllGenres(){
        return null;
    }

    // TODO
    public List<Playlist> getPlaylist(){
        return null;
    }

    // TODO
    // TODO
}
