// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
class CaesarCypher{
    public String cypher(String text,int move){
        text=text.toUpperCase();
        int asc=0;
        int new_asc=0;
        String tex="";
        for(char ch:text.toCharArray()){
            if(ch!=' ') {
                asc = (int) ch;
                asc = asc - 64;
                if ((asc + move) > 26) {
                    new_asc = asc + move - 26;
                } else {
                    new_asc = asc + move;
                }
                new_asc += 64;
                char new_ch = (char) new_asc;
                tex+=new_ch;
            }
            else {
                tex+=' ';
            }
        }
        return tex;
    }
}



public class Main {
    public static void main(String[] args) {
        CaesarCypher cypher = new CaesarCypher();
        String tex1 = cypher.cypher("At noon be in the conference room with your hat on for a surprise party yell loud", 8);
        System.out.println("");
        String tex2 = cypher.cypher("At noon be in the conference room with your hat on for a surprise party yell loud", 21);
        String tex3="";
        for(int i=0;i<tex1.length();i++){
            if(i%2==0){
                tex3+=tex1.charAt(i);
            }
            else{
                tex3+=tex2.charAt(i);
            }
        }
        System.out.println(tex1);
        System.out.println(tex2);
        System.out.println(tex3.toLowerCase());
    }
}