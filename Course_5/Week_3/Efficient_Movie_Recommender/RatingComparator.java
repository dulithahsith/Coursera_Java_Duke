import java.util.Comparator;

public class RatingComparator implements Comparator<Rating> {
    public int compare(Rating rating1, Rating rating2) {
        if (rating1.getValue() > rating2.getValue()) {
            return 1;
        } else {
            return -1;
        }
    }

}