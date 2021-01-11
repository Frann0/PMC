package be;

import bll.ISrch;

public class SrchTitle implements ISrch {

    @Override
    public boolean compareMovie(Movie movie, Srch search) {

        for (int i = 0; i < search.getFilterTokens().size(); i++){
            if(movie.getTitle().toLowerCase().contains(search.getFilterTokens().get(i))){
                return true;
            }
        }
        return false;
    }
}
