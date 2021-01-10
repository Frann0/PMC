package be;

import bll.ISrch;

import java.util.List;

public class SrchGenre implements ISrch {

    @Override
    public boolean compareMovie(Movie movie, Srch search) {
        List<String> allGenres = movie.getGenres();
        List<String> genreTokens = search.getGenreTokens();

        for (String genreToken : genreTokens) {
            for (String allGenre : allGenres) {
                if (genreToken.equalsIgnoreCase(allGenre)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean genreMatch(Movie movie, String query){

        for (int i = 0; i<movie.getGenres().size(); i++){
            if(movie.getGenres().get(i).equalsIgnoreCase(query)){
                return true;
            }
        }
        return false;
    }

}
