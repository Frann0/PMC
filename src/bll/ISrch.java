package bll;

import be.Movie;
import be.Srch;

public interface ISrch {
    public boolean compareMovie(Movie movie, Srch search);
}
