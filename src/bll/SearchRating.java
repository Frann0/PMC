package bll;

import be.Movie;

public class SearchRating implements ISearch {

    /**
     * Method to compare imdbRating and personalRating of the passed movie to
     * the rating specified in the search.
     * @param movie is a movie object.
     * @param search contains the search tokens,
     * @return a boolean to indicate if the movie's rating is equal to
     * or higher than the rating specified by search.
     */
    @Override
    public boolean compareMovie(Movie movie, Search search) {
        return movie.getRating() >= search.getRating() || movie.getPersonalRating() >= search.getRating();
    }
}
