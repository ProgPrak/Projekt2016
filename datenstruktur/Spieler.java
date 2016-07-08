package datenstruktur;

import GUI.GUImain;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//Spieler ist eine Unterklasse der Klasse Figur
public class Spieler extends Figur {

	private String name;			//Name des Spielers
	private boolean hatSchluessel;	//Status, ob Schlüssel vorhanden ist
	private int anzHeiltraenke;		//Anzahl der aufgenommenen Tränke
	private int heiltrankWirkung;	//Wirkung des Heiltranks wird hier gespeichert
	private GUImain fenster;		//Fenster in dem gespielt wird

	
	//Konstruktor
	public Spieler(GUImain fenster){
		this.fenster = fenster;
		setAnzahlHeiltraenke(0);
		setPos(0,0);		
		setMaxGesundheit(getGesundheit());
		setName("Hindi Bones");
		try {
			setBild(ImageIO.read(new File("img/spieler.png")));
		} catch (IOException e) {
			System.err.print("Das Bild konnte nicht geladen werden.");
		}
	}

	//Schlüssel wird aufgenommen
	public void nimmSchluessel(){
		hatSchluessel = true;
	}

	
	//Schlüssel ist nicht mehr aufgenommen
	public void entferneSchluessel(){
		hatSchluessel = false;
	}	
	
	//Heiltrank wird benutzt
	public int benutzeHeiltrank(){
		setAnzahlHeiltraenke(anzHeiltraenke-1);
		return heiltrankWirkung;
	}
	
	//Heiltrank wird aufgenommen
	public void nimmHeiltrank(Heiltrank t){
		anzHeiltraenke++;
		heiltrankWirkung = t.getWirkung();
	}
	
	//Anzahl der Heiltraenke wird veraendert
	public void setAnzahlHeiltraenke(int anzahl){
		if (anzahl >= 0) anzHeiltraenke = anzahl;
	}
	
	//Anzahl der Heiltraenke wird zurueckgegeben
	public int getAnzahlHeiltraenke(){
		return anzHeiltraenke;
	}
	
	//Status, ob der Schlüssel bereits aufgenommen wurde
	public boolean hatSchluessel(){
		return hatSchluessel;
	}
	
	//Name des Spielers wird zurückgegeben
	public String getName(){
		return name;
	}
	
	//Name des Spielers wird geaendert
	public void setName(String name){
		this.name = name;
	}
	
	public boolean monsterNah()
	{
		if(fenster.spielFeld.getKarte()[getPosX()/32+1][getPosY()/32] == 5 || fenster.spielFeld.getKarte()[getPosX()/32-1][getPosY()/32] == 5 || fenster.spielFeld.getKarte()[getPosX()/32][getPosY()/32+1] == 5 || fenster.spielFeld.getKarte()[getPosX()/32][getPosY()/32-1] == 5) return true;
		else return false;
	}
	
	public boolean aufOffenerTuer()
	{
		if(fenster.spielFeld.getKarte()[getPosX()/32][getPosY()/32] == 6)
		{
			return true;
		}
		
		return false;
	}
}
