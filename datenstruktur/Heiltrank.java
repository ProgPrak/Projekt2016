package datenstruktur;

//Heiltraenke sind vom Typ Spielelement und besitzen daher ihre Eigenschaften und Attribute
public class Heiltrank extends Spielelement{
	
	private int wirkung;
	
	//Bei der Initialisierung wird dem Heiltrank eine Wirkung und eine Position zugewiesen
	public Heiltrank(int wirkung,int x,int y){
		this.wirkung = wirkung;
		this.posx=x;
		this.posy=y;
	}
	
	//Getter für die Wirkung des Tranks
	public int getWirkung(){
		return wirkung;
	}
	
	//X-Koordinate des Tranks
	public int getPosX(){
		return this.posx;
	}
	
	//Y-Koordinate des Tranks
	public int getPosY(){
		return this.posy;
	}
}
