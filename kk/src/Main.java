import java.util.ArrayList;
import java.util.List;

class Dna_Parser{
    List<String> dnas=new ArrayList<>();
    String[] ends={"TAG","TGA","TAA"};
    String dna;
    public int findSimple(String dna){
        int index=dna.indexOf("CTG");
        int count=0;
        while(true){
            if(index==-1){
                break;
            }
            count++;
            index=dna.indexOf("CTG",index+3);

        }
        return count;
    }
    public List findDna(String dna){
        int index=0;
        int endindex=-1;
        index = dna.indexOf("ATG");
        while (true) {
            if(index==-1){
                break;
            }
            int min= 0;
            String temp="";
            for (var s : ends) {
                endindex=dna.indexOf(s,index+3);
                if (endindex==-1){
                    continue;
                }
                int k=endindex-index;
                if (k>=0){
                    if(k<min||min==0){
                        temp=dna.substring(index,endindex+3);
                        min=endindex-index;
                    }
                }

            }
            if(temp!=""){
                dnas.add(temp);
                index = dna.indexOf("ATG",min+index+3);
            }
            else{
                index=dna.indexOf("ATG",index+3);
            }

        }
        return dnas;
    }
}
public class Main {
    public static void main(String args[]){
        Dna_Parser dnaParser=new Dna_Parser();
        int count=0;
        int c=0;
        double ratio;
        List<String> dnas = dnaParser.findDna("AACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTCACCCTTCTAACTGGACTCTGACCCTGATTGTTGAGGGCTGCAAAGAGGAAGAATTTTATTTACCGTCGCTGTGGCCCCGAGTTGTCCCAAAGCGAGGTAATGCCCGCAAGGTCTGTGCTGATCAGGACGCAGCTCTGCCTTCGGGGTGCCCCTGGACTGCCCGCCCGCCCGGGTCTGTGCTGAGGAGAACGCTGCTCCGCCTCCGCGGTACTCCGGACATATGTGCAGAGAAGAACGCAGCTGCGCCCTCGCCATGCTCTGCGAGTCTCTGCTGATGAGAACACAGCTTCACTTTCGCAAAGGCGCAGCGCCGGCGCAGGCGCGGAGGGGCGCGCAGCGCCGGCGCAGGCGCGGAGGGGCGCGCCCGAACCCGAACCCTAATGCCGTCATAAGAGCCCTAGGGAGACCTTAGGGAACAAGCATTAAACTGACACTCGATTCTGTAGCCGGCTCTGCCAAGAGACATGGCGTTGCGGTGATATGAGGGCAGGGGTCATGGAAGAAAGCCTTCTGGTTTTAGACCCACAGGAAGATCTGTGACGCGCTCTTGGGTAGAGCACACGTTGCTGGGCGTGCGCTTGAAAAGAGCCTAAGAAGAGGGGGCGTCTGGAAGGAACCGCAACGCCAAGGGAGGGTGTCCAGCCTTCCCGCTTCAACACCTGGACACATTCTGGAAAGTTTCCTAAGAAAGCCAGAAAAATAATTTAAAAAAAAATCCAGAGGCCAGACGGGCTAATGGGGCTTTACTGCGACTATCTGGCTTAATCCTCCAAACAACCTTGCCATACCAGCCCATCAGTCCTCTGAGACAGGTGAAGAACCTGAGGTCGCAGGAGGACACCCAGAAGGTCCAGAGAGAGCCTCCTAGGCCCCCCACCTCCCCCCGTGGCAGCTCCAACCCCAGCTTTTTCACTAGTAAGGCAGTCGGGCCCCTGGGCCACGCCCACTCCCCCAAGCGGGGAAGGAGCTTCGCGCTGCCGCTTGGCTGGGGACTGGGCACCGCCCTCCCGCGGCTCCTGAGCCGGCTGCCACCAGGGGGCGCGCCAGCGGTGTCCGGGAGCCTAGCGGCGCGTGTGCAGCGGCCAGTGCACCTGCTCTGGCCCTCGCCGCGGTCTCTGCCAGGACCCCGACGCCCAGCCTGACCCTGCCATTCAGCGGGGCTGCGGCTCCACGGCCTGCGACAGCAGCCCCACCTGGCATTCAGCGCGCTCCCGGGGGCAGAGGTCGCGGTGTCCTCACGCTGTGGTGCCGGCCTACAACCCCCACGCCGGGCTCGGGCCCGGCGGAGGAGGGCGATGCTCCCCGGGTAGGACAAACCGGTCACCTGGGCTGCGACGGCGGCTTAGGGGCAGAAGCGGCGGTCCAGGGCCGCCTGGCGCAGCAGCCTGTCCCAGCCGCGGTCCCTGCAGTCCCTCCCTGGCGGCTGCGCAGCCGTCCCACGACAGGGGCCATAAACTCTCCAGAGCGGAAAGCCGCACCCTGGTGGCCCGGCCCCGCGCCCAGACCTGGCGGCCGCTGGCACCTGACCCGCTGCATGGGTCTCCAGGGAGCTCGCTGCCCACCCGGCGCTGCAGGCTCGGCTCCCTCGTACACTCTCTGGTAGGTGCTAGGGACGACCCTATGGGCCAGCTTGCCATGCCCAGTCCCCAGGCCGCACCCACCCTGGCTCCCTGGGCTAGGGGACTGGCTCCTCCTGTGAGTCGTGGGTCTGGGAGGCAGGGGCGTTAGGGGAGAGTGAGGGACCGAGGGCAGCCCCTGCTGTGTGCACAGCGAGGTCGTGCACAGGCGTCTGTTGCAGAGCGTGCAGCTTCAGATGAGACTGGATTGCAGGTGGAGATGACTGTGGGTGCGCACACCTGGAGGTGAAGGGGAGGCAGCCTGTCTACCTGACCCATGAAATACAGGAGACTGTACCCCAGAAGCAGCGGGTTCACTGCTCCATTGATTAAGCAAGTCTGGGACACACATGTAGCTAAGCTGTGAGTTCTGTACCAGCGATCCCAACACCCACGCCCTCAGAAAGACACTGGTGTGGGGCCTGGGTGCTTGTCAGGCCTGAAAGTGGAGAGCACGGGCCAGAGACACTGAGTAGGGGGAACCCACCCTAGGGCTCTGAGGGACGACGATGTGGGGAGCTGGTGACAGAGCCTGAGCTGGCCCAATGTTGCACGGTGGGGACAGATTCGAGGTACAGTGGGGACTGGTGACCTCAGTTCCCAGTGTCCCAGCCTGGCCTCCCAGTCCACCCAGCAATTAGTGGGTGCTGCCCTGCAAAGACTCTGGGGGTGCCTCAGCCCTCCTCATCACACGTGACTGGTGACTTCTGTGTCCACCCGCACAATAAGAGGGATCTTCTCTCACTTTCAGGCAAGCCCAAGAAAGTCAGGGGCCTATGTGAGCCAAAGAGGAGAGAAGGTGATGCCTCAGCCCAGTGTTTCTGCCCCACCTCGCTTGTGGCCTTCGGAACTTGATTTGCACCGCAGGAAAATGGGCAATGAAAACCCCTCCCTAACTGGCTTCTCAGTCCACTCTGACCAGCCCACTGCACAGCGCCCACCCTGCAGCTCCAGGTACAGAGGCTGGGATGGCTCTGGGCTGACCTAAGGGCCTTCTGATGGCTCCAACCCTCGGGATGCCTCATGCTCACCCTTTGGCACCCACCTGACAGCTCAGCATCTCTGCTCTCTGCCATCCTCAATGCCTGCTCTAGACAAGCCCAAGTCCCCCAGGAGTGGCAGAGGGAACTGAGCCGAAAACTAAGTCTCGGCTCACTGAACCCCAAGTGGGCTGTCCAGCCTCGCCCTTCAGTTCACAACCCCAGGCAGGTTCCCTCCAGGGATGTGATCCCAGGGGCCACAGCAGCACATTCTGGCCTAACCTATCCACTATTTAAACAGTTACTGAAAAGGCCAGGATGGCCGTGGGCCCTGACATTAATCCCCTTTCTCTGTGAGGGGGCTGGGTTGGGTTTGCCATCCTGATGTCTTTGTGGAAAGAGCTGGCAGGTGAAGCAAGTCTCAGGGGCCAGCCATGGGACAAGGAACCTAGGACTGGCCTCTGCTGGAACCCTCTGAGGCCCCTGCGGACAGGAGGATCCAATGGAGGTCTAGCCACCCCTCCCAGGTTGGTGCTCACAGCCCCTCCCTGGCCCACTCCCTGCACACCTGCACCTGCTGGTCTCTGGGAGAGGAGCATCCATCCATCTTGTGCGCATAGCTTTCGGCTCCATTTTCATGAGGATGGTCTCCTTGGCAGAAATGCCCATTAGGGGATCCTGAGCCTGTGCTAGCTCTTCTCTAAGTGCCAAAGCCAGTGAGAGGGACTTGAAAACTCAAGACTTATTAACAGTATTTTCTGCATTTTGTGCTTTCAGGGTTGTTTTTTCCTTAAAATGTGTAAAAACAAACATTGAGATTTCTATCTTTTATATAATTTGGATTCTGTTATCACACGGACTTTTCCTGAAATTTATTTTTATGTATGTATATCAAACATTGAATTTCTGTTTTCTTCTTTACTGGAATTGTTAACTGTTTTATAGGCCAAATCTTTTAAAAAAAACACATCTCTCTAATTTCTCTAAACATTTCTAATTACATATATATTTACTATACCTAATACACTACTTTGGAATTCCTTGAGGCCTAAATGCATCGGGGTGCTCTGGTTTTGTTGTTGTTATTTCTGAATGACATTTACTTTGGTGCTCTTTATTTTGCGTATTTAAAACTATTAGATCGTGTGATTATATTTGACAGGTCTTAATTGACGCGCTGTTCAGCCCTTTGAGTTCGGTTGAGTTTTGGGTTGGAGAATTTTCTTCCACAAGGGATTGTCTTGGATTTTTCTGTTTCTCCCTCAATATCCACCTGGAAAACATTTCAATTAATTTATATTTACTTAAATATTTCTGTGCAAAAACTGTGTACAAAAGCCCCAAAGCATAATTTGTGCAGTTGAGCGCATGTTCTGTTGTTCAGCATTTATGGTGGTTGGTAGTGGAAAAGATTTTTAGAATATGTGGATTTTCGGGATATTCCCAGAAGCCCAGATAGCGACACTTTACCTTTGGAGGAATTACTTCTCAGAATATTGCACACAATCAATCGCCTTTGGAAGGAGCATATATCCCCAGCAAAAGCTCTGGTTTTTTGAAGTCTGTATTGTGTGTTACTTCCAGGAGAATATGCAATGATGACAATGTTATTAGATGATTCAAATATGAAGTGCTGTTATGCCAAACAATGAATCTTTGTGTTATACATTATGCCTAACTATAAATCTTTGTGTTATACATTTTAATGTCATTGGAGAGTACTCCTGTCTTCTTGGCATTATTGATAATTAGATTCTAATTGCTAATAAGTCAGAAAAATTAGGAACACCAAATTTCAGTTGTCTCAAAAGCACTCCTCTTATTAAATTTGGATGTTTACCTTTATCACATCAAAAGAAATATTGTTAGAAAGGTGTTTAATGTTTTGCAGATGGATAGATTACTGTTATTAGTTCTCATTTCATTGTTAATTTTTAAAACCATAAGGTTGGAAGTATCAATATGCCTTTCAATATACCTTAGTGGAATTTATTAAATTTTCATGGATGTCCTTTAGGGGGTTCAGGAAGTTATTTCTATTGCTAGATTTCTGGAAGATTTATCAGGAATGAGTGTCAGACATTGTCAGACGTCCATTGAAATCATCATGGTCTTTTCCTTTATTCTATTAATATGATGTATTACACTGATTGATTTTTAAATTTGTATTGGTAGGATAATTCCACTTGGTTATATTGTCTAACTTTTTTCTAATTTTCTTTCATTTTTATTACAGATGAGGCCTCACTCTGTCACCCAGGTTGGGGTGGAGTGGCACAGTCACAGCTCACTATAACCTCAAGCTCCTGGGCTCAAGTGATCCTGCCACCTCAGCCTCCTAAGTAGCTGGAACTACAGATGTGCACTGCCATGCCAGGCTTGTCTAACATTTTTATGTGTTGCTTCATCCAGTTTGCTAGAGTTTTTGGAGATTTCTGTCTTCATTCATGAGGGATAATAGTCTGCACTTTTATTTTCTTGTGATACTTTTGTCTGATTTGTTATCTGGGTAATACTGGCCTTGAAAATGAATTGATGTTTTCCTGCTTCTCTGCTTTGCAAGTGTTTGTGAAGGATTGGTTATTCATTAAGTGTTTAATAGAATTCACTAGTGAAGCTATGTGAGCCAGGGCTAGACTGATGAAGAGTTTTCATTAGTCTAATCTGTTTACTTGCTGTATAAGTACGCATATATTCTCTTTCTTCTTGATTTAATTTTACACTTTGTGTATAGCAGGGAATCTGTGTCTAATTTGTAGTATTTCATGCTTCTAGGTTTTCATGGCAGTTGAGATGTAAGAATAACAATAATGTTGGGAGAAGGAAGTTGTGGACAATCCATGAATATCCCAACATCTGTTGTAGGAAGGTTAAGATTACTTTTTTTTTTTTTGCTGTACTGAACTGAATACTCTTATTTATAATGTCAGACAAATGTAATGTTGTATATAAATAGAACTAGGAAAATGTGCCATTTGTCTTAGTATTTAATCAAGATGGAAGTCTGGGCCTACCTCCTCTCTTTTATTAATATGTAGACAGGACACCAACACAAATTAGAATGAAGACAAACAAAATGTTAGCAAATGAAGAATGGTATCAATTGGTTAAAATGTGATGAAATAGAGTGGTGAATATTTACATAGAATCCATGATGTGTTAGGTGCTATTTCAAGCTATTTGCACATATAGTTTTAATACCAATGACGTTAAAATGTATAACACAAAGATTCATATAAATAAAAATTACAACATTGTAAATAATATTAGGTGACACTAAAACTGTCATAGAAATACACATTTATATAAAACATAAAGTAACATGAAGTATTAAATTTTAGAAACTTTGATTACTAATCAGATGAACAACTGATTAGCCTTTTTATCCAGTAAAAAAGGCATACATATTATTTTCAAATTCCAGAGACAAATATTTTAAATATTGAAGTTGAAGACCTAAAAATGTGTCACTGACCTCATGGAAGTAGATATTCACTAGGTGATATTTTCTAGGCTCTCTGAAATTATATCAGAAAAATGTGAATTAGAATATAACCCATAAATAATATCTGGCCACATACAAAGTAATTGAAGATCAATTTAAATGGCTATTGGATTAAGAAATAGGGACTGAGGTAAATTTGCAGTGTCAGGGAGGATCTAAGGAGGAAGCATTGACACTGGAGCCCAAGGACCTGGGATCACAGAACAGATTCTACCAGTGCTAACTTACTGCTCCACAGAAAACATCAATTCTGCTCATGCGCAGGTACAATTCATCAAGAAAGGAATTACAACTTCAGAAATGTGTTCAAAATATATCCATACTTTGACATATTAATGAAGTAATCACATTCTACACATAACTACTCCATATGGAATACTGGGGAGGAGGTGTTCCAAATAAAGAGACTGAGGATTTCTCATGAGAACTCAGTGTCTGCTAGAAAATATCTAAGTAAAATATTTTACTTATGTGGAAAGTGTGGATGTTTGTGCATCAAAAGTTTCAAGAATCCCTAAAATTTACAATGGAGATGAGGAGAAAATATCAGAATTTCCCAGCACCAGAAATAAGGCAAGAAAAAATTCAGAGGGGTTGTAAATGTGAAAAGCCAATGGCTGGTCACACAGCAACATTGATAACCTTGTGCCTGGACAACTAGAATAAATACATAAACATACACATTGAAAATATTTCCAATATTAGATCTCCCTCATGTGAGAACTAAATTATAAAGATTGAAGCATAGAAGAAAATAAGCTACCAGAATAAATTTGATTACACATAAATTTCTGATATTGAAACTGTCACAAATGTTTAAGTTGGTAGTGGAAGACAAAGGACATATAATCTTGGGAGTCCTAAGGCCCTGCCCACTGCCAGTCCCTCCACACTACTACAGCTGATGCTTTCTGGAAATCACCACCTCCTGGCAGGAGCCCAACCAGCACAAATATAGAGCATTAAACCACCAAAGCTAAGGAGGCTCACAGAGTCTATTGCACCCTTCACCACCTCCACTGGAACAGGCGCTGGTATCCATGGCTCAGAGACCCAAAGATGGTTCACATCACAGGGCTCTATGCAGACAACCCCCAGTACCAGCCCAAAGCCACGTAGACCTGCTGGGTGGCTAGACCCAGAAGAGAGACAACAATCAATGCACTTTGGCTTACAGGAAGCCATGCCCATAGGAAAAAGGGGAGAGTACTACGTCAAGGGAACACCCCGTGGGATGAAAGAGTCTGAACAACAGTCTTCAGCCCTAGACCTTTCCTCTGACAGAGTCTACCAAAATGAGAAGGAACCAGAAAACCAACCCTGGTAATCTGACAAAACAAGAATCTTCAACACCCCCCAAAAAATCACACCAGTTCATCACCAATGGATCCAAACAAAGAAGAAATCACTGATTCATCTAAAAAAAAATTCAGGTTAGTTATTAAGCTAATCAGGGAGGGGCCAGAGAAAGATGAAGCCCAATGCAAGAAAATCCAAAAAATGATACAATACGTGAAGGGAGAATTATTCAAGGAAATAGATAGCTTAAATAAAAAAATAAAAAATCAGGAAACTTTGGACGTACTTTTAGAAATGTGAAATGCTCTGGAAAGTCTCAGCAATAGAATTGAACAAGTAGAAGAAAGAAATTCAGAATTCGAAGACAAGGTCTTTGATTTAACCCAATCCAATAAAGACAAAGAAAAAAGAATAAGAAAATATGAGCAAAGTCTCCAAGGAGTCTGGCATTCTGTTAAATGATGAAACCTAACACTAATTGGTGTACCTGAGGAAGAAGTGAATTCTAAAAGCCAGGAAAACATATTTGGGAGAATAATCTAGGAAAACTTCCATGGCCTTGTGAGAGACCTAGACATCCAAATACAAGAACCACAAATAACACCTGGGAAATTCATCACAAAAAGATCTTAGCCTAGGCACATTGTCATTAGGTTATCCAAAGTTAAGACAAAGGAAAGAATCTTAAGAGCTGTGAGACAGAAGCACTAGGTAACCTATAAAGGAAAACCTGTCAAATTAACAGCAGATTTCACAGCAGGAACCTTACAAGCTAGATGGGATTGGGGCCCTTTCTTCAGCCTCCTCAAACAAAACAATTATCAGCCAAGAATTTTGTATCCAGCAAAACTAAACATCATATATGAAGGAAAGATACAGTCATTTTCAGACAAACAAATGCTGACAGAATTTGCCATTACCAAGCCAGGACTCTAAGAACTGCTAAAAGGAGCTCTAAATCATGAAACAAATCCTGGAAACACATCAAAACAGAACTTCATTAACGCATAAATCACACAGGACCTATAAAACAAAAATACAAGTTAAAAAACAAAAACAAAGTACAGAGGCAACAAAGAGCATGATGAAAGCAATGGTACCTCACTTTTTAATACTAATGTTGGTTGTAAATGGCTTAAATGCTCCACTTACAAGATACAGAACCACAGAATGGATAACAACTCACCAACTAACTATCTGCTGCCTTCAGGAGACTCACCTAACACATAACGACTTACATAAACTTAAGGAAAGTGGTAGAAAAAGGCATTTCATGCAAATGGACACCAAAAGCAAGCAGCAGTAACTATTCTCATATGAGACAAAACAAACTTTAAAGCAACAGTAGCTAAAAGAGACAAAGAGAGACAGTATATCATCTGTCACCTGACAGTCTCATCCAACAGAAAAATATGACAATCCTAAACATATGTGAACCTAACACTGGAGCTCCCAAATTTATAAAACAATTACTAGTAGACATAAGAAATAAGATAGACAGCAACACAATAATAGTGGGGGACTTCAATACTCCACTGACAGCACTAGACAGGTCATCAAGACAGAAAGTCAACAAAGAAACAATGGATTTAAACTATACTTTGGAACAAATGGACTTAACAGATATATATAGAACATTTCATCCAACAACCACAGAATACACATTCTATTCAACAGCACATGGAATTTTCTCCAAGATAGACCATATGATAGGCCATAAAATGAGTCTCAATAAATTTAAGAAAATTGAAATTGTATCACGCACTCTCTCACATCACAATGGAATAAAACTGAAAATCAACTCCAAAAGGAATCTTCGAAACCATGCAAATACATGGAAATTAAATAACCTGCTCCTGAATGAGCATTGGGTGAAAAACGAAATCAAGATGGAAATGTAAAAAATTTCTTCGAACTGGATGACACAACCTATCAAGACCTCTGGGATACAGCAAAGGCAGTGCTAAGAGGAAAGTTTATAGCACTAAACACCTACGTCGAAAAGTCTGAAAGAGCACAGACAATCTAAGTTCACATCTCAGGGAACTAGAGAAGGAGGAACAAGCCAAACCCAATCCCAGCAAACAAAGGAAATAACCAAGATCAGAGCAGAACTAAATGAAATTGACACAACAACAACAACAACAAAAATACAAAACATAAATAAAACAAAAATTTGGTTATTTGAAAAGATA");
        int t= dnaParser.findSimple("AACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTCACCCTTCTAACTGGACTCTGACCCTGATTGTTGAGGGCTGCAAAGAGGAAGAATTTTATTTACCGTCGCTGTGGCCCCGAGTTGTCCCAAAGCGAGGTAATGCCCGCAAGGTCTGTGCTGATCAGGACGCAGCTCTGCCTTCGGGGTGCCCCTGGACTGCCCGCCCGCCCGGGTCTGTGCTGAGGAGAACGCTGCTCCGCCTCCGCGGTACTCCGGACATATGTGCAGAGAAGAACGCAGCTGCGCCCTCGCCATGCTCTGCGAGTCTCTGCTGATGAGAACACAGCTTCACTTTCGCAAAGGCGCAGCGCCGGCGCAGGCGCGGAGGGGCGCGCAGCGCCGGCGCAGGCGCGGAGGGGCGCGCCCGAACCCGAACCCTAATGCCGTCATAAGAGCCCTAGGGAGACCTTAGGGAACAAGCATTAAACTGACACTCGATTCTGTAGCCGGCTCTGCCAAGAGACATGGCGTTGCGGTGATATGAGGGCAGGGGTCATGGAAGAAAGCCTTCTGGTTTTAGACCCACAGGAAGATCTGTGACGCGCTCTTGGGTAGAGCACACGTTGCTGGGCGTGCGCTTGAAAAGAGCCTAAGAAGAGGGGGCGTCTGGAAGGAACCGCAACGCCAAGGGAGGGTGTCCAGCCTTCCCGCTTCAACACCTGGACACATTCTGGAAAGTTTCCTAAGAAAGCCAGAAAAATAATTTAAAAAAAAATCCAGAGGCCAGACGGGCTAATGGGGCTTTACTGCGACTATCTGGCTTAATCCTCCAAACAACCTTGCCATACCAGCCCATCAGTCCTCTGAGACAGGTGAAGAACCTGAGGTCGCAGGAGGACACCCAGAAGGTCCAGAGAGAGCCTCCTAGGCCCCCCACCTCCCCCCGTGGCAGCTCCAACCCCAGCTTTTTCACTAGTAAGGCAGTCGGGCCCCTGGGCCACGCCCACTCCCCCAAGCGGGGAAGGAGCTTCGCGCTGCCGCTTGGCTGGGGACTGGGCACCGCCCTCCCGCGGCTCCTGAGCCGGCTGCCACCAGGGGGCGCGCCAGCGGTGTCCGGGAGCCTAGCGGCGCGTGTGCAGCGGCCAGTGCACCTGCTCTGGCCCTCGCCGCGGTCTCTGCCAGGACCCCGACGCCCAGCCTGACCCTGCCATTCAGCGGGGCTGCGGCTCCACGGCCTGCGACAGCAGCCCCACCTGGCATTCAGCGCGCTCCCGGGGGCAGAGGTCGCGGTGTCCTCACGCTGTGGTGCCGGCCTACAACCCCCACGCCGGGCTCGGGCCCGGCGGAGGAGGGCGATGCTCCCCGGGTAGGACAAACCGGTCACCTGGGCTGCGACGGCGGCTTAGGGGCAGAAGCGGCGGTCCAGGGCCGCCTGGCGCAGCAGCCTGTCCCAGCCGCGGTCCCTGCAGTCCCTCCCTGGCGGCTGCGCAGCCGTCCCACGACAGGGGCCATAAACTCTCCAGAGCGGAAAGCCGCACCCTGGTGGCCCGGCCCCGCGCCCAGACCTGGCGGCCGCTGGCACCTGACCCGCTGCATGGGTCTCCAGGGAGCTCGCTGCCCACCCGGCGCTGCAGGCTCGGCTCCCTCGTACACTCTCTGGTAGGTGCTAGGGACGACCCTATGGGCCAGCTTGCCATGCCCAGTCCCCAGGCCGCACCCACCCTGGCTCCCTGGGCTAGGGGACTGGCTCCTCCTGTGAGTCGTGGGTCTGGGAGGCAGGGGCGTTAGGGGAGAGTGAGGGACCGAGGGCAGCCCCTGCTGTGTGCACAGCGAGGTCGTGCACAGGCGTCTGTTGCAGAGCGTGCAGCTTCAGATGAGACTGGATTGCAGGTGGAGATGACTGTGGGTGCGCACACCTGGAGGTGAAGGGGAGGCAGCCTGTCTACCTGACCCATGAAATACAGGAGACTGTACCCCAGAAGCAGCGGGTTCACTGCTCCATTGATTAAGCAAGTCTGGGACACACATGTAGCTAAGCTGTGAGTTCTGTACCAGCGATCCCAACACCCACGCCCTCAGAAAGACACTGGTGTGGGGCCTGGGTGCTTGTCAGGCCTGAAAGTGGAGAGCACGGGCCAGAGACACTGAGTAGGGGGAACCCACCCTAGGGCTCTGAGGGACGACGATGTGGGGAGCTGGTGACAGAGCCTGAGCTGGCCCAATGTTGCACGGTGGGGACAGATTCGAGGTACAGTGGGGACTGGTGACCTCAGTTCCCAGTGTCCCAGCCTGGCCTCCCAGTCCACCCAGCAATTAGTGGGTGCTGCCCTGCAAAGACTCTGGGGGTGCCTCAGCCCTCCTCATCACACGTGACTGGTGACTTCTGTGTCCACCCGCACAATAAGAGGGATCTTCTCTCACTTTCAGGCAAGCCCAAGAAAGTCAGGGGCCTATGTGAGCCAAAGAGGAGAGAAGGTGATGCCTCAGCCCAGTGTTTCTGCCCCACCTCGCTTGTGGCCTTCGGAACTTGATTTGCACCGCAGGAAAATGGGCAATGAAAACCCCTCCCTAACTGGCTTCTCAGTCCACTCTGACCAGCCCACTGCACAGCGCCCACCCTGCAGCTCCAGGTACAGAGGCTGGGATGGCTCTGGGCTGACCTAAGGGCCTTCTGATGGCTCCAACCCTCGGGATGCCTCATGCTCACCCTTTGGCACCCACCTGACAGCTCAGCATCTCTGCTCTCTGCCATCCTCAATGCCTGCTCTAGACAAGCCCAAGTCCCCCAGGAGTGGCAGAGGGAACTGAGCCGAAAACTAAGTCTCGGCTCACTGAACCCCAAGTGGGCTGTCCAGCCTCGCCCTTCAGTTCACAACCCCAGGCAGGTTCCCTCCAGGGATGTGATCCCAGGGGCCACAGCAGCACATTCTGGCCTAACCTATCCACTATTTAAACAGTTACTGAAAAGGCCAGGATGGCCGTGGGCCCTGACATTAATCCCCTTTCTCTGTGAGGGGGCTGGGTTGGGTTTGCCATCCTGATGTCTTTGTGGAAAGAGCTGGCAGGTGAAGCAAGTCTCAGGGGCCAGCCATGGGACAAGGAACCTAGGACTGGCCTCTGCTGGAACCCTCTGAGGCCCCTGCGGACAGGAGGATCCAATGGAGGTCTAGCCACCCCTCCCAGGTTGGTGCTCACAGCCCCTCCCTGGCCCACTCCCTGCACACCTGCACCTGCTGGTCTCTGGGAGAGGAGCATCCATCCATCTTGTGCGCATAGCTTTCGGCTCCATTTTCATGAGGATGGTCTCCTTGGCAGAAATGCCCATTAGGGGATCCTGAGCCTGTGCTAGCTCTTCTCTAAGTGCCAAAGCCAGTGAGAGGGACTTGAAAACTCAAGACTTATTAACAGTATTTTCTGCATTTTGTGCTTTCAGGGTTGTTTTTTCCTTAAAATGTGTAAAAACAAACATTGAGATTTCTATCTTTTATATAATTTGGATTCTGTTATCACACGGACTTTTCCTGAAATTTATTTTTATGTATGTATATCAAACATTGAATTTCTGTTTTCTTCTTTACTGGAATTGTTAACTGTTTTATAGGCCAAATCTTTTAAAAAAAACACATCTCTCTAATTTCTCTAAACATTTCTAATTACATATATATTTACTATACCTAATACACTACTTTGGAATTCCTTGAGGCCTAAATGCATCGGGGTGCTCTGGTTTTGTTGTTGTTATTTCTGAATGACATTTACTTTGGTGCTCTTTATTTTGCGTATTTAAAACTATTAGATCGTGTGATTATATTTGACAGGTCTTAATTGACGCGCTGTTCAGCCCTTTGAGTTCGGTTGAGTTTTGGGTTGGAGAATTTTCTTCCACAAGGGATTGTCTTGGATTTTTCTGTTTCTCCCTCAATATCCACCTGGAAAACATTTCAATTAATTTATATTTACTTAAATATTTCTGTGCAAAAACTGTGTACAAAAGCCCCAAAGCATAATTTGTGCAGTTGAGCGCATGTTCTGTTGTTCAGCATTTATGGTGGTTGGTAGTGGAAAAGATTTTTAGAATATGTGGATTTTCGGGATATTCCCAGAAGCCCAGATAGCGACACTTTACCTTTGGAGGAATTACTTCTCAGAATATTGCACACAATCAATCGCCTTTGGAAGGAGCATATATCCCCAGCAAAAGCTCTGGTTTTTTGAAGTCTGTATTGTGTGTTACTTCCAGGAGAATATGCAATGATGACAATGTTATTAGATGATTCAAATATGAAGTGCTGTTATGCCAAACAATGAATCTTTGTGTTATACATTATGCCTAACTATAAATCTTTGTGTTATACATTTTAATGTCATTGGAGAGTACTCCTGTCTTCTTGGCATTATTGATAATTAGATTCTAATTGCTAATAAGTCAGAAAAATTAGGAACACCAAATTTCAGTTGTCTCAAAAGCACTCCTCTTATTAAATTTGGATGTTTACCTTTATCACATCAAAAGAAATATTGTTAGAAAGGTGTTTAATGTTTTGCAGATGGATAGATTACTGTTATTAGTTCTCATTTCATTGTTAATTTTTAAAACCATAAGGTTGGAAGTATCAATATGCCTTTCAATATACCTTAGTGGAATTTATTAAATTTTCATGGATGTCCTTTAGGGGGTTCAGGAAGTTATTTCTATTGCTAGATTTCTGGAAGATTTATCAGGAATGAGTGTCAGACATTGTCAGACGTCCATTGAAATCATCATGGTCTTTTCCTTTATTCTATTAATATGATGTATTACACTGATTGATTTTTAAATTTGTATTGGTAGGATAATTCCACTTGGTTATATTGTCTAACTTTTTTCTAATTTTCTTTCATTTTTATTACAGATGAGGCCTCACTCTGTCACCCAGGTTGGGGTGGAGTGGCACAGTCACAGCTCACTATAACCTCAAGCTCCTGGGCTCAAGTGATCCTGCCACCTCAGCCTCCTAAGTAGCTGGAACTACAGATGTGCACTGCCATGCCAGGCTTGTCTAACATTTTTATGTGTTGCTTCATCCAGTTTGCTAGAGTTTTTGGAGATTTCTGTCTTCATTCATGAGGGATAATAGTCTGCACTTTTATTTTCTTGTGATACTTTTGTCTGATTTGTTATCTGGGTAATACTGGCCTTGAAAATGAATTGATGTTTTCCTGCTTCTCTGCTTTGCAAGTGTTTGTGAAGGATTGGTTATTCATTAAGTGTTTAATAGAATTCACTAGTGAAGCTATGTGAGCCAGGGCTAGACTGATGAAGAGTTTTCATTAGTCTAATCTGTTTACTTGCTGTATAAGTACGCATATATTCTCTTTCTTCTTGATTTAATTTTACACTTTGTGTATAGCAGGGAATCTGTGTCTAATTTGTAGTATTTCATGCTTCTAGGTTTTCATGGCAGTTGAGATGTAAGAATAACAATAATGTTGGGAGAAGGAAGTTGTGGACAATCCATGAATATCCCAACATCTGTTGTAGGAAGGTTAAGATTACTTTTTTTTTTTTTGCTGTACTGAACTGAATACTCTTATTTATAATGTCAGACAAATGTAATGTTGTATATAAATAGAACTAGGAAAATGTGCCATTTGTCTTAGTATTTAATCAAGATGGAAGTCTGGGCCTACCTCCTCTCTTTTATTAATATGTAGACAGGACACCAACACAAATTAGAATGAAGACAAACAAAATGTTAGCAAATGAAGAATGGTATCAATTGGTTAAAATGTGATGAAATAGAGTGGTGAATATTTACATAGAATCCATGATGTGTTAGGTGCTATTTCAAGCTATTTGCACATATAGTTTTAATACCAATGACGTTAAAATGTATAACACAAAGATTCATATAAATAAAAATTACAACATTGTAAATAATATTAGGTGACACTAAAACTGTCATAGAAATACACATTTATATAAAACATAAAGTAACATGAAGTATTAAATTTTAGAAACTTTGATTACTAATCAGATGAACAACTGATTAGCCTTTTTATCCAGTAAAAAAGGCATACATATTATTTTCAAATTCCAGAGACAAATATTTTAAATATTGAAGTTGAAGACCTAAAAATGTGTCACTGACCTCATGGAAGTAGATATTCACTAGGTGATATTTTCTAGGCTCTCTGAAATTATATCAGAAAAATGTGAATTAGAATATAACCCATAAATAATATCTGGCCACATACAAAGTAATTGAAGATCAATTTAAATGGCTATTGGATTAAGAAATAGGGACTGAGGTAAATTTGCAGTGTCAGGGAGGATCTAAGGAGGAAGCATTGACACTGGAGCCCAAGGACCTGGGATCACAGAACAGATTCTACCAGTGCTAACTTACTGCTCCACAGAAAACATCAATTCTGCTCATGCGCAGGTACAATTCATCAAGAAAGGAATTACAACTTCAGAAATGTGTTCAAAATATATCCATACTTTGACATATTAATGAAGTAATCACATTCTACACATAACTACTCCATATGGAATACTGGGGAGGAGGTGTTCCAAATAAAGAGACTGAGGATTTCTCATGAGAACTCAGTGTCTGCTAGAAAATATCTAAGTAAAATATTTTACTTATGTGGAAAGTGTGGATGTTTGTGCATCAAAAGTTTCAAGAATCCCTAAAATTTACAATGGAGATGAGGAGAAAATATCAGAATTTCCCAGCACCAGAAATAAGGCAAGAAAAAATTCAGAGGGGTTGTAAATGTGAAAAGCCAATGGCTGGTCACACAGCAACATTGATAACCTTGTGCCTGGACAACTAGAATAAATACATAAACATACACATTGAAAATATTTCCAATATTAGATCTCCCTCATGTGAGAACTAAATTATAAAGATTGAAGCATAGAAGAAAATAAGCTACCAGAATAAATTTGATTACACATAAATTTCTGATATTGAAACTGTCACAAATGTTTAAGTTGGTAGTGGAAGACAAAGGACATATAATCTTGGGAGTCCTAAGGCCCTGCCCACTGCCAGTCCCTCCACACTACTACAGCTGATGCTTTCTGGAAATCACCACCTCCTGGCAGGAGCCCAACCAGCACAAATATAGAGCATTAAACCACCAAAGCTAAGGAGGCTCACAGAGTCTATTGCACCCTTCACCACCTCCACTGGAACAGGCGCTGGTATCCATGGCTCAGAGACCCAAAGATGGTTCACATCACAGGGCTCTATGCAGACAACCCCCAGTACCAGCCCAAAGCCACGTAGACCTGCTGGGTGGCTAGACCCAGAAGAGAGACAACAATCAATGCACTTTGGCTTACAGGAAGCCATGCCCATAGGAAAAAGGGGAGAGTACTACGTCAAGGGAACACCCCGTGGGATGAAAGAGTCTGAACAACAGTCTTCAGCCCTAGACCTTTCCTCTGACAGAGTCTACCAAAATGAGAAGGAACCAGAAAACCAACCCTGGTAATCTGACAAAACAAGAATCTTCAACACCCCCCAAAAAATCACACCAGTTCATCACCAATGGATCCAAACAAAGAAGAAATCACTGATTCATCTAAAAAAAAATTCAGGTTAGTTATTAAGCTAATCAGGGAGGGGCCAGAGAAAGATGAAGCCCAATGCAAGAAAATCCAAAAAATGATACAATACGTGAAGGGAGAATTATTCAAGGAAATAGATAGCTTAAATAAAAAAATAAAAAATCAGGAAACTTTGGACGTACTTTTAGAAATGTGAAATGCTCTGGAAAGTCTCAGCAATAGAATTGAACAAGTAGAAGAAAGAAATTCAGAATTCGAAGACAAGGTCTTTGATTTAACCCAATCCAATAAAGACAAAGAAAAAAGAATAAGAAAATATGAGCAAAGTCTCCAAGGAGTCTGGCATTCTGTTAAATGATGAAACCTAACACTAATTGGTGTACCTGAGGAAGAAGTGAATTCTAAAAGCCAGGAAAACATATTTGGGAGAATAATCTAGGAAAACTTCCATGGCCTTGTGAGAGACCTAGACATCCAAATACAAGAACCACAAATAACACCTGGGAAATTCATCACAAAAAGATCTTAGCCTAGGCACATTGTCATTAGGTTATCCAAAGTTAAGACAAAGGAAAGAATCTTAAGAGCTGTGAGACAGAAGCACTAGGTAACCTATAAAGGAAAACCTGTCAAATTAACAGCAGATTTCACAGCAGGAACCTTACAAGCTAGATGGGATTGGGGCCCTTTCTTCAGCCTCCTCAAACAAAACAATTATCAGCCAAGAATTTTGTATCCAGCAAAACTAAACATCATATATGAAGGAAAGATACAGTCATTTTCAGACAAACAAATGCTGACAGAATTTGCCATTACCAAGCCAGGACTCTAAGAACTGCTAAAAGGAGCTCTAAATCATGAAACAAATCCTGGAAACACATCAAAACAGAACTTCATTAACGCATAAATCACACAGGACCTATAAAACAAAAATACAAGTTAAAAAACAAAAACAAAGTACAGAGGCAACAAAGAGCATGATGAAAGCAATGGTACCTCACTTTTTAATACTAATGTTGGTTGTAAATGGCTTAAATGCTCCACTTACAAGATACAGAACCACAGAATGGATAACAACTCACCAACTAACTATCTGCTGCCTTCAGGAGACTCACCTAACACATAACGACTTACATAAACTTAAGGAAAGTGGTAGAAAAAGGCATTTCATGCAAATGGACACCAAAAGCAAGCAGCAGTAACTATTCTCATATGAGACAAAACAAACTTTAAAGCAACAGTAGCTAAAAGAGACAAAGAGAGACAGTATATCATCTGTCACCTGACAGTCTCATCCAACAGAAAAATATGACAATCCTAAACATATGTGAACCTAACACTGGAGCTCCCAAATTTATAAAACAATTACTAGTAGACATAAGAAATAAGATAGACAGCAACACAATAATAGTGGGGGACTTCAATACTCCACTGACAGCACTAGACAGGTCATCAAGACAGAAAGTCAACAAAGAAACAATGGATTTAAACTATACTTTGGAACAAATGGACTTAACAGATATATATAGAACATTTCATCCAACAACCACAGAATACACATTCTATTCAACAGCACATGGAATTTTCTCCAAGATAGACCATATGATAGGCCATAAAATGAGTCTCAATAAATTTAAGAAAATTGAAATTGTATCACGCACTCTCTCACATCACAATGGAATAAAACTGAAAATCAACTCCAAAAGGAATCTTCGAAACCATGCAAATACATGGAAATTAAATAACCTGCTCCTGAATGAGCATTGGGTGAAAAACGAAATCAAGATGGAAATGTAAAAAATTTCTTCGAACTGGATGACACAACCTATCAAGACCTCTGGGATACAGCAAAGGCAGTGCTAAGAGGAAAGTTTATAGCACTAAACACCTACGTCGAAAAGTCTGAAAGAGCACAGACAATCTAAGTTCACATCTCAGGGAACTAGAGAAGGAGGAACAAGCCAAACCCAATCCCAGCAAACAAAGGAAATAACCAAGATCAGAGCAGAACTAAATGAAATTGACACAACAACAACAACAACAAAAATACAAAACATAAATAAAACAAAAATTTGGTTATTTGAAAAGATA");
        for(String dna:dnas) {
            if(dna.length()>60)  {
                count++;
            }
        }
        System.out.println(dnas.size());
        System.out.println(count);
        System.out.println(t);
    }
}