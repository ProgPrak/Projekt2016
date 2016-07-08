package datenstruktur;

import java.awt.Image;

//Oberklasse fuer Monster und Spieler
public class Figur {

	//Attribute
	private int Gesundheit = 100;
	private int MaxGesundheit;
	private int Schaden;
	private int posx,posy;
	private Image Bild;
	public boolean amLeben = true;
	
	//Neue Position der Figur wird gesetzt
	public void setPos(int x,int y){
		this.posx=x;
		this.posy=y;
	}
	
	public boolean getAmLebenBoolean()
	{
		return amLeben;
	}
	
	//Y-Koordinate der Figur wird geladen
	public int getPosY(){
		return posy;
	}
	
	// X-Koordinate der Figur wird geladen
	public int getPosX(){
		return posx;
	}
	
	//Gesundheit der Figur wird geladen
	public int getGesundheit(){
		return Gesundheit;
	}
	
	//Gesundheit der Figur wird gesetzt, kann aber nicht groesser als die Maximale Gesundheit sein
	public void setGesundheit(int g){
		this.Gesundheit=g;
		if(Gesundheit>MaxGesundheit){
			Gesundheit=MaxGesundheit;
		}
		if(g == 0)amLeben = false;
	}
	
	//Maximale Gesundheit der Figur wird gesetzt
	public void setMaxGesundheit(int g){
		this.MaxGesundheit=g;
	}
	
	//Schaden der Figur wird geladen
	public int getSchaden(){
		return Schaden;
	}
	
	//Bild der Figur wird geladen
	public Image getBild(){
		return Bild;
	}
	
	//Bild der Figur setzen
	public void setBild(Image b){
		this.Bild=b;
	}
	
	//Bewegung einer Figur nach oben, Summand 32 wichtig für das Zeichnen	
	public void hoch(){
		posy = posy -32;
	}
	
	//Bewegung einer Figur nach unten, Summand 32 wichtig für das Zeichnen
	public void runter(){
		posy = posy +32;
	}
	
	//Bewegung einer Figur nach links, Summand 32 wichtig für das Zeichnen
	public void links(){
		posx = posx - 32;
	}
	
	//Bewegung einer Figur nach rechts, Summand 32 wichtig für das Zeichnen
	public void rechts(){
		posx = posx + 32;
	}
}
