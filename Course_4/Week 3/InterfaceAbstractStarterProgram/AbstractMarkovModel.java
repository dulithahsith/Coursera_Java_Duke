
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;

    public AbstractMarkovModel() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    protected ArrayList<String> getFollows(String key) {
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

    abstract public String getRandomText(int numChars);

}
