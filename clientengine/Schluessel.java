package clientengine;

public class Schluessel extends Spielelement{
	
	//Ein Key wird ben�tigt, damit man das n�chste Level erreichen kann
	//Er liegt an einer Stelle x,y im Level
	public Schluessel (int x, int y){
		this.posx=x;
		this.posy=y;
		this.aufgenommen= false;
	}
	
}
