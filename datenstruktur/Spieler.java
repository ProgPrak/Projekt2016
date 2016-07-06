package datenstruktur;

import GUI.GUImain;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//Jabo
public class Spieler extends Figur {
// Kommentar1234
	private String name;
//asdkjlas
	private boolean hatSchluessel;
	private int anzHeiltraenke;
	private int heiltrankWirkung;
	
	private GUImain fenster;


	public boolean AmRand;
	
	public Spieler(GUImain fenster){
		this.fenster = fenster;
		
		setAnzahlHeiltraenke(0);
		setPos(0,0);		
		setGesundheit(100);
		setMaxGesundheit(getGesundheit());
		setName("Hindi Bones");
		
		try {
			setBild(ImageIO.read(new File("img/spieler.png")));
		} catch (IOException e) {
			System.err.print("Das Bild konnte nicht geladen werden.");
		}
	}

	public void nimmSchluessel(){
		hatSchluessel = true;
	}
	
	public void entferneSchluessel(){
		hatSchluessel = false;
	}	
	
	public int benutzeHeiltrank(){
		setAnzahlHeiltraenke(anzHeiltraenke-1);
		return heiltrankWirkung;
	}
	
	public void nimmHeiltrank(Heiltrank t){
		anzHeiltraenke++;
		heiltrankWirkung = t.getWirkung();
	}
	
	public void setAnzahlHeiltraenke(int anzahl){
		if (anzahl >= 0) anzHeiltraenke = anzahl;
	}
	
	public int getAnzahlHeiltraenke(){
		return anzHeiltraenke;
	}
	
	public boolean hatSchluessel(){
		return hatSchluessel;
	}
		
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	

	
}
