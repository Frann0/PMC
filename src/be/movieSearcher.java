package be;

import bll.ISrch;

public class movieSearcher {
    private ISrch iSrch;

    public movieSearcher(ISrch iSrch) {
        this.iSrch = iSrch;
    }

    public boolean executeSearch(Movie movie, Srch search){
        return iSrch.compareMovie(movie, search);
    }
}
