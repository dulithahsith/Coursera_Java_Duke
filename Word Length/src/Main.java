import java.io.*;

class WordLength{
    public int[] countWordLengths(File resource, int[] counts) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(resource));
        while(true) {
            String line = reader.readLine();
            if(line==null){
                break;
            }
            String[] words = line.split(" ");
            for (String word : words) {
                counts[word.length()] += 1;
            }
        }
        return counts;
    }
    public boolean characterIsLetter(char ch){
        int asc = (int) ch;
        if((asc<=90 && asc>=65)||(asc>=97 && asc<=122)){
            return true;
        }
        else{
            return false;
        }
    }
    public void testWordLength() throws IOException {
        File file = new File("romeo.txt");
        int[] counts = new int[31];
        for (int i = 0; i < 31; i++) {
            counts[i] = 0;
        }
        int[] newcounts = countWordLengths(file, counts);
        for (int i : newcounts) {
            System.out.println(i);
        }
    }
}
public class Main{
    public static void main(String args[]) throws IOException {
        WordLength wl = new WordLength();
        wl.testWordLength();
    }
}