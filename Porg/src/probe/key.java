package probe;

public class key extends Spielelement{
	
	//Ein Key wird ben�tigt, damit man das n�chste Level erreichen kann
	//Er liegt an einer Stelle x,y im Level
	public key (int x, int y){
		this.posx=x;
		this.posy=y;
		this.aufgenommen= false;
	}
	
}
