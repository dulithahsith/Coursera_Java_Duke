import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class WordsInFiles {

    private HashMap<String, ArrayList<String>> filenames;

    public WordsInFiles() {
        filenames = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] words = data.split(" ");
                for (String word : words) {

                    if (!filenames.containsKey(word)) {
                        filenames.put(word, new ArrayList<>());
                        filenames.get(word).add(f.getName());
                    } else {
                        if (filenames.get(word).contains(f.getName())) {
                            continue;
                        } else {
                            filenames.get(word).add(f.getName());
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public void buildWordFileMap() {
        filenames.clear();
        String[] files = { "hamlet.txt", "macbeth.txt", "likeit.txt", "caesar.txt", "romeo.txt", "confucius.txt",
                "errors.txt" };
        for (String file : files) {
            addWordsFromFile(new File(file));
        }
    }

    public int maxNumber() {
        int max = 0;
        String word = "";
        for (String file : filenames.keySet()) {
            if (file == "") {
                continue;
            }
            if (filenames.get(file).size() > max) {
                max = filenames.get(file).size();
                word = file;
            }
        }
        System.out.println("Word is :" + word + "<-this");
        int count = 0;
        for (String file : filenames.keySet()) {
            if (file.equals("tree")) {
                for (String temp : filenames.get(file)) {
                    System.out.println(temp);
                }
            }
            if (filenames.get(file).size() == 4) {

                count += 1;
            }
        }
        System.out.println(count);
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordlist = new ArrayList<>();
        for (String file : filenames.keySet()) {
            if (filenames.get(file).size() == number) {
                wordlist.add(file);
            }
        }
        return wordlist;
    }

    public void printFilesIn(String word) {
        for (String text : filenames.get(word)) {
            System.out.println(text);
        }
    }
}

public class Words_in_files {
    public static void main(String args[]) {
        WordsInFiles wif = new WordsInFiles();
        wif.buildWordFileMap();
        System.out.println(wif.maxNumber());
    }
}