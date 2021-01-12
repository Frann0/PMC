package bll;

import dal.GenreDAL;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GenreManager {
    private GenreDAL myGenreDAL;


    public GenreManager() throws IOException {
        myGenreDAL = new GenreDAL();
    }

    /**
     * Add a genre to the database.
     * @param genre the genre to be added.
     * @throws SQLException
     */
    public void addGenre(String genre){
        myGenreDAL.addGenre(genre);
    }

    /**
     * Getter for all genres in the database.
     * @return a List<String> of all genres.
     * @throws SQLException
     */
    public List<String> getAllGenres(){
        return myGenreDAL.getAllGenres();
    }

    /**
     * Delete a genre from the database.
     * @param genre the genre to be deleted.
     * @throws SQLException
     */
    public void deleteGenre(String genre){
        myGenreDAL.deleteGenre(genre);
    }
}
