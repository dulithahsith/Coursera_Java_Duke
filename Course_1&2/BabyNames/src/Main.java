import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;


class CSVReader{
    public void find_record_data(String data) throws IOException {
        File folder = new File("us_babynames_by_year");
        File[] files = folder.listFiles();
        String k=null;
        int min=50000;
        double tot=0;
        String name = null;
        for(File file:files) {
            System.out.println(file.getName());
            if(!file.getName().equals("yob1990.csv")){
                continue;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int count = 0;
            int f_count=0;
            while (true) {
                String line = reader.readLine();
                count++;
                if (line == null) {
                    break;
                }
                String[] datums = line.split(",");
                if(datums[0].equals("Drew")&&datums[1].equals("M") ){
                    break;
                }
                if(datums[1].equals("M")){
                    tot+=Integer.parseInt(datums[2]);
                }
            }
        break;
        }
        System.out.println(min);
        System.out.println(name);
        System.out.println(tot);
    }
}
public class Main{
    public static void main(String args[]) throws IOException {
        CSVReader csvReader = new CSVReader();
        csvReader.find_record_data("k");
    }
}