
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int char_No;
    private HashMap<String, ArrayList<String>> hashmap;

    public EfficientMarkovModel(int char_No) {
        this.char_No = char_No;
        myRandom = new Random();
        hashmap = new HashMap<>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public void buildMap() {
        for (int i = 0; i < myText.length() - char_No; i++) {
            String temp = myText.substring(i, i + char_No);
            if (!hashmap.containsKey(temp)) {
                ArrayList<String> nextArr = new ArrayList<String>();
                nextArr.add(myText.substring(i + char_No, i + char_No + 1));

                hashmap.put(temp, nextArr);
            } else if (hashmap.containsKey(temp)) {
                ArrayList<String> nextArr = hashmap.get(temp);
                nextArr.add(myText.substring(i + char_No, i + char_No + 1));
            }
        }
        int max = 0;
        for (String key : hashmap.keySet()) {
            System.out.print(key + " : ");
            if (max < hashmap.get(key).size()) {
                max = hashmap.get(key).size();
            }
            // for (String element : hashmap.get(key)) {

            // System.out.print(element + " ");
            // }
            System.out.println("");
        }
        System.out.println(hashmap.keySet().size());
        System.out.println(max);
        // System.out.println(hashmap.get("he").size());
    }

    public ArrayList<String> getFollows(String key) {
        return hashmap.get(key);
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - char_No);
        String key = myText.substring(index, index + char_No);
        sb.append(key);
        this.buildMap();
        for (int k = 0; k < numChars - char_No; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows == null || follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
            key = key.substring(1) + follows.get(index);
        }
        System.out.println(hashmap.size());
        return sb.toString();
    }

    public String toString() {
        return "This Efficient model is of " + char_No + ".";
    }

}
