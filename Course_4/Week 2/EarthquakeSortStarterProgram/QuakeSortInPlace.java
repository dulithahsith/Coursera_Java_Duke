
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 0; i < quakeData.size() - 1 - numSorted; i++) {
            if (quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()) {
                QuakeEntry temp = quakeData.get(i);
                quakeData.set(i, quakeData.get(i + 1));
                quakeData.set(i + 1, temp);
            }
        }
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {

        for (int i = 0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }

    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
            if (checkInSortedOrder(in)) {
                System.out.println(i + "jjjjjjjjj");
                break;
            }
        }
        for (QuakeEntry qe : in) {
            System.out.println(qe);
        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            int index = getSmallestMagnitude(in, i);
            QuakeEntry temp = in.get(index);
            in.set(index, in.get(i));
            in.set(i, temp);
            if (checkInSortedOrder(in)) {
                System.out.println(i + "NNNNN");
                break;
            }
        }
        for (QuakeEntry qe : in) {
            System.out.println(qe);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i = 0; i < quakes.size() - 1; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        // String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");
        // sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSort(list);
        // for (int i = 0; i < list.size() - 1; i++) {
        // onePassBubbleSort(list, i);
        // if (checkInSortedOrder(list)) {
        // break;
        // }
        // }
        // for (QuakeEntry qe : list) {
        // System.out.println(qe);
        // }

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
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

    public static void main(String[] args) {
        QuakeSortInPlace qsip = new QuakeSortInPlace();
        qsip.testSort();

    }
}
