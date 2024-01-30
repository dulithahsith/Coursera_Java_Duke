import java.io.*;
import java.util.ArrayList;

public class Tester {

    public static void main(String[] args) {

        // Specify the path to your CSV file
        String file = "results.csv";

        // Create a CSV parser
        BufferedReader reader = null;
        String line = "";
        ArrayList<String> lines = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if (row[1].equals("BIOLOGICAL SCIENCE") && row[3].contains("NEW") && row[15].equals("9")
                        && row[16].equals("December") && row[18].equals("female") && row[17].equals("2001")) {
                    lines.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (String liner : lines) {
            System.out.println(liner);
        }
    }

}
