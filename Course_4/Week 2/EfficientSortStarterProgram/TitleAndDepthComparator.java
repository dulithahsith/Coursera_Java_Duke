import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        if (q1.getInfo().compareToIgnoreCase(q2.getInfo()) > 0) {
            return 1;
        }

        if (q1.getInfo().compareToIgnoreCase(q2.getInfo()) < 0) {
            return -1;
        }
        if (q1.getInfo().compareToIgnoreCase(q2.getInfo()) == 0) {
            if (q1.getDepth() > q2.getDepth()) {
                return -1;
            }
            if (q1.getDepth() < q2.getDepth()) {
                return 1;
            }
            if (q1.getDepth() == q2.getDepth()) {
                return 0;
            }
        }
        return 0;
    }
}