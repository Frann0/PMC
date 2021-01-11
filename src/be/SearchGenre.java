package be;

import bll.ISearch;

import java.util.List;

public class SearchGenre implements ISearch {

    /**
     * Method to compare genre of the passed movie to
     * the genre(s) specified in the search.
     * @param movie is a movie object.
     * @param search contains the search tokens,
     * @return a boolean to indicate if the movie's genre(s) matches
     * the genres specified by search.
     */
    @Override
    public boolean compareMovie(Movie movie, Search search) {
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
