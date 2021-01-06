package dal;

import be.Movie;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class dbTest {








    public static void main(String[] args) throws SQLException, IOException {

        //String movieTitle = "Yiiiiir 2";
        //String filePath = "";
        //String imdbRating = "9.9";
        //String personalRating = "2";
        //LocalDate lastViewed = null;


        genreDAL myGenreDAL = new genreDAL();
        movieDAL myMovieDAL = new movieDAL();

        //myGenreDAL.addGenre("Disco");
        //myMovieDAL.addMovie("Smurfs", "9.9", "C:/yeah/docs/Smurfs.mp4");
        //myMovieDAL.addMovie(new Movie(movieTitle, imdbRating, filePath, personalRating, lastViewed));

        List<Movie> allMovs = myMovieDAL.getAllMovies();
        for(Movie mov : allMovs){
            System.out.println(mov.getTitle());
            for(int i = 0; i < mov.getGenres().size(); i++){
                System.out.println("Genre " + i + ": " + mov.getGenres().get(i));
            }
        }
    }
}


