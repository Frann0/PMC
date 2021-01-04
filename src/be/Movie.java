package be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;

public class Movie {
    private Image artwork = new Image("/Resources/ShawshankRedemptionMoviePoster.jpg");
    private final StringProperty title = new SimpleStringProperty("");
    private final IntegerProperty rating = new SimpleIntegerProperty();
    private final int maxRating = 5;
    private LocalDate lastViewed;

    public Movie(String title, int rating){
        this.title.set(title);
        this.rating.set(rating);
    }

    public Image getArtwork() {
        return artwork;
    }

    public String getTitle() {
        return title.get();
    }

    public String getRating() {
        return rating.get() + " / " + maxRating;
    }

    public LocalDate getLastViewed() {
        return LocalDate.now();
    }
}
