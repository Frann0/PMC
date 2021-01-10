package be;

import bll.ISrch;
import dal.genreDAL;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SrchTitle implements ISrch {

    @Override
    public boolean compareMovie(Movie movie, Srch search) {

        for (int i = 0; i < search.getFilterStrings().size(); i++){
            if(movie.getTitle().toLowerCase().contains(search.getFilterStrings().get(i))){
                return true;
            }
        }
        return false;
    }
}
