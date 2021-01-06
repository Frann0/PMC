package dal;

import be.Movie;
import be.dbConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class movieDAL {
    private dbConnector dbCon = new dbConnector();

    public movieDAL() throws IOException {
    }


    // TODO
    public List<Movie> getAllMovies(){
        return null;
    }

    // TODO
    public void addMovie(String title,String rating,String imdb,String lastview,String filepath) throws SQLException {
        try (Connection con = dbCon.getConnection()) {

            PreparedStatement pSql = con.prepareStatement("INSERT Into Movie VALUES(?)");
            pSql.setString(1,title);
            pSql.setString(2,rating);
            pSql.setString(3,imdb);
            pSql.setString(4,lastview);
            pSql.setString(5,filepath);
            pSql.execute();
        }
    }


    // TODO
    public void deleteMovie(){}

    // TODO
    public void updateMovie(){}


}
