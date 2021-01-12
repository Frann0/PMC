package dal;

import java.io.IOException;
import java.sql.SQLException;

public class dbTest {




    public static String[] splitSearch(String query){
        String[] queryTokens = new String[50];
        if(!query.contains(" ")){
            queryTokens[0] = query;
        } else{
            queryTokens = query.split(" ");
        }
        return queryTokens;
    }



    public static void main(String[] args) throws SQLException, IOException {

        //String movieTitle = "Yiiiiir 2";
        //String filePath = "";
        //String imdbRating = "9.9";
        //String personalRating = "2";
        //LocalDate lastViewed = null;


        GenreDAL myGenreDAL = new GenreDAL();
        movieDAL myMovieDAL = new movieDAL();

        /*
        List<Movie> allMovs = myMovieDAL.getAllMovies();
        List<Movie> filterMovs = new ArrayList<>();
        String[] queryTokens = new String[50];
        String search = "comedy ar";
        if(search.contains(" ")){
            queryTokens = splitSearch(search);
        }


        for (Movie mov : allMovs){
            boolean match = true;
            if(search.contains(" ")) {
                for (int i = 0; i < queryTokens.length; i++) {
                    if (!mov.getMovieString().contains(queryTokens[i])) {
                        match = false;
                    }
                }
            } else {
                match = mov.getMovieString().contains(search);
            }
            if (match){
                filterMovs.add(mov);
            }
        }
        for (Movie mov : filterMovs){
            System.out.println(mov.getTitle());
        }
        */

        /*
        String srch = "drama";

        String[] tmpArr = srch.split(" ");
        List<String> filterStrings = new ArrayList<>();
        int rating = -1;

        for (int i = 0; i<tmpArr.length; i++){
            try{
                rating = Integer.parseInt(tmpArr[i]);
            }catch(Exception ex){
                if(tmpArr[i] != null){
                    filterStrings.add(tmpArr[i]);
                }
            }
        }

        for (int i = 0; i < filterStrings.size(); i++){
            System.out.println(filterStrings.get(i));
        }
        System.out.println(rating);

        */


    }


}


