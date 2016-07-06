package datenstruktur;

import java.awt.Image;
import java.awt.Toolkit;

public class Schluessel extends Spielelement{
	
	private Image bild;
	//Ein Key wird benötigt, damit man das nächste Level erreichen kann
	//Er liegt an einer Stelle x,y im Level
	public Schluessel (int x, int y){
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		bild = tk.getImage("img/schluessel.png");
		this.posx=x;
		this.posy=y;
		this.aufgenommen= false;
		 
	}
	
	public Image getBild()
	{
		return bild;
	}
	
	
	
}
