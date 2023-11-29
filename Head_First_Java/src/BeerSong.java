
public class BeerSong {
	public static void main(String[] args){
		int num=99;
		String word="bottles";
		while(num>0){
			if(num==1){word="bottle";}
			System.out.println(num+" "+word+" "+"of beer on the wall");
			System.out.println(num+" "+word+" "+"of beer");
			System.out.println("Take one down");
			System.out.println("Pass it around");
			num-=1;
		}
	}
}
 