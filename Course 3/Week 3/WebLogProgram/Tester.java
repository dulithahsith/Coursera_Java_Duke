
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.IOException;
import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer analyzer = new LogAnalyzer();
        try {
            analyzer.readFile("weblog2_log.txt");
        } catch (IOException e) {
            System.out.println("Error");
        }

        // analyzer.countUniqueIPs();
        // analyzer.uniqueIPVisitsOnDay("Sep 24");
        // System.out.println(analyzer.countUniqueIPsInRange(200, 299));
        // analyzer.printAllHigherThanNum(400);
        // analyzer.countVisitsPerIP();
        // HashMap<String, Integer> visit_map = analyzer.countVisitsPerIP();
        // System.out.println(analyzer.mostNumberVisitsByIP(visit_map));
        // for (String s : analyzer.iPsMostVisits(visit_map)) {
        // System.out.println(s);
        // }
        HashMap<String, ArrayList<String>> test = analyzer.iPsForDays();
        // for (String s : test.keySet()) {
        // for (String ss : test.get(s)) {
        // System.out.println(s + " " + ss);
        // }
        // }
        // ArrayList<String> list = analyzer.iPsWithMostVisitsOnDay(test, "Sep 30");
        // for (String s : list) {
        // System.out.println(s);
        // }
        // System.out.println(analyzer.dayWithMostIPVisits(test));
        for (String s : analyzer.iPsWithMostVisitsOnDay(test, "Sep 29")) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.testLogAnalyzer();
    }
}
