
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters) {
        int count = 0;
        int tot = 0;
        for (Rater rater : myRaters) {
            for (String item : rater.getItemsRated()) {
                if (item.equals(id)) {
                    count += 1;
                    tot += rater.getRating(item);
                }
            }
        }

        if (count < minimalRaters) {
            return 0.0;
        } else {
            return (double) tot / count;
        }
    }

    // private double getAverageByID(String id,int minimalRaters){
    // HashMap<String,ArrayList<Double>> map = new HashMap<>();
    // for(Rater rater:myRaters){
    // for(String item:rater.getItemsRated()){
    // if(!map.keySet().contains(item)){
    // map.put(item,new ArrayList<>());
    // ArrayList<Double> temp = map.get(item);
    // temp.add(rater.getRating(item));
    // }
    // else{
    // ArrayList<Double> temp = map.get(item);
    // temp.add(rater.getRating(item));
    // }
    // }
    // }
    // }
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        double result = 0.0;
        ArrayList<Rating> avg_ratings = new ArrayList<>();
        for (Movie movie : myMovies) {
            result = getAverageByID(movie.getID(), minimalRaters);
            if (result != 0.0) {
                avg_ratings.add(new Rating(movie.getID(), result));
            }
        }
        return avg_ratings;
    }

    public String getTitle(String id) {
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "ID not found";
    }

    public String getID(String title) {
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}