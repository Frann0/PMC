package bll;


import be.Playlist;
import dal.genreDAL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class genreManager {
    private genreDAL myGenreDAL;


    public genreManager() throws IOException {
        myGenreDAL = new genreDAL();
    }

    public List<String> getAllGenres() throws SQLException {
        return myGenreDAL.getAllGenres();
    }

    // TODO getAllPlaylists()
    // TODO addGenre()
    // TODO deleteGenre()
    // TODO addAssociation()
    // TODO deleteAssociation()
    // TODO updateAllGenres()
    // TODO updateAllPlaylists()

}
