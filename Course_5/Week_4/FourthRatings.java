
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {

    public FourthRatings() {
    }

    // private double getAverageByID(String id, int minimalRaters) {
    // int count = 0;
    // int tot = 0;
    // for (Rater rater : RaterDatabase.getRaters()) {
    // for (String item : rater.getItemsRated()) {
    // if (item.equals(id)) {
    // count += 1;
    // tot += rater.getRating(item);
    // }
    // }
    // }

    // if (count < minimalRaters) {
    // return 0.0;
    // } else {
    // return (double) tot / count;
    // }
    // }

    // public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter
    // filterCriteria) {
    // double result = 0.0;
    // ArrayList<Rating> avg_ratings = new ArrayList<>();
    // ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
    // for (String movie : movies) {
    // result = getAverageByID(movie, minimalRaters);
    // if (result != 0.0) {
    // avg_ratings.add(new Rating(movie, result));
    // }
    // }
    // return avg_ratings;
    // }

    // public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    // double result = 0.0;
    // ArrayList<Rating> avg_ratings = new ArrayList<>();
    // ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    // for (String movie : movies) {
    // result = getAverageByID(movie, minimalRaters);
    // if (result != 0.0) {
    // avg_ratings.add(new Rating(movie, result));
    // }
    // }
    // return avg_ratings;
    // }

    private double dotProduct(Rater me, Rater r) {
        int similarity = 0;
        for (String item : me.getItemsRated()) {
            if (r.getItemsRated().contains(item)) {
                similarity += (me.getRating(item) - 5) * (r.getRating(item) - 5);
            }
        }
        if (similarity > 0) {
            return similarity;
        } else {
            return -1.0;
        }

    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> rater_ratings = new ArrayList<>();
        for (Rater r : RaterDatabase.getRaters()) {
            if (r.getID().equals(id)) {
                continue;
            }
            double product = dotProduct(RaterDatabase.getRater(id), r);
            if (product <= 0) {
                continue;
            }
            rater_ratings.add(new Rating(r.getID(), product));
        }
        Collections.sort(rater_ratings, new RatingComparator());
        return rater_ratings;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> rater_ratings = getSimilarities(id);
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> movie_ratings = new ArrayList<>();
        for (String movieID : movieIDs) {
            int rater_count = 0;
            double weight = 0;
            for (int i = 0; i < numSimilarRaters; i++) {
                Rating r = rater_ratings.get(i);
                Rater rater = RaterDatabase.getRater(r.getItem());
                if (rater.hasRating(movieID)) {
                    rater_count += 1;
                    weight += r.getValue() * rater.getRating(movieID);
                }
            }
            if (rater_count >= minimalRaters) {
                movie_ratings.add(new Rating(movieID, weight / rater_count));
            }

        }
        Collections.sort(movie_ratings, new RatingComparator());
        return movie_ratings;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters,
            Filter filterCriteria) {
        ArrayList<Rating> rater_ratings = getSimilarities(id);
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> movie_ratings = new ArrayList<>();
        for (String movieID : movieIDs) {
            int rater_count = 0;
            double weight = 0;
            for (int i = 0; i < numSimilarRaters; i++) {
                Rating r = rater_ratings.get(i);
                Rater rater = RaterDatabase.getRater(r.getItem());
                if (rater.hasRating(movieID)) {
                    rater_count += 1;
                    weight += r.getValue() * rater.getRating(movieID);
                }
            }
            if (rater_count >= minimalRaters) {
                movie_ratings.add(new Rating(movieID, weight / rater_count));
            }

        }
        Collections.sort(movie_ratings, new RatingComparator());
        return movie_ratings;
    }

    public static void main(String[] args) {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        ArrayList<Rating> ratings = fr.getSimilarRatings("1", 3, 1, new TrueFilter());
        for (Rating rating : ratings) {

            System.out.println(rating.getItem() + " " + rating.getValue());
        }
    }

}
