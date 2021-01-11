package gui.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NotificationViewController implements Initializable {

    @FXML
    private JFXTextArea oldMovies;
    @FXML
    private JFXTextArea badMovies;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){




    }

    public void setOldMovies(String oldMovies) {
        this.oldMovies.setText(oldMovies);
    }

    public void setBadMovies(String badMovies) {
        this.badMovies.setText(badMovies);
    }

    public void handleContinue(ActionEvent actionEvent) {
        Stage window = (Stage) anchorPane.getScene().getWindow();
        window.close();
    }
}
