package datenstruktur;

import java.awt.Image;

public class Spielelement {

	//Es gibt zwei Spielelemente, Key und Heiltrank, die �ber �hnliche Methoden verf�gen.
	//Diesen Fakt kann man �ber Vererbung elegant ausnutzen
	
	//Attribute
	int posx,posy;			//Position des Spielelements
	boolean aufgenommen;	//Status, ob das Element aufgenommen wurde
	Image Bild;				//Bild des Spielelements
	
	//Position eines Spielelements wird ge�ndert
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
	
	//Bild des Spielelements wird zur�ckgegeben
	public Image getBild(){
		return this.Bild;
	}
}
