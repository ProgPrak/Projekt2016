package datenstruktur;

import java.awt.Image;

//Oberklasse f�r Monster und Spieler
public class Figur {
// testtest
	//Attribute
	private int Gesundheit;
	private int MaxGesundheit;
	private int Schaden;
	private int posx,posy;
	private Image Bild;
	
	//Getter und Setter
	public void setPos(int x,int y){
		this.posx=x;
		this.posy=y;
	}
	
	public int getPosY(){
		return posy;
	}
	
	public int getPosX(){
		return posx;
	}
	
	public int getGesundheit(){
		return Gesundheit;
	}
	
	public void setGesundheit(int g){
		this.Gesundheit=g;
		if(Gesundheit>MaxGesundheit){
			Gesundheit=MaxGesundheit;
		}
	}
	
	public void setMaxGesundheit(int g){
		this.MaxGesundheit=g;
	}
	
	public int getSchaden(){
		return Schaden;
	}
	
	
	/*public void hoch(){
		posy--;
	}
	
	public void runter(){
		posy++;
	}
	
	public void rechts(){
		posx++;
	}
	
	public void links(){
		posx--;
	}
	*/
	public Image getBild(){
		return Bild;
	}
	
	public void setBild(Image img){
		Bild = img;
	}
	
	//Bewegung einer Figur	
	public void hoch(){
		posy = posy -32;
	}
	
	public void runter(){
		posy = posy +32;
	}
	
	public void links(){
		posx = posx - 32;
	}
	
	public void rechts(){
		posx = posx + 32;
	}
}
