package be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {
    private Image artwork = new Image("/Resources/ShawshankRedemptionMoviePoster.jpg");
    private final StringProperty title = new SimpleStringProperty("");
    private final StringProperty imdbRating = new SimpleStringProperty("");
    private final StringProperty personalRating = new SimpleStringProperty("");
    private final String maxRating = "10";
    private List<String> genres = new ArrayList<>();
    private LocalDate lastViewed;
    private final StringProperty filePath = new SimpleStringProperty("");

    public Movie(String title, String imdbRating, String filePath){
        this.title.set(title);
        this.imdbRating.set(imdbRating);
        this.lastViewed = null;
        this.filePath.set(filePath);
    }

    public Movie(String title, String imdbRating, String filePath, String personalRating, LocalDate lastViewed){
        this.title.set(title);
        this.imdbRating.set(imdbRating);
        this.lastViewed = lastViewed;
        this.filePath.set(filePath);
        this.personalRating.set(personalRating);
    }

    public Image getArtwork() {
        return null;
    }

    public String getTitle() {
        return title.get();
    }

    public String getRating() {
        return imdbRating.get() + " / " + maxRating;
    }

    public String getPersonalRating() {
        return personalRating.get();
    }

    public LocalDate getLastViewed() {
        return LocalDate.now();
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres){this.genres = genres;}

    public String getFilePath() {
        return filePath.get();
    }
}

