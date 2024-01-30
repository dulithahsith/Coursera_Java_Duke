
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<>();
    }

    public void readFile(String filename) throws IOException {
        // complete method
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while (line != null) {
            LogEntry log = WebLogParser.parseEntry(line);
            records.add(log);
            line = br.readLine();
        }
        br.close();

    }

    public void countUniqueIPs() {
        ArrayList<String> temp = new ArrayList<>();
        int unique_count = 0;
        for (LogEntry le : records) {
            if (temp.indexOf(le.getIpAddress()) == -1) {
                unique_count += 1;
                temp.add(le.getIpAddress().toString());
            }
        }
        System.out.println("Unique IP address count is " + unique_count);
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    public void uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<LogEntry> temp_logs = new ArrayList<>();
        for (LogEntry le : records) {
            String texts[] = le.getAccessTime().toString().split(" ");
            String tempString = texts[1] + " " + texts[2];
            if (tempString.equals(someday)) {
                if (temp.indexOf(le.getIpAddress()) == -1) {
                    temp.add(le.getIpAddress());
                    temp_logs.add(le);
                }
            }
        }
        for (LogEntry le : temp_logs) {
            System.out.println(le);
        }
        System.out.println(temp_logs.size());
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> temp = new ArrayList<>();
        int count = 0;
        for (LogEntry le : records) {
            if (le.getStatusCode() > low && le.getStatusCode() < high) {
                if (temp.indexOf(le.getIpAddress()) == -1) {
                    temp.add(le.getIpAddress());
                    count += 1;
                }
            }
        }
        return count;
    }
    // COUNTING WEBSITE VISITS

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> visits_map = new HashMap<>();
        for (LogEntry le : records) {
            if (!visits_map.keySet().contains(le.getIpAddress())) {
                visits_map.put(le.getIpAddress(), 1);
            } else {
                visits_map.put(le.getIpAddress(), visits_map.get(le.getIpAddress()) + 1);
            }
        }
        for (String s : visits_map.keySet()) {
            System.out.println(s + " " + visits_map.get(s));
        }
        return visits_map;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> visits_map) {
        int max = 0;
        for (String s : visits_map.keySet()) {
            if (max < visits_map.get(s)) {
                max = visits_map.get(s);
            }
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visits_map) {
        int max = mostNumberVisitsByIP(visits_map);
        ArrayList<String> max_IPs = new ArrayList<>();
        for (String s : visits_map.keySet()) {
            if (visits_map.get(s) == max) {
                max_IPs.add(s);
            }
        }
        return max_IPs;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> day_to_ips = new HashMap<>();
        for (LogEntry le : records) {
            String[] texts = le.getAccessTime().toString().split(" ");
            String tempString = texts[1] + " " + texts[2];
            if (!day_to_ips.keySet().contains(tempString)) {
                day_to_ips.put(tempString, new ArrayList<>());
                day_to_ips.get(tempString).add(le.getIpAddress());
            } else {
                day_to_ips.get(tempString).add(le.getIpAddress());
            }
        }
        return day_to_ips;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> day_to_ips) {
        int max = 0;
        String day = "";
        for (String s : day_to_ips.keySet()) {
            if (max < day_to_ips.get(s).size()) {
                max = day_to_ips.get(s).size();
            }
        }
        for (String s : day_to_ips.keySet()) {
            if (max == day_to_ips.get(s).size()) {
                System.out.println(s);
                day = s;
            }
        }
        return day;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> day_to_ips, String day) {
        ArrayList<String> ips = day_to_ips.get(day);
        HashMap<String, Integer> visit_maps = new HashMap<>();
        for (String ip : ips) {
            if (!visit_maps.keySet().contains(ip)) {
                visit_maps.put(ip, 1);
            } else {
                visit_maps.put(ip, visit_maps.get(ip) + 1);
            }
        }
        return this.iPsMostVisits(visit_maps);
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

}
