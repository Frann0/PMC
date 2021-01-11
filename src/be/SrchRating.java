package be;

import bll.ISrch;

public class SrchRating implements ISrch {

    @Override
    public boolean compareMovie(Movie movie, Srch search) {
        return movie.getRating() >= search.getRating() || movie.getPersonalRating() >= search.getRating();
    }
}
