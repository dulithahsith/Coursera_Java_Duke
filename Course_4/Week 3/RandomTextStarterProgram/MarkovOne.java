
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;

import edu.duke.FileResource;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1);
        sb.append(key);
        for (int k = 0; k < numChars - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
            key = follows.get(index);
            System.out.println(key);
        }
        return sb.toString();
    }

    public ArrayList<String> getFollows(String key) {
        String temp = new String(myText);
        ArrayList<String> chars = new ArrayList<>();
        while (temp.contains(key)) {
            int index = temp.indexOf(key);
            if (index + key.length() < temp.length()) {
                chars.add(temp.charAt(index + key.length()) + "");
                temp = temp.substring(index + key.length());
            }
            if (index + key.length() == temp.length()) {
                break;
            }
        }
        return chars;
    }

    public static void main(String[] args) {
        FileResource fr = new FileResource("data/melville.txt");
        String st = fr.asString();
        MarkovOne mo = new MarkovOne();
        mo.setTraining(st);
        System.out.println(mo.getFollows("th").size());

    }

}
