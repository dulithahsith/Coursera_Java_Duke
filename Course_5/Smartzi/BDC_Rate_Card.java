import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class BDC_Rate_Card {
    public void printRecords() {
        String csvFile = "test.csv"; // Provide the path to your CSV file
        try (Reader reader = new FileReader(csvFile)) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            HashMap<String, ArrayList<CSVRecord>> hm = new HashMap<>();
            HashMap<String, ArrayList<Double>> hashMap = new HashMap<>();

            for (CSVRecord record : csvParser) {
                if (!record.get(9).equals("STANDARD")) {
                    continue;
                }
                String pick = record.get(2);

                if (!hm.containsKey(pick)) {
                    hm.put(pick, new ArrayList<>());
                }
                hm.get(pick).add(record);
            }
            // Print the records grouped by route
            int c = 0;
            int cc = 0;
            for (String key : hm.keySet()) {
                // if (!key.contains("Heathrow") && !key.contains("Luton") &&
                // !key.contains("LCY")
                // && key.contains("London")) {
                // c += 1;
                // System.out.println("Pickup : " + key);
                // for (CSVRecord record : hm.get(key)) {
                // if (record.get(8).contains("London Transfers")) {
                // cc += 1;
                // }
                // System.out.println(record.get(8) + " - " + record.get(6) + " - " +
                // record.get(10));
                // }
                // }
                for (CSVRecord record : hm.get(key)) {
                    if (record.get(2).contains("London")) {
                        System.out.println("Pickup : " + key);
                    } else {
                        continue;
                    }
                    double a1 = Double.parseDouble(record.get(10));
                    double a2 = Double.parseDouble(record.get(13));

                    System.out.println(record.get(8) + "  -  " + record.get(6) + "  -  " + a1 / a2 + "  >>> " + a2);
                }

            }
            System.out.println(cc);
            System.out.println(c);
            System.out.println(hm.keySet().size());
        } catch (

        IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BDC_Rate_Card brc = new BDC_Rate_Card();
        brc.printRecords();
    }
}
