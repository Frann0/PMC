package be;

import bll.ISrch;

import java.util.List;

public class SrchGenre implements ISrch {

    @Override
    public boolean compareMovie(Movie movie, Srch search) {
        List<String> genres = movie.getGenres();
        List<String> genreTokens = search.getGenreTokens();

        for (String genreToken : genreTokens) {
            for (String allGenre : genres) {
                if (genreToken.equalsIgnoreCase(allGenre)) {
                    return true;
                }
            }
        }
        return false;
    }
}
