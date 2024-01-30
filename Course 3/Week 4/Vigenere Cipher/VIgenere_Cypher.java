import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

class CaesarCypher {
    public String encrypt(String text, int key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int asc = (char) text.charAt(i);
            char new_char = text.charAt(i);
            if (asc >= 97 && asc <= 122) {
                new_char = (char) (97 + (asc - 97 + key) % 26);
            }
            if (asc >= 65 && asc <= 90) {
                new_char = (char) (65 + (asc - 65 + key) % 26);
            }
            sb.append(new_char);
        }
        return sb.toString();
    }

    public String decrypt(String text, int key) {
        return this.encrypt(text, 26 - (key % 26));
    }
}

class CaesarCracker {
    char common_l;

    public CaesarCracker(char common_l) {
        this.common_l = common_l;
    }

    public int charCount(String text) {
        int count = 0;
        for (char i : text.toCharArray()) {
            if (common_l == i) {
                count += 1;
            }
        }
        return count;
    }

    public int getKey(String text) {
        int max = 0;
        int key = -1;
        CaesarCypher ccypher = new CaesarCypher();
        for (int i = 0; i < 26; i++) {
            String temp_txt = ccypher.encrypt(text, i);
            int temp = this.charCount(temp_txt.toLowerCase());
            if (temp > max) {
                max = temp;
                key = i;
            }
        }
        return 26 - key;
    }
}

class VigenereCipher {
    int[] key;
    CaesarCypher cipher;

    public VigenereCipher(int[] key) {
        this.key = key;
        cipher = new CaesarCypher();
    }

    public String encrypt(String input) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            sb.append(cipher.encrypt((String) (c + ""), key[i % (key.length)]));
            i++;
        }
        return sb.toString();
    }

    public String decrypt(String input) {
        int[] new_key = new int[key.length];
        int count = 0;
        for (int k : key) {
            new_key[count] = 26 - (k % 26);
            count++;
        }
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            sb.append(cipher.encrypt((String) (c + ""), new_key[i % (key.length)]));
            i++;
        }
        return sb.toString();
    }

}

class VigenereBreaker {
    // Following method identifies the characters under the influence of a given
    // key, the starting point that the key is applied and the total number of keys
    // should be given.
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
        int[] keys = new int[klength];
        CaesarCracker ccracker = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String temp = this.sliceString(encrypted, i, klength);
            keys[i] = ccracker.getKey(temp);
        }
        return keys;
    }

    public void breakVigenere() throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File("secret_message_4.txt"), StandardCharsets.UTF_8);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
                sb.append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("404 Error");
        }

        String[] languages = { "Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish" };
        HashMap<String, HashSet<String>> dictionaries = new HashMap<>();
        for (String language : languages) {
            String pathname = "dictionaries/" + language + ".txt";
            HashSet<String> dictionary = readDictionary(new File(pathname));
            dictionaries.put(language, dictionary);
        }
        for (String language : dictionaries.keySet()) {
            System.out.println(language + dictionaries.get(language).iterator().next());
        }
        this.breakForAllLangs(sb.toString(), dictionaries);
    }

    public HashSet<String> readDictionary(File file) {
        HashSet<String> hs = new HashSet<String>();
        try (Scanner scanner = new Scanner(file);) {
            while (scanner.hasNextLine()) {
                hs.add(scanner.nextLine().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            System.out.println("404 File not found");
        }
        return hs;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W+");
        int count = 0;
        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public int[] breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int max = 0;
        int key_length = 0;
        int quiz_4 = 0;
        int[] real_keys = {};
        char mostCommon = mostCommonCharIn(dictionary);
        for (int i = 1; i <= 100; i++) {
            int[] keys = this.tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(keys);
            String temp = vc.decrypt(encrypted);
            int count = this.countWords(temp, dictionary);
            if (i == 38) {
                quiz_4 = count;
            }
            if (count > max) {
                max = count;
                real_keys = keys;
                key_length = keys.length;
            }
        }
        VigenereCipher vc = new VigenereCipher(real_keys);
        System.out.println(vc.decrypt(encrypted));
        int[] results = new int[2 + real_keys.length];
        results[0] = key_length;
        results[1] = max;
        for (int i = 0; i < real_keys.length; i++) {
            results[i + 2] = real_keys[i];
            System.out.print(real_keys[i] + " ");
        }
        System.out.println("");
        return results;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charcount = new HashMap<>();
        for (int i = 97; i <= 122; i++) {
            charcount.put((char) i, 0);
        }
        for (String word : dictionary) {
            for (char ch : word.toCharArray()) {
                if (charcount.keySet().contains(ch)) {
                    int temp = charcount.get(ch) + 1;
                    charcount.put(ch, temp);
                }
            }
        }
        int max = 0;
        char letter = ' ';
        for (char ch : charcount.keySet()) {
            if (charcount.get(ch) > max) {
                max = charcount.get(ch);
                letter = ch;
            }
        }
        return letter;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> dictionaries) {
        int real_max = 0;
        int real_klength = 0;
        int[] real_keys = {};
        String lang = "";
        for (String language : dictionaries.keySet()) {
            int max, klength;
            int[] results = this.breakForLanguage(encrypted, dictionaries.get(language));
            max = results[1];
            klength = results[0];
            if (max > real_max) {
                real_max = max;
                real_klength = klength;
                real_keys = new int[klength];
                lang = language;
                for (int i = 2; i < results.length; i++) {
                    real_keys[i - 2] = results[i];
                }
            }

        }
        VigenereCipher vc = new VigenereCipher(real_keys);
        System.out.println(vc.decrypt(encrypted));
        System.out.println(lang);
    }

}

public class VIgenere_Cypher {
    public static void main(String[] args) {
        CaesarCypher ob = new CaesarCypher();
        ob.encrypt("null", 28);
        String text = ob.decrypt("""
                Rj ridrj v fj sriÃµvj rjjzercrufj
                Hlv, ur ftzuvekrc girzr cljzkrer,
                Gfi drivj eletr uv rekvj ermvxrufj
                Grjjrird rzeur rcÃ©d ur Krgifsrer,
                Vd gvizxfj v xlviirj vjwfiÃ§rufj,
                Drzj uf hlv gifdvkzr r wfiÃ§r yldrer,
                V vekiv xvekv ivdfkr vuzwztrird
                Efmf ivzef, hlv krekf jlsczdrird.
                .....
                Trekreuf vjgrcyrivz gfi kfur r grikv,
                Jv r krekf dv raluri f vexveyf v rikv
                    """, 0);
        System.out.println(text);
        CaesarCracker ccracker = new CaesarCracker('a');
        System.out.println(ob.encrypt(text, 17));
        System.out.println(ccracker.getKey(text));
        int[] keys = { 17, 14, 12, 4 };
        VigenereCipher vc = new VigenereCipher(keys);
        try {
            Scanner scanner = new Scanner(new File("titus-small.txt"));
            while (scanner.hasNextLine()) {
                System.out.println(vc.encrypt(scanner.nextLine()));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
        VigenereBreaker vb = new VigenereBreaker();
        try {
            vb.breakVigenere();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}