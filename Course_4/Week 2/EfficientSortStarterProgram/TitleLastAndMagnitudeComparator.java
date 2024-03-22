import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] temp1 = q1.getInfo().split(" ");
        String[] temp2 = q2.getInfo().split(" ");
        if (temp1[temp1.length - 1].compareToIgnoreCase(temp2[temp2.length - 1]) < 0) {
            return -1;
        }
        if (temp1[temp1.length - 1].compareToIgnoreCase(temp2[temp2.length - 1]) > 0) {
            return 1;
        }
        if (q1.getMagnitude() < q2.getMagnitude()) {
            return -1;
        }
        if (q1.getMagnitude() > q2.getMagnitude()) {
            return 1;
        }
        return 0;
    }
}