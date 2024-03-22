
/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DifferentSorters {
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";

        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        // Collections.sort(list);

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    QuakeEntry temp = list.get(j + 1);
                    list.set(j + 1, list.get(j));
                    list.set(j, temp);
                }
            }
        }

        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        System.out.println(list.get(600));

    }

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    public void sortByTitleAndDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleAndDepthComparator());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        System.out.println(list.get(500));

    }

    public void sortByLastWordInTitle() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        System.out.println(list.get(500));

    }

    // This is the final sort by compare to method
    public void sortByCompareTo2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataDec6sample2.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new MagnitudeDepthComparator());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        System.out.println(list.get(50));

    }

    public static void main(String[] args) {
        DifferentSorters ds = new DifferentSorters();
        // ds.sortWithCompareTo();
        String s1 = "abcdef";
        String s2 = "abcdeg";
        if (s1.compareToIgnoreCase(s2) < 0) {
            System.out.println("NNNNNNNN");
        }
        ds.sortByTitleAndDepth();
        // ds.sortByLastWordInTitle();
        // ds.sortWithCompareTo();
        // ds.sortByTitleAndDepth();
        // ds.sortByLastWordInTitle();

    }

}
