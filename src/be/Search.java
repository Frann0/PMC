package be;

import java.util.ArrayList;
import java.util.List;

public class Search {

    private int rating = -1;
    List<String> allGenres;
    List<String> titleTokens;
    List<String> genreTokens;

    /**
     * The search method splits the query string into separate strings.
     * If one of the strings contains numbers, rating will be updated (parsing int).
     * If the strings match the genres in the database, genreTokens will be added.
     * The rest of the strings will be saved as filterTokens.
     * @param query is the search string entered in the GUI.
     * @param genres is a list of all genres from the database.
     */
    public Search(String query, List<String> genres) {
        genreTokens = new ArrayList<>();
        titleTokens = new ArrayList<>();
        allGenres = genres;
        String[] tmpArr = query.split(" ");

        for (int i = 0; i < tmpArr.length; i++) {
            try {
                if(Integer.parseInt(tmpArr[i]) > rating){
                    rating = Integer.parseInt(tmpArr[i]);
                }

            } catch (Exception ex) {
                    if (isItAGenre(tmpArr[i])) {
                        genreTokens.add(tmpArr[i]);
                    } else {
                        titleTokens.add(tmpArr[i]);
                    }
            }
        }
    }

    /**
     * Checks to see if the string matches a genre.
     * @param query is a string token derived from the original search string.
     * @return boolean to determine if the string is a genre.
     */
    private boolean isItAGenre(String query){
        for (int i = 0; i < allGenres.size(); i++){
            if (allGenres.get(i).equalsIgnoreCase(query)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for rating.
     * @return rating.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Getter for title tokens.
     * @return a List<String> of title tokens.
     */
    public List<String> getTitleTokens() {
        return titleTokens;
    }

    /**
     * Getter for genre tokens.
     * @return a List<String> of genre tokens.
     */
    public List<String> getGenreTokens() {
        return genreTokens;
    }

    /*
    public List<String> getAllGenres() {
        return allGenres;
    } */
}
