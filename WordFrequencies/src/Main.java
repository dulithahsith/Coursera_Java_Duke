import java.io.*;
import java.util.HashMap;
import java.util.Map;

class WordFrequencies{
    public void WordFreq(File file) throws IOException {
        Map<String,Integer> map = new HashMap();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while(true){
            String line = reader.readLine();
            if(line==null){
                break;
            }
            line=line.trim();
            String[] words = line.split("\\s+");
            for (String word:words){
                String temp= word.toLowerCase().trim();
                if(temp.equals("")){
                    continue;
                }
                System.out.println(temp);
                if(temp.length()==1){
                    if(!map.containsKey(temp)){
                        map.put(temp,1);
                    }
                    else{
                        map.put(temp,map.get(temp)+1);
                    }
                    continue;
                }
                while(!Character.isAlphabetic(temp.charAt(0))){
                    temp=temp.substring(1);
                }
                while(!Character.isAlphabetic(temp.charAt(temp.length()-1))){
                    temp=temp.substring(0,temp.length()-1);
                }
                if(!map.containsKey(temp)){
                    map.put(temp,1);
                }
                else{
                    map.put(temp,map.get(temp)+1);
                }
            }
        }
        int max=0;
        String smax="";
        int kt=0;
        for(String key: map.keySet()){
            kt++;
            if(map.get(key)>max){
                max=map.get(key);
                smax=key;
            }
        }
        System.out.println(kt);
        System.out.println(max);
        System.out.println(smax);
        System.out.println(map.size());
        System.out.println(map.get("the"));
    }
}
class CharacterNames{
    public void MostPlayed(File file) throws IOException {
        Map<String,Integer> map = new HashMap();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while(true){
            String line = reader.readLine();
            if(line==null){
                break;
            }
            line=line.trim();
            if(line.indexOf(".")==-1){
                continue;
            }
            String temp = line.substring(0,line.indexOf("."));
            if(!map.containsKey(temp)){
                map.put(temp,1);
            }
            else{
                map.put(temp,map.get(temp)+1);
            }
        }
        int max=0;
        String smax="";
        for(String key: map.keySet()){
            if(map.get(key)>max){
                max=map.get(key);
                smax=key;
            }
            if(map.get(key)>1){
                System.out.println(key);
            }
        }
        System.out.println(max);
        System.out.println(smax);
    }
}
public class Main{
    public static void main(String args[]) throws IOException {
        WordFrequencies wf = new WordFrequencies();
        wf.WordFreq(new File("romeo.txt"));
        CharacterNames cn = new CharacterNames();
        //cn.MostPlayed(new File("romeo.txt"));
    }
}