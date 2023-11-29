class Guess {
	int x=(int)(Math.random() * 10);
}
class player{
	int x=(int)(Math.random() * 10);
}
public class simulate{
	public static void main(String args[]){
	Guess game=new Guess();
	player player1= new player();
	player player2= new player();
	player player3= new player();
	if(player1.x==game.x){
		System.out.println("player1 won");}
	System.out.println(game.x+" "+player1.x);
	if(player2.x==game.x){
		System.out.println("player1 won");}
	System.out.println(game.x+" "+player2.x);
	if(player3.x==game.x){
		System.out.println("player1 won");}
	System.out.println(game.x+" "+player3.x);
}
}
