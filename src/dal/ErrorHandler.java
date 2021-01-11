package dal;

import be.dbConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ErrorHandler {

    private static dbConnector dbCon;


public ErrorHandler() throws IOException {
    dbCon = new dbConnector();
}


    public static boolean testConnection() {
        try (Connection con = dbCon.getConnection()) {
            return true;
        } catch (SQLException throwables) {
            return false;
        }

    }


    public static void connectionErr() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Couldn't connect to Server");
        alert.setContentText("Check your Connections and make you have access to the Server");

        alert.showAndWait();
    }

    public static void addGenreErr() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Couldn't Add new Genre");
        alert.setContentText("Make sure that the Genre doesnt all ready exists.");

        alert.showAndWait();
    }

    public static void deleteAssociationByGenreErr() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Couldn't delete the Genre from Input");
        alert.setContentText("Make sure that the Genre does exists");

        alert.showAndWait();
    }

    public static void deleteGenreErr() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Couldn't delete the Genre from Input");
        alert.setContentText("Make sure that the Genre does exists");

        alert.showAndWait();
    }

    public static void getAllGenresErr() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Couldn't find the list with genre");
        alert.setContentText("Something went Wrong contact Jonas");

        alert.showAndWait();
    }
    public static void addMovieErr() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Couldn't add movie");
        alert.setContentText("Check that the movie from input exists.");

        alert.showAndWait();
    }
    public static void deleteMovieErr() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Couldn't delete movie");
        alert.setContentText("Check that the movie from input exists.");

        alert.showAndWait();
    }
    public static void addAssociationsErr() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Couldn't add Association");
        alert.setContentText("Check that the movie/genre from input exists.");

        alert.showAndWait();
    }
    public static void deleteAssociationsErr() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Couldn't delete Association");
        alert.setContentText("Check that the movie/genre from input exists.");

        alert.showAndWait();
    }
    public static void updateMovieErr() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Couldn't update infomation");
        alert.setContentText("Check that your input");

        alert.showAndWait();
    }
}
