package datenstruktur;

import GUI.GUImain;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spieler extends Figur {

	private String name;


	private boolean hatSchluessel;
	private int anzahlHeiltraenke;
	private int heiltrankWirkung;
	
	private GUImain fenster;


	public boolean onRand;
	
	public Spieler(GUImain fenster){
		this.fenster = fenster;
		
		setAnzahlHeiltraenke(0);
		setPos(0,0);		
		setHealth(100);
		setMaxHealth(getHealth());
		setName("Hindi Bones");
		
		try {
			setImage(ImageIO.read(new File("img/spieler.png")));
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
		setAnzahlHeiltraenke(anzahlHeiltraenke-1);
		return heiltrankWirkung;
	}
	
	public void nimmHeiltrank(Heiltrank t){
		anzahlHeiltraenke++;
		heiltrankWirkung = t.getWirkung();
	}
	
	public void setAnzahlHeiltraenke(int anzahl){
		if (anzahl >= 0) anzahlHeiltraenke = anzahl;
	}
	
	public int getAnzahlHeiltraenke(){
		return anzahlHeiltraenke;
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
