package be;

import bll.ISearch;

public class SearchTitle implements ISearch {

    /**
     * Method to compare title of the passed movie to
     * the title specified in the search.
     * @param movie is a movie object.
     * @param search contains the search tokens,
     * @return a boolean to indicate if the movie's title matches the search.
     */
    @Override
    public boolean compareMovie(Movie movie, Search search) {
        boolean[] tokenMatch = new boolean[search.getTitleTokens().size()];

        for (int i = 0; i < search.getTitleTokens().size(); i++){
            tokenMatch[i] = movie.getTitle().toLowerCase().contains(search.getTitleTokens().get(i));
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
