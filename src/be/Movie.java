package be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private Image artwork = new Image("/Resources/ShawshankRedemptionMoviePoster.jpg");
    private final StringProperty title = new SimpleStringProperty("");
    private final IntegerProperty imdbRating = new SimpleIntegerProperty(0);
    private final IntegerProperty personalRating = new SimpleIntegerProperty(0);
    private List<String> genres = new ArrayList<>();
    private LocalDate lastViewed;
    private final StringProperty filePath = new SimpleStringProperty("");

    public Movie(String title, int imdbRating, String filePath){
        this.title.set(title);
        this.imdbRating.set(imdbRating);
        this.lastViewed = null;
        this.filePath.set(filePath);
    }

    public Movie(String title, int imdbRating, String filePath, int personalRating, LocalDate lastViewed){
        this.title.set(title);
        this.imdbRating.set(imdbRating);
        this.lastViewed = lastViewed;
        this.filePath.set(filePath);
        this.personalRating.set(personalRating);
    }

    /**
     * Getter for the movie poster.
     * @return filepath to the poster.
     */
    public Image getArtwork() {
        return artwork;
    }

    /**
     * Getter for the movie's filepath.
     * @return filepath of the movie.
     */
    public String getFilePath() {
        return filePath.get();
    }

    /**
     * Getter for the movie's personal rating.
     * @return personal rating of the movie.
     */
    public int getPersonalRating() {
        return personalRating.get();
    }

    /**
     * Getter for the movie title.
     * @return title of the movie.
     */
    public String getTitle() {
        return title.get();
    }

    /**
     * Getter for the movie's imdbRating.
     * @return imdb rating of the movie.
     */
    public int getRating() {
        return imdbRating.get();
    }

    /**
     * Getter for the last viewed date of the movie.
     * @return LocalDate with date for last view.
     */
    public LocalDate getLastViewed() {
        return lastViewed;
    }

    /**
     * Getter for the movie's genre.
     * @return a List<String> with the movie's genres.
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Setter for the movie's genres.
     * @param genres a list of genres.
     */
    public void setGenres(List<String> genres){this.genres = genres;}

    /**
     * Setter for the movie's personal rating.
     * @param personalRating the movie's personal rating.
     */
    public void setPersonalRating(int personalRating){this.personalRating.set(personalRating);}

    public void setLastViewed(LocalDate lastViewed) {
        this.lastViewed = lastViewed;
    }
}
