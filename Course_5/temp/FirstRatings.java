import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<>();
        try (Reader reader = new FileReader(filename)) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            int i = 0;
            for (CSVRecord record : csvParser) {
                if (i == 0) {
                    i = 1;
                } else {
                    movies.add(new Movie(record.get(0), record.get(1), record.get(2), record.get(4), record.get(5),
                            record.get(3), record.get(7), Integer.parseInt(record.get(6))));
                }
            }
            reader.close();
            csvParser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public void testLoadMovies() {
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> movies = fr.loadMovies("data/ratedmoviesfull.csv");
        int count = 0;
        System.out.println(movies.size());
        HashMap<String, Integer> map_dir_movies = new HashMap<>();
        for (Movie movie : movies) {
            // if (movie.getGenres().contains("Comedy")) {
            // count += 1;
            // }
            // if (movie.getMinutes() > 150) {
            // count += 1;
            // }

            if (!map_dir_movies.keySet().contains(movie.getDirector())) {
                map_dir_movies.put(movie.getDirector(), 1);
            } else {
                int temp = map_dir_movies.get(movie.getDirector());
                map_dir_movies.put(movie.getDirector(), temp + 1);
            }

        }
        int max = 0;
        String max_dir = "";
        for (String director : map_dir_movies.keySet()) {
            if (map_dir_movies.get(director) > max) {
                max = map_dir_movies.get(director);
                max_dir = director;
            }
        }
        System.out.println(max + " " + max_dir);
    }

    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raters = new ArrayList<>();
        HashMap<String, Rater> map = new HashMap<>();
        try (Reader reader = new FileReader(filename)) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            int i = 0;
            for (CSVRecord record : csvParser) {
                if (i == 0) {
                    i = 1;
                } else {
                    if (!map.keySet().contains(record.get(0))) {
                        map.put(record.get(0), new EfficientRater(record.get(0)));
                        Rater r = map.get(record.get(0));
                        r.addRating(record.get(1), Double.parseDouble(record.get(2)));
                    } else {
                        Rater r = map.get(record.get(0));
                        r.addRating(record.get(1), Double.parseDouble(record.get(2)));
                    }
                }
            }
            reader.close();
            csvParser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String key : map.keySet()) {
            raters.add(map.get(key));
        }
        return raters;
    }

    public void testLoadRaters() {
        FirstRatings fr = new FirstRatings();
        ArrayList<Rater> raters = fr.loadRaters("data/ratings.csv");
        int count = 0;
        // for (Rater rater : raters) {
        // if (rater.getID().equals("193")) {
        // for (String item : rater.getItemsRated()) {
        // System.out.println(item + " " + rater.getRating(item));
        // count += 1;
        // }
        // }
        // }
        // int max = 0;
        // String max_rater = "";
        // for (Rater rater : raters) {
        // if (rater.getItemsRated().size() > max) {
        // max_rater = rater.getID();
        // max = rater.getItemsRated().size();
        // }
        // }
        // System.out.println(count);
        ArrayList<String> movies = new ArrayList<>();
        System.out.println(raters.size());
        // System.out.println(max + " " + max_rater);
        for (Rater rater : raters) {
            for (String item : rater.getItemsRated()) {
                if (!movies.contains(item)) {
                    count += 1;
                    movies.add(item);
                }
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        FirstRatings fr = new FirstRatings();
        // fr.testLoadMovies();
        fr.testLoadRaters();
    }

}
