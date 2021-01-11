package bll;

import be.Movie;
import be.Search;

public interface ISearch {
    public boolean compareMovie(Movie movie, Search search);
}
