package be;

import bll.ISearch;

public class movieSearcher {
    private ISearch iSearch;

    public movieSearcher(ISearch iSearch) {
        this.iSearch = iSearch;
    }

    /**
     * Method to execute the rating, genre and title search methods.
     * @param movie is the movie object so be searched
     * @param search is the search tokens
     * @return boolean to indicate if there is a match.
     */
    public boolean executeSearch(Movie movie, Search search){
        return iSearch.compareMovie(movie, search);
    }
}
