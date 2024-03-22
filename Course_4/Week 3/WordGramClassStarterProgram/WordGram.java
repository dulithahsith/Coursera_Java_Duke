
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public int hashCode() {
        myHash = this.toString().hashCode();
        return myHash;
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }

    public int length() {
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString() {
        String ret = "";
        for (String word : myWords) {
            ret = ret + word;
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (this.length() != other.length()) {
            return false;
        }
        for (int k = 0; k < myWords.length; k++) {
            if (!myWords[k].equals(other.wordAt(k))) {
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) {
        String[] newWords = new String[myWords.length];
        newWords[myWords.length - 1] = word;
        for (int i = 0; i < myWords.length - 1; i++) {
            newWords[i] = myWords[i + 1];
        }
        WordGram out = new WordGram(newWords, 0, myWords.length);

        return out;
    }

}