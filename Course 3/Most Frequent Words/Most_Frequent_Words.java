import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class WordFrequencies {
    ArrayList<String> myWords;
    ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    public void findUnique() {
        myFreqs.clear();
        myWords.clear();

        try {
            Scanner scanner = new Scanner(new File("ass1_1.txt"));
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] arrdata = data.split(" ", 0);
                for (String a : arrdata) {
                    String temp = a.trim();
                    if (temp.startsWith("\"") || temp.equals("\n") || temp.equals("")) {
                        continue;
                    }

                    if (!myWords.contains(temp.toLowerCase())) {
                        myWords.add(temp.toLowerCase());
                        myFreqs.add(myWords.indexOf(temp.toLowerCase()), 1);
                    } else {
                        int index = myWords.indexOf(temp.toLowerCase());
                        Integer temps = (Integer) myFreqs.get(index);

                        myFreqs.set(myWords.indexOf(temp.toLowerCase()), temps + 1);
                    }
                }
            }
            scanner.close();
            int max = 0;
            for (Object word : myFreqs) {
                Integer temp = (Integer) word;
                if (temp > max) {
                    max = temp;
                }
                ;
            }
            System.out.println("Most used word is " + myWords.get(myFreqs.indexOf(max)) + " " + max);

            for (String word : myWords) {
                System.out.println(word);
            }
            System.out.println(myWords.size());
        } catch (FileNotFoundException e) {
            System.out.println("Error occured");
            e.printStackTrace();
        }
    }
}

public class ass1 {
    public static void main(String args[]) {
        WordFrequencies wf = new WordFrequencies();
        wf.findUnique();
    }
}