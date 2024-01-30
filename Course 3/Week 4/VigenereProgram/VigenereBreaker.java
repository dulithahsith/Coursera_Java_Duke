import java.util.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        int temp = whichSlice;
        while (temp < message.length()) {
            sb.append(message.charAt(temp));
            temp += totalSlices;
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker ccracker = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String temp = this.sliceString(encrypted, i, klength);
            key[i] = ccracker.getKey(temp);
        }
        return key;
    }

    public void breakVigenere() {
        // WRITE YOUR CODE HERE
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File("secret_message_1.txt"));
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
                sb.append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("404 Error");
        }

        System.out.println(sb.toString());
        int[] keys = this.tryKeyLength(sb.toString(), 4, 'e');
        for (int j : keys) {
            System.out.println(j);
        }
        VigenereCipher vc = new VigenereCipher(keys);
        System.out.println(vc.decrypt(sb.toString()));
    }

    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}
