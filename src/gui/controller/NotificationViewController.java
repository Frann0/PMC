package gui.controller;

import be.Movie;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NotificationViewController implements Initializable {

    @FXML
    private JFXListView<String> oldMovies;
    @FXML
    private JFXListView<String> badMovies;
    @FXML
    private AnchorPane anchorPane;

    List<Movie> badMoviesList = new ArrayList<>();
    List<Movie> oldMoviesList = new ArrayList<>();
    ObservableList<String> badMovieTitles = FXCollections.observableArrayList();
    ObservableList<String> oldMovieTitles = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public void setAllMovies(List<Movie> allMovies) {
        for (Movie movie : allMovies){
            System.out.println(movie.getLastViewed());
        }
        badMoviesList.addAll(allMovies);
        oldMoviesList.addAll(allMovies);

    }

    public void setFields() {
        if (badMoviesList != null && oldMoviesList != null) {
            badMoviesList.removeIf(movie -> movie.getPersonalRating() >= 6);
            oldMoviesList.removeIf(movie -> movie.getLastViewed().plusYears(2).compareTo(LocalDate.now()) > 0);

            for (Movie movie : badMoviesList) {
                badMovieTitles.add(movie.getTitle());
            }

            for (Movie movie : oldMoviesList) {
                oldMovieTitles.add(movie.getTitle());
            }

            badMovies.setItems(badMovieTitles);
            oldMovies.setItems(oldMovieTitles);
        }
    }
    

    public void handleContinue(ActionEvent actionEvent) {
        Stage window = (Stage) anchorPane.getScene().getWindow();
        window.close();
    }
}
