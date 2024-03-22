import java.util.ArrayList;

import java.util.Collections;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        ThirdRatings sr = new ThirdRatings();
        System.out.println("Number of raters: " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> ratings = sr.getAverageRatings(35);
        System.out.println(ratings.size());
        Collections.sort(ratings, new RatingComparator());
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println(ratings.size());
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        AllFilters afs = new AllFilters();
        afs.addFilter(new YearAfterFilter(1990));
        afs.addFilter(new GenreFilter("Drama"));
        ThirdRatings tr = new ThirdRatings();
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(8, afs);
        Collections.sort(ratings, new RatingComparator());
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println(ratings.size());
    }

    public void printSimilarRatings() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> ratings = fr.getSimilarRatings("65", 20, 5, new TrueFilter());
        for (Rating rating : ratings) {

            System.out.println(rating.getItem() + " " + rating.getValue());
        }
    }

    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> ratings = fr.getSimilarRatings("65", 20, 5, new GenreFilter("Action"));
        for (Rating rating : ratings) {

            System.out.println(rating.getItem() + " " + rating.getValue());
        }
    }

    public void printSimilarRatingsByDirector() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> ratings = fr.getSimilarRatings("1034", 10, 2,
                new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"));
        for (Rating rating : ratings) {

            System.out.println(rating.getItem() + " " + rating.getValue());
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        AllFilters afs = new AllFilters();
        afs.addFilter(new GenreFilter("Adventure"));
        afs.addFilter(new MinutesFilter(100, 200));
        ArrayList<Rating> ratings = fr.getSimilarRatings("65", 10, 2, afs);
        for (Rating rating : ratings) {

            System.out.println(rating.getItem() + " " + rating.getValue());
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        AllFilters afs = new AllFilters();
        // afs.addFilter(new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred
        // Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        // afs.addFilter(new GenreFilter("Drama"));
        afs.addFilter(new MinutesFilter(70, 200));
        afs.addFilter(new YearAfterFilter(1975));
        // afs.addFilter(new MinutesFilter(80, 100));
        ArrayList<Rating> ratings = fr.getSimilarRatings("314", 10, 5, afs);
        for (Rating rating : ratings) {

            System.out.println(rating.getItem() + " " + rating.getValue());
        }
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings mrsr = new MovieRunnerSimilarRatings();
        // mrsr.printSimilarRatingsByDirector();
        // mrsr.printSimilarRatingsByGenreAndMinutes();
        // mrsr.printSimilarRatingsByYearAfterAndMinutes();
        mrsr.printSimilarRatings();
    }

}
