import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;

class CSVReader{
    public void find_record_data(String data) throws IOException {
        File folder = new File("exportdata.csv");
        BufferedReader reader = new BufferedReader(new FileReader(folder));
        String headerline = reader.readLine();
        HashMap<String, List<String>> map = new HashMap<>();
        String[] headers = headerline.split(",");
        for (String header : headers) {
            map.put(header, new ArrayList<>());
        }
        int count = 0;
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            String temp = line.substring(line.indexOf("$"),line.length()-1);
            if(temp.length()>16){
                System.out.println(line);
            }
        }
        System.out.println(count);
    }
}
public class Main{
    public static void main(String args[]) throws IOException {
        CSVReader csvReader = new CSVReader();
        csvReader.find_record_data("k");
    }
}