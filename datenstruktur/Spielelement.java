package datenstruktur;

import java.awt.Image;

public class Spielelement {

	//Es gibt zwei Spielelemente, Key und Heiltrank, die über ähnliche Methoden verfügen.
	//Diesen Fakt kann man über Vererbung elegant ausnutzen
	
	//Attribute
	int posx,posy;			//Position des Spielelements
	boolean aufgenommen;	//Status, ob das Element aufgenommen wurde
	Image Bild;				//Bild des Spielelements
	
	//Position eines Spielelements wird geändert
	public void setPos(int a, int b){
		this.posx=a;
		this.posy=b;
	}
	
	//Erhalten der x-Koordinate des Spielelements
	public int getPosX(){
		return posx;
	}
	
	//Erhalten der y-Koordinate des Spielelements
	public int getPosY(){
		return posy;
	}
	
	//Frage, ob das Spielelement bereits aufgenommen wurde
	public boolean getStatus(){
		return this.aufgenommen;
	}
	
	//Spielelemente erhalten den Status aufgenommen, wenn sie aufgenommen wurden
	public void aufnehmen(){
		this.aufgenommen=true;
	}
	
	//Bild des Spielelements wird zurückgegeben
	public Image getBild(){
		return this.Bild;
	}
}
