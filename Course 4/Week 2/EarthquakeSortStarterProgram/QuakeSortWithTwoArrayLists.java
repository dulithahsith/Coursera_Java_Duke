import java.util.*;

public class QuakeSortWithTwoArrayLists {
    // This is the code from the Video of Selection Sort with Two ArrayLists

    public QuakeSortWithTwoArrayLists() {
        // TODO Auto-generated constructor stub
    }

    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes) {
        QuakeEntry min = quakes.get(0);
        for (QuakeEntry q : quakes) {
            if (q.getMagnitude() < min.getMagnitude()) {
                min = q;
            }
        }

        return min;
    }

    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        while (!in.isEmpty()) {
            QuakeEntry minElement = getSmallestMagnitude(in);
            in.remove(minElement);
            out.add(minElement);
        }

        return out;
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int index = from;
        double maxDepth = quakes.get(from).getDepth();
        for (int i = from + 1; i < quakes.size(); i++) {
            if (maxDepth < quakes.get(i).getDepth()) {
                maxDepth = quakes.get(i).getDepth();
                index = i;
            }
        }

        return index;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        ArrayList<QuakeEntry> out = new ArrayList<>();
        while (!in.isEmpty()) {
            QuakeEntry minElement = getSmallestMagnitude(in);
            in.remove(minElement);
            out.add(minElement);
        }
        for (QuakeEntry qe : out) {
            System.out.println(qe);
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        // String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        sortByLargestDepth(list);

        System.out.println("read data for " + list.size() + " quakes");
        list = sortByMagnitude(list);

        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedatasmall.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }

}
