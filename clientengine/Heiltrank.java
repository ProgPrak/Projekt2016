package clientengine;

import datenstruktur.Spielelement;

//Hat Attribute und Methoden des Spielelements
public class Heiltrank extends Spielelement{
	private int wirkung;
	
		//Ein Heiltrank heilt mit einer Wirkung einen Spieler und wird auf einer Position x,y generiert
		public Heiltrank(int wirkung,int x,int y){
			this.wirkung = wirkung;
			this.posx=x;
			this.posy=y;
		}
		
		//Heilwirkung des Tranks zurückgeben
		public int getWirkung(){
			return wirkung;
		}
}
