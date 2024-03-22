
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {
    private int char_No;

    public MarkovModel(int char_No) {
        this.char_No = char_No;
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
        int index = myRandom.nextInt(myText.length() - char_No);
        String key = myText.substring(index, index + char_No);
        sb.append(key);
        for (int k = 0; k < numChars - char_No; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
            key = key.substring(1) + follows.get(index);

        }
        return sb.toString();
    }

}
