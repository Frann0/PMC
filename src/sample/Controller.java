package sample;

import be.Movie;
import bll.Searcher;
import com.jfoenix.controls.JFXTextField;
import gui.model.genreModel;
import gui.model.movieModel;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private Pane paneEditMovie;
    @FXML
    private Pane TitleBar;
    @FXML
    private Pane titlePane;
    @FXML
    private HBox root;
    @FXML
    private HBox titleHbox;
    @FXML
    private JFXTextField Search;
    @FXML
    private Label movieTitleField;
    @FXML
    private JFXTextField personalRatingField;
    @FXML
    private Label lblIMDBRating;
    @FXML
    private Label lblIMDBRating1;


    private movieModel myMovieModel;
    private genreModel myGenreModel;


    //private final ObservableList<String> genres = FXCollections.observableArrayList();
    //private final ObservableList<Movie> movies = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            myMovieModel = new movieModel();
            myGenreModel = new genreModel();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        TitleBar.setPrefWidth(465);
        titlePane.setPrefWidth(150);


        tblClmTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblClmRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tblClmLastViewed.setCellValueFactory(new PropertyValueFactory<>("lastViewed"));

        paneMovies.setVisible(false);
        paneMovieTitle.setVisible(false);
        paneEditMovie.setVisible(false);

        titleHbox.setPrefWidth(800);


        try {
            lstGenre.setItems(myGenreModel.getAllGenres());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /*
        try {
            movies.addAll(myMovieModel.getAllMovies());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //tblMoviesInGenre.setItems(movies);
        */

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
        tblMoviesInGenre.setItems(myMovieModel.moviesByGenre(lstGenre.getSelectionModel().getSelectedItem()));

    }

    public void handleMovieSelected(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), paneMovieTitle);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(100);
        if (!paneMovieTitle.isVisible()){
            fadeTransition.play();
        }

        if (tblMoviesInGenre.getSelectionModel().getSelectedItem() != null) {
            titlePane.setPrefWidth(490);
            titleHbox.setPrefWidth(800);
            TitleBar.setPrefWidth(800);
            Movie currentMovie = tblMoviesInGenre.getSelectionModel().getSelectedItem();
            paneMovieTitle.setVisible(true);
            lblBigMovieTitle.setText(currentMovie.getTitle());
            imgPoster.setImage(currentMovie.getArtwork());
            lblMovieTitle.setText(currentMovie.getTitle());
            lblIMDBRating.setText(currentMovie.getRating());
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

        //tblMoviesInGenre.setItems(searcher.search(movies, Search.getText()));

    }

    public void handleAddMovie() throws SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a movie you want to add to your playlist");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Movie files (*.mpeg4 , *.mp4)", "*.mpeg4", "*.mp4");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());

        // Reformatting filePath to get movie info.
        String filePath = selectedFile.getPath();
        String[] movieInfoTemp = filePath.split("\\\\");
        String[] movieInfo = movieInfoTemp[movieInfoTemp.length-1].split("-");
        String imdbRating = movieInfo[0].trim();
        String[] movieTitleTemp = movieInfo[1].split("\\.");
        String movieTitle = movieTitleTemp[0].trim();

        myMovieModel.addMovie(movieTitle, imdbRating, filePath);

        // TODO Make if statement to check if title is already in allMovies
        // TODO Update fields in editWindow

        movieTitleField.setText(movieTitle);
        lblIMDBRating1.setText(imdbRating);

        paneEditMovie.setVisible(true);
        TitleBar.setLayoutX(0);
        TitleBar.setPrefWidth(1135);
        titlePane.setPrefWidth(845);
        titleHbox.setPrefWidth(1135);

    }

    public void HandleAddMoviePoster(MouseEvent mouseEvent) {
        System.out.println("Test");
    }

    public void handleRemoveMovie(ActionEvent actionEvent) {
        Movie toDelete = tblMoviesInGenre.getSelectionModel().getSelectedItem();


    }

    public void handleEditMovie(ActionEvent actionEvent) {
        paneEditMovie.setVisible(true);
        TitleBar.setLayoutX(0);
        TitleBar.setPrefWidth(1135);
        titlePane.setPrefWidth(845);
        titleHbox.setPrefWidth(1135);
    }

    public void handleAddGenre(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("Genre");
        dialog.setTitle("Add genre");
        dialog.setHeaderText("Add Genre");
        dialog.setContentText("Please enter the genre you wish to add: ");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(genre -> {
            try {
                myGenreModel.addGenre(genre);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public void handleRemoveGenre(ActionEvent actionEvent) {
    }

    public void handleSaveMovie(){
        Movie oldMovie = tblMoviesInGenre.getSelectionModel().getSelectedItem();
        
        //Update info
        List<String> newGenres;
        String newPersonalRating = ""; // if == old do nothing.

        myMovieModel.updateMovie(oldMovie.getTitle(), newGenres, newPersonalRating);

        paneEditMovie.setVisible(false);
        TitleBar.setLayoutX(335);
        TitleBar.setPrefWidth(800);
        titlePane.setPrefWidth(483);
        titleHbox.setPrefWidth(800);
    }

    public void handleCancelMovie(){
        paneEditMovie.setVisible(false);
        TitleBar.setLayoutX(335);
        TitleBar.setPrefWidth(800);
        titlePane.setPrefWidth(483);
        titleHbox.setPrefWidth(800);
    }


}
