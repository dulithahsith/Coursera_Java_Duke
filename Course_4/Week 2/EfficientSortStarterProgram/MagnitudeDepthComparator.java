import java.util.Comparator;

public class MagnitudeDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        if (q1.getMagnitude() > q2.getMagnitude()) {
            return 1;
        }
        if (q1.getMagnitude() < q2.getMagnitude()) {
            return -1;
        }
        if (q1.getDepth() > q2.getDepth()) {
            return -1;
        }
        if (q1.getDepth() < q2.getDepth()) {
            return 1;
        }
        return 0;
    }
}