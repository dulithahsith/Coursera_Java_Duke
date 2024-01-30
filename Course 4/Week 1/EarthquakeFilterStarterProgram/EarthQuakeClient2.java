import java.util.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read("data/nov20quakedatasmall.atom");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0));
        maf.addFilter(new DepthFilter(-100000, -10000));
        maf.addFilter(new PhraseFilter("any", "a"));
        ArrayList<QuakeEntry> answer = filter(list, maf);
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
    }

    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read("data/nov20quakedatasmall.atom");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 3.0));
        maf.addFilter(new PhraseFilter("any", "Ca"));
        maf.addFilter(new DistanceFilter(10000000, new Location(36.1314, -95.9372)));
        ArrayList<QuakeEntry> answer = filter(list, maf);
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // Filter f1 = new MagnitudeFilter(4.0, 5.0);
        // Filter f2 = new DepthFilter(-35000.0, -12000.0);
        // ArrayList<QuakeEntry> m7 = filter(filter(list, f1), f2);
        // for (QuakeEntry qe : m7) {
        // System.out.println(qe);
        // }

        Filter f3 = new DistanceFilter(10000000, new Location(35.42, 139.43));
        Filter f4 = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> m8 = filter(filter(list, f3), f4);
        for (QuakeEntry qe : m8) {
            System.out.println(qe);
        }
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "../data/nov20quakedata.atom";
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
        EarthQuakeClient2 eq2 = new EarthQuakeClient2();
        // eq2.quakesWithFilter();
        eq2.testMatchAllFilter2();
    }

}
