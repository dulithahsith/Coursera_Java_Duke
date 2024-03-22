import java.util.ArrayList;

import java.util.Collections;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings();
        System.out.println(sr.getMovieSize() + " " + sr.getRaterSize());
        ArrayList<Rating> ratings = sr.getAverageRatings(12);
        System.out.println(ratings.size());
        Collections.sort(ratings, new RatingComparator());
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
        }
    }

    public void getAverageRatingOneMovie(String title) {
        SecondRatings sr = new SecondRatings();
        ArrayList<Rating> ratings = sr.getAverageRatings(3);
        for (Rating rating : ratings) {
            if (sr.getTitle(rating.getItem()).equals(title)) {
                System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
            }
        }
    }

    public static void main(String[] args) {
        MovieRunnerAverage mra = new MovieRunnerAverage();
        mra.printAverageRatings();
        mra.getAverageRatingOneMovie("Vacation");
    }
}