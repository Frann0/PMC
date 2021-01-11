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

    /**
     * Add a genre to the database.
     * @param genre the genre to be added.
     * @throws SQLException
     */
    public void addGenre(String genre) throws SQLException {
        myGenreDAL.addGenre(genre);
    }

    /**
     * Getter for all genres in the database.
     * @return a List<String> of all genres.
     * @throws SQLException
     */
    public List<String> getAllGenres() throws SQLException {
        return myGenreDAL.getAllGenres();
    }

    /**
     * Delete a genre from the database.
     * @param genre the genre to be deleted.
     * @throws SQLException
     */
    public void deleteGenre(String genre) throws SQLException {
        myGenreDAL.deleteGenre(genre);
    }
}
