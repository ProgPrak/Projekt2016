package datenstruktur;

public class Heiltrank extends Spielelement{
	
	private int wirkung;
	
	public Heiltrank(int wirkung,int x,int y){
		this.wirkung = wirkung;
		this.posx=x;
		this.posy=y;
	}
	
	public int getWirkung(){
		return wirkung;
	}
}
