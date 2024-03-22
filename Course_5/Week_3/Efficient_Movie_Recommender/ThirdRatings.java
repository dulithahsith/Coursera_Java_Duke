
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.lang.reflect.Array;
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
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

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        double result = 0.0;
        ArrayList<Rating> avg_ratings = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movie : movies) {
            result = getAverageByID(movie, minimalRaters);
            if (result != 0.0) {
                avg_ratings.add(new Rating(movie, result));
            }
        }
        return avg_ratings;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        double result = 0.0;
        ArrayList<Rating> avg_ratings = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String movie : movies) {
            result = getAverageByID(movie, minimalRaters);
            if (result != 0.0) {
                avg_ratings.add(new Rating(movie, result));
            }
        }
        return avg_ratings;
    }

}