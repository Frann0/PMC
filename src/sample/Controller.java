package sample;

import be.Movie;
import bll.Searcher;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    private Label lblMoviesInGenre;
    @FXML
    private Label lblBigMovieTitle;
    @FXML
    private ImageView imgPoster;
    @FXML
    private Label lblMovieTitle;
    @FXML
    private Label lblMovieRating;
    @FXML
    private Label lblMovieLastView;
    @FXML
    private ListView<String> lstGenre;
    @FXML
    private TableView<Movie> tblMoviesInGenre;
    @FXML
    private TableColumn<Movie, String> tblClmTitle;
    @FXML
    private TableColumn<Movie, Integer> tblClmRating;
    @FXML
    private TableColumn<Movie, LocalDate> tblClmLastViewed;
    @FXML
    private Pane paneMovies;
    @FXML
    private Pane paneMovieTitle;
    @FXML
    private Pane TitleBar;
    @FXML
    private Pane titlePane;
    @FXML
    private HBox root;
    @FXML
    private JFXTextField Search;

    private final ObservableList<String> genres = FXCollections.observableArrayList();
    private final ObservableList<Movie> movies = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TitleBar.setPrefWidth(465);
        titlePane.setPrefWidth(143);
        genres.add("Test");
        genres.add("Test2");
        genres.add("Test3");
        genres.add("Test4");

        ObservableList<String> Test = FXCollections.observableArrayList();
        Test.add(genres.get(0));
        Test.add(genres.get(1));

        movies.add(new Movie("The Shawshank Redemption", 5, Test));

        tblClmTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblClmRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tblClmLastViewed.setCellValueFactory(new PropertyValueFactory<>("lastViewed"));

        paneMovies.setVisible(false);
        paneMovieTitle.setVisible(false);

        lstGenre.setItems(genres);
        tblMoviesInGenre.setItems(movies);
    }

    public void handleGenreSelected(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), paneMovies);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(100);
        if (!paneMovies.isVisible()){
            fadeTransition.play();
        }

        if (lstGenre.getSelectionModel().getSelectedItem() != null) {
            paneMovies.setVisible(true);
            lblMoviesInGenre.setText(lstGenre.getSelectionModel().getSelectedItem());
        }
    }

    public void handleMovieSelected(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), paneMovieTitle);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(100);
        if (!paneMovieTitle.isVisible()){
            fadeTransition.play();
        }

        if (tblMoviesInGenre.getSelectionModel().getSelectedItem() != null) {
            titlePane.setPrefWidth(483);
            TitleBar.setPrefWidth(800);
            Movie currentMovie = tblMoviesInGenre.getSelectionModel().getSelectedItem();
            paneMovieTitle.setVisible(true);
            lblBigMovieTitle.setText(currentMovie.getTitle());
            imgPoster.setImage(currentMovie.getArtwork());
            lblMovieTitle.setText(currentMovie.getTitle());
            lblMovieRating.setText(currentMovie.getRating());
            lblMovieLastView.setText(currentMovie.getLastViewed().toString());
        }
    }

    public void handleExit(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void handleMinimize(MouseEvent mouseEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);
    }

    public void handleSearch(KeyEvent keyEvent) {
        Searcher searcher = new Searcher();

        tblMoviesInGenre.setItems(searcher.search(movies, Search.getText()));

    }
}
