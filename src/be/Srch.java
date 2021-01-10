package be;

import java.util.ArrayList;
import java.util.List;

public class Srch {
    private int rating = -1;
    List<String> filterStrings;
    List<String> allGenres;
    List<String> genreTokens;

    public Srch(String query, List<String> genres) {
        genreTokens = new ArrayList<>();
        filterStrings = new ArrayList<>();
        allGenres = genres;
        String[] tmpArr = query.split(" ");

        for (int i = 0; i < tmpArr.length; i++) {
            try {
                rating = Integer.parseInt(tmpArr[i]);
            } catch (Exception ex) {
                if (tmpArr[i] != null) {
                    if (isItAGenre(tmpArr[i])) {
                        genreTokens.add(tmpArr[i]);
                    } else {
                        filterStrings.add(tmpArr[i]);
                    }
                }
            }
        }
    }

    private boolean isItAGenre(String query){
        for (int i = 0; i < allGenres.size(); i++){
            if (allGenres.get(i).equalsIgnoreCase(query)) {
                return true;
            }
        }
        return false;
    }

    public int getRating() {
        return rating;
    }

    public List<String> getFilterStrings() {
        return filterStrings;
    }

    public List<String> getGenreTokens() {
        return genreTokens;
    }

    public List<String> getAllGenres() {
        return allGenres;
    }
}
