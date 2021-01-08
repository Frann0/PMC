package bll;



import dal.genreDAL;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class genreManager {
    private genreDAL myGenreDAL;


    public genreManager() throws IOException {
        myGenreDAL = new genreDAL();
    }

    public void addGenre(String genre) throws SQLException {
        myGenreDAL.addGenre(genre);
    }

    public List<String> getAllGenres() throws SQLException {
        return myGenreDAL.getAllGenres();
    }

    public void deleteGenre(String genre) throws SQLException {
        myGenreDAL.deleteGenre(genre);
    }




}
