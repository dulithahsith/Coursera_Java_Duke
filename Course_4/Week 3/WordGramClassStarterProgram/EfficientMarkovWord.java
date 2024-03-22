
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

import javax.swing.text.WrappedPlainView;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> hashmap;

    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        this.myOrder = order;
        hashmap = new HashMap<>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    public void buildMap() {
        for (int i = 0; i < myText.length - myOrder; i++) {
            WordGram wg = new WordGram(myText, i, myOrder);
            if (!hashmap.containsKey(wg)) {
                ArrayList<String> nextArr = new ArrayList<String>();
                nextArr.add(myText[i + myOrder]);
                hashmap.put(wg, nextArr);
            } else if (hashmap.containsKey(wg)) {
                ArrayList<String> nextArr = hashmap.get(wg);
                nextArr.add(myText[i + myOrder]);
            }

        }
        int max=0;
        for (WordGram wg : hashmap.keySet()) {
            if(hashmap.get(wg).size()>max){
                max=hashmap.get(wg).size();
            }
            for (String st : hashmap.get(wg)) {
                System.out.print(st + " ");
            }
            System.out.println("");
        }
        System.out.println(hashmap.size());
        System.out.println(max);

    }

    public void printHashMapInfo() {
        for (WordGram wg : hashmap.keySet()) {

            for (String st : hashmap.get(wg)) {
                System.out.print(st + " ");
            }
            System.out.println("");
        }
        System.out.println(hashmap.size());

    }

    private ArrayList<String> getFollows(WordGram kGram) {
        return hashmap.get(kGram);
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder); // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for (int k = 0; k < numWords - myOrder; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows == null || follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }

        return sb.toString().trim();
    }
}