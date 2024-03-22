import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser eq = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = eq.read(source);
        System.out.println("Read " + list.size() + " quakes");
    }

    public int indexOfLargest(ArrayList<QuakeEntry> Data) {
        double max = 0;
        int index = 0;
        for (QuakeEntry qe : Data) {
            if (qe.getMagnitude() > max) {
                max = qe.getMagnitude();
                index = Data.indexOf(qe);
            }
        }
        return index;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> duplicate_q_data = new ArrayList<>();
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            duplicate_q_data.add(qe);
        }
        for (int i = 0; i < howMany; i++) {
            int maxIndex = 0;
            for (int j = 1; j < duplicate_q_data.size(); j++) {
                if (duplicate_q_data.get(maxIndex).getMagnitude() < duplicate_q_data.get(j).getMagnitude()) {
                    maxIndex = j;
                }
            }
            answer.add(duplicate_q_data.get(maxIndex));
            duplicate_q_data.remove(maxIndex);
        }
        return answer;

    }

    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();
        EarthQuakeParser eq = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = eq.read(source);
        ArrayList<QuakeEntry> answer = lq.getLargest(list, 50);
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
    }
}