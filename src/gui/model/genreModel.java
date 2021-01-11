package gui.model;

import bll.genreManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;


public class genreModel {
    private genreManager myGenreManager;
    private ObservableList<String> allGenres;


    public genreModel() throws IOException, SQLException {
        myGenreManager = new genreManager();
        allGenres = FXCollections.observableArrayList();
        allGenres.addAll(myGenreManager.getAllGenres());
    }

    /**
     * Adds a genre to the database.
     * @param genre The genre to be added.
     * @throws SQLException
     */
    public void addGenre(String genre) throws SQLException {
        myGenreManager.addGenre(genre);
        allGenres.add(genre);
    }

    /**
     * Gets a string containing all genres. Used by the editMovie view
     * to indicate which genres can be added to a movie.
     * @return String with all genres.
     */
    public String getGenresString(){
        String genres ="";
        for (String str : allGenres){
            genres += str +", ";
        }
        return genres;
    }

    /**
     * Gets all genres that are specified in the database.
     * @return List of all genres.
     * @throws SQLException
     */
    public ObservableList<String> getAllGenres(){
        return allGenres;
    }

    /*
    public void updateAllGenres() throws SQLException {
        allGenres.clear();
        allGenres.addAll(myGenreManager.getAllGenres());
    } */

    /**
     * Delete a genre from the database.
     * @param genre The genre to be deleted.
     */
    public void deleteGenre(String genre){
        allGenres.remove(genre);
        myGenreManager.deleteGenre(genre);

    }
}
