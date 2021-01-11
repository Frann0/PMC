package be;

import bll.ISrch;

public class SrchTitle implements ISrch {

    @Override
    public boolean compareMovie(Movie movie, Srch search) {
        boolean[] tokenMatch = new boolean[search.getFilterTokens().size()];

        for (int i = 0; i < search.getFilterTokens().size(); i++){
            tokenMatch[i] = movie.getTitle().toLowerCase().contains(search.getFilterTokens().get(i));
        }
        boolean match = false;
        for(boolean bool : tokenMatch){
            if (bool){
                match = bool;
            }
        }
        // If any filterToken does not match, false is returned.
        for(boolean bool : tokenMatch){
            if(!bool){
                match = bool;
            }
        }

        return match;
    }
}
