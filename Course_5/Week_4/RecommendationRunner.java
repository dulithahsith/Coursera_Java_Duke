import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender {

    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movieIDs = new ArrayList<>();
        movieIDs = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> givenlist = new ArrayList<>();
        int count = 12;
        for (int i = 0; i < count; i++) {
            Random random = new Random();
            String movieID = movieIDs.get(random.nextInt(movieIDs.size()));
            if (!givenlist.contains(movieID)) {
                givenlist.add(movieID);
            } else {
                count += 1;
            }
        }
        return givenlist;
    }

    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID, 20, 1, new TrueFilter());
        if (ratings.size() == 0) {
            System.out.println(
                    "<style>\n" +
                            ".error-message {\n" +
                            "    font-size: 16px;\n" +
                            "    color: red;\n" +
                            "}\n" +
                            "</style>\n" +
                            "<div class=\"error-message\">No ratings to show</div>");

        } else {
            ArrayList<String> MovieList = getItemsToRate();
            ArrayList<String> displaying = new ArrayList<>();
            for (Rating rating : ratings) {
                if (!MovieList.contains(rating.getItem())) {
                    displaying.add(rating.getItem());
                }
                if (displaying.size() == 10) {
                    break;
                }
            }
            System.out.println("Size of displaying is: " + displaying.size());
            System.out.println("<style>\n" +
                    "    body {\n" +
                    "        font-family: Arial, sans-serif;\n" +
                    "    }\n" +
                    "\n" +
                    "    h2 {\n" +
                    "        color: #333;\n" +
                    "    }\n" +
                    "\n" +
                    "    table {\n" +
                    "        width: 100%;\n" +
                    "        border-collapse: collapse;\n" +
                    "        margin-top: 20px;\n" +
                    "    }\n" +
                    "\n" +
                    "    th, td {\n" +
                    "        padding: 10px;\n" +
                    "        text-align: left;\n" +
                    "        border-bottom: 1px solid #ddd;\n" +
                    "    }\n" +
                    "\n" +
                    "    th {\n" +
                    "        background-color: #f2f2f2;\n" +
                    "    }\n" +
                    "\n" +
                    "    tr:hover {\n" +
                    "        background-color: #f5f5f5;\n" +
                    "    }\n" +
                    "</style>\n" +
                    "\n" +
                    "<h2>Recommended Movies</h2>\n" +
                    "<table border=\"1\">\n" +
                    "    <thead>\n" +
                    "        <tr>\n" +
                    "            <th>Blank</th>\n" +
                    "            <th>Poster</th>\n" +
                    "            <th>Name</th>\n" +
                    "            <th>Genre</th>\n" +
                    "            <th>Country</th>\n" +
                    "        </tr>\n" +
                    "    </thead>\n" +
                    "    <tbody>\n");
            int No = 1;
            for (String movieID : displaying) {
                System.out.println("<tr><td>" + No + "</td>" +
                        "<td><img src=\"" + MovieDatabase.getPoster(movieID) + "\" width=\"50\" height=\"70\"></td>" +
                        "<td>" + MovieDatabase.getTitle(movieID) + "</td>" +
                        "<td>" + MovieDatabase.getGenres(movieID) + "</td>" +
                        "<td>" + MovieDatabase.getCountry(movieID) + "</td></tr>");
                No += 1;

            }
            System.out.println("</tbody>\n" +
                    "</table>");

        }
    }

    public static void main(String[] args) {
        RecommendationRunner rr = new RecommendationRunner();
        rr.printRecommendationsFor("65");
    }
}