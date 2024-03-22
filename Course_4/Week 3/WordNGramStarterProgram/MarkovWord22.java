
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWord22 implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWord22() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    private int indexOf(String[] words,
            String target1, String target2, int start) {

        for (int i = start; i < words.length - 1; i++) {

            if (words[i].equals(target1) && words[i + 1].equals(target2))
                return i;
        }

        return -1;
    }

    private ArrayList<String> getFollows(String key1, String key2) {

        ArrayList<String> follows = new ArrayList<String>();

        int index = indexOf(myText, key1, key2, 0);

        while (index != -1) {
            follows.add(myText[index + 1]);
            index = indexOf(myText, key1, key2, index + 1);
        }

        return follows;
    }

    public void testIndexOf() {
        String[] words = "this is just a test yes this is a simple tes".split("\\s+");
        System.out.println("this is:\t" + indexOf(words, "this", "is", 0));
        System.out.println("is just:\t" + indexOf(words, "is", "just", 0));
        System.out.println("this is:\t" + indexOf(words, "this", "is", 1));
    }

    public void testGetFollows() {
        String[] words = myText;
        for (int i = 0; i < words.length - 2; i++) {
            System.out.println(words[i] + " " + words[i + 1] + ":\t" + getFollows(words[i], words[i + 1]));
        }
    }

    @Override
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 1);
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1);
        sb.append(key2);

        for (int k = 0; k < numWords - 1; k++) {
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);

            key1 = key2;
            key2 = next;
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) {
        MarkovWordTwo markov = new MarkovWordTwo();
        markov.setTraining("this is just a test yes this is a simple test");
        // markov.testIndexOf();

    }

}
