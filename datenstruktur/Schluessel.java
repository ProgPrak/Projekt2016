package datenstruktur;

import java.awt.Image;
import java.awt.Toolkit;


public class Schluessel extends Spielelement{
	
	//Ein Schlüssel wird benötigt, damit man das nächste Level erreichen kann
	//Er liegt an einer Stelle x,y im Level
	
	//Konstruktor, Bild und Position werden festgelegt, Schluessel ist logischerweise nicht aufgenommen
	public Schluessel (int x, int y){
		Toolkit tk = Toolkit.getDefaultToolkit();
		this.Bild = tk.getImage("img/schluessel.png");
		this.posx=x;
		this.posy=y;
		this.aufgenommen= false;	 
	}	
}
