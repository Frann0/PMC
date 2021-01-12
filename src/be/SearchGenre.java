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
        List<String> movieGenres = movie.getGenres();
        List<String> genreTokens = search.getGenreTokens();

        boolean[] tokenMatch = new boolean[genreTokens.size()];
        System.out.println(tokenMatch.length);
        for (int i = 0; i < search.getGenreTokens().size(); i++){
            for (String movieGenre : movieGenres) {
                if(genreTokens.get(i).equalsIgnoreCase(movieGenre)){
                    tokenMatch[i] = true;
                }
            }
        }
        boolean match = true;

        // If any filterToken does not match, false is returned.
        for(boolean bool : tokenMatch){
            if(!bool){
                match = bool;
            }
        }
        return match;
    }
}
