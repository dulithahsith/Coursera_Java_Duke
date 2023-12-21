import java.util.HashMap;

class codon_c {
    private HashMap<String, Integer> codons;

    public codon_c() {
        codons = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna) {
        codons.clear();
        int temp = start;
        while ((temp + 3) <= (dna.length() - start)) {
            String codon = dna.substring(temp, temp + 3);
            if (!codons.containsKey(codon)) {
                codons.put(codon, 1);
            } else {
                codons.put(codon, codons.get(codon) + 1);
            }
            ;
            temp += 3;
        }
    }

    public String getMostCommonCodon() {
        int max = 0;
        String temp = "";
        for (String codon : codons.keySet()) {
            if (max < codons.get(codon)) {
                max = codons.get(codon);
                temp = codon;
            }
        }

        return temp;
    }

    public void printCodonCounts(int start, int end) {
        for (String codon : codons.keySet()) {
            if ((start < codons.get(codon)) & (codons.get(codon) < end)) {
                System.out.println(codon + " : " + codons.get(codon));
            }
        }
    }
}

public class Codon_count {
    public static void main(String args[]) {
        codon_c tester = new codon_c();
        tester.buildCodonMap(0, "CGTTCAAGTTCAAAA");
        System.out.println(tester.getMostCommonCodon());
        tester.printCodonCounts(0, 4);
    }
}