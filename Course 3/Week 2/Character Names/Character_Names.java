import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class CharactersInPlay {
    ArrayList<String> myWords;
    ArrayList<Integer> myFreqs;

    public CharactersInPlay() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    public void update(String person) {
        int index = myWords.indexOf(person.toLowerCase());
        if (index != -1) {
            myFreqs.set(index, myFreqs.get(index) + 1);
        } else {
            myWords.add(person.toLowerCase());
            myFreqs.add(1);
        }
    }

    public void findAllCharacters() {
        myFreqs.clear();
        myWords.clear();

        try {
            Scanner scanner = new Scanner(new File("ass1.txt"));
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                if (data.startsWith(" ") || data.equals("\n") || data.equals("")) {
                    continue;
                }

                data.trim();
                System.out.println(data);

            }
            scanner.close();

        } catch (

        FileNotFoundException e) {
            System.out.println("Error occured");
            e.printStackTrace();
        }
    }
}

public class ass2 {
    public static void main(String args[]) {
        CharactersInPlay CIP = new CharactersInPlay();
        CIP.findAllCharacters();
    }
}