import java.time.Year;
import java.util.ArrayList;

import java.util.Collections;

public class MovieRunnerWithFilters {
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

    public void getAverageRatingOneMovie(String title) {
        SecondRatings sr = new SecondRatings();
        ArrayList<Rating> ratings = sr.getAverageRatings(3);
        for (Rating rating : ratings) {
            if (sr.getTitle(rating.getItem()).equals(title)) {
                System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
            }
        }
    }

    public void printAverageRatingsByYear() {
        YearAfterFilter yaf = new YearAfterFilter(2000);
        ThirdRatings tr = new ThirdRatings();
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(20, yaf);
        Collections.sort(ratings, new RatingComparator());
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println(ratings.size());
    }

    public void printAverageRatingsByGenre() {
        GenreFilter gf = new GenreFilter("Comedy");
        ThirdRatings tr = new ThirdRatings();
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(20, gf);
        Collections.sort(ratings, new RatingComparator());
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println(ratings.size());
    }

    public void printAverageRatingsByMinutes() {
        MinutesFilter mf = new MinutesFilter(105, 135);
        ThirdRatings tr = new ThirdRatings();
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(5, mf);
        Collections.sort(ratings, new RatingComparator());
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println(ratings.size());
    }

    public void printAverageRatingsByDirectors() {
        DirectorsFilter df = new DirectorsFilter(
                "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ThirdRatings tr = new ThirdRatings();
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(4, df);
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

    public void printAverageRatingsByDirectorsAndMinutes() {
        AllFilters afs = new AllFilters();
        afs.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        afs.addFilter(new MinutesFilter(90, 180));
        ThirdRatings tr = new ThirdRatings();
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(3, afs);
        Collections.sort(ratings, new RatingComparator());
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println(ratings.size());
    }

    public static void main(String[] args) {
        // MovieRunnerWithFilters mra = new MovieRunnerWithFilters();
        // mra.printAverageRatings();
        // mra.getAverageRatingOneMovie("Vacation");
        MovieRunnerWithFilters mrf = new MovieRunnerWithFilters();
        // mrf.printAverageRatingsByYear();
        // mrf.printAverageRatingsByGenre();
        // mrf.printAverageRatingsByMinutes();
        // mrf.printAverageRatingsByDirectors();
        // mrf.printAverageRatingsByYearAfterAndGenre();
        mrf.printAverageRatingsByDirectorsAndMinutes();
    }
}