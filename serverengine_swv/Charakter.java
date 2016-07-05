package Spielweltverwaltung;

import java.io.IOException;


public class Charakter {
	//Speicherung der spielerbezogenen Daten
	public static int spielerID;
	public static int lebenspunkte;
	public static int spielerSchaden;
	public static int anzahlTraenke;
	public static boolean schluessel;
	//Speichert die Position des Spielers
	public static int posX;
	public static int posY;
	
	//Konstruktor
	public Charakter(int id, int anfangsLebenspunkte, int anfangsSchaden, int anfangsTraenke, int anfangsX, int anfangsY) throws IOException {
		 spielerID = id;
		 setLebenspunkte(anfangsLebenspunkte);
		 setSchaden(anfangsSchaden);
		 setAnzahlTraenke(anfangsTraenke);
		 setSchluessel(false);
		 setPosX(anfangsX);
		 setPosY(anfangsY);
		 
	 }
	
	//Keine setter-Methode f�r die Spieler-ID, da diese nicht ver�ndert werden soll
	
	//getter-Methode f�r die Spieler-ID
	public static int getSpielerID(){
		return spielerID;
	}
	
	//setter-Methode f�r die Lebenspunkte
	public static void setLebenspunkte(int neueLebenspunkte){
		lebenspunkte = neueLebenspunkte;
	}
	
	//getter-Methode f�r die Lebenspunkte
	public static int getLebenspunkte(){
		return lebenspunkte;
	}
	
	//setter-Methode f�r den Schaden, den der Spieler verursacht
	public static void setSchaden (int neuSpielerSchaden){
		spielerSchaden = neuSpielerSchaden;
	}
	
	//getter-Methode f�r den Schaden, den der Spieler verursacht
	public static int getspielerSchaden(){
		return spielerSchaden;
	}
	
	//setter-Methode f�r die Anzahl der Tr�nke, die der Spieler bei sich tr�gt
	public static void setAnzahlTraenke(int neuAnzahlTraenke){
		anzahlTraenke = neuAnzahlTraenke;
	}
	
	//getter-Methode f�r die Anzahl der Tr�nke, die der Spieler bei sich tr�gt
	public static int getAnzahlTraenke(){
		return anzahlTraenke;
	}
	
	//setter-Methode f�r den Schl�ssel. Wird dieser gefunden, so wird er auf true gesetzt. Bei jedem Level-Neustart auf false
	public static void setSchluessel(boolean neuschluessel){
		schluessel=neuschluessel;
	}
	
	//getter-Methode, die zur�ck gibt, ob der Schl�ssel bisher von diesem Charakter gefunden wurde
	public static boolean getSchluessel(){
		return schluessel;
	}
	
	//setter-Methode f�r die x-Koordinate des Spielers
	public static void setPosX(int neuPosX){
		posX=neuPosX;
	}
	
	//getter-Methode f�r die x-Koordinate des Spielers
	public static int getPosX(){
		return posX;
	}
	
	//setter-Methode f�r die y-Koordinate des Spielers
	public static void setPosY(int neuPosY){
		posY=neuPosY;
	}
	
	//getter-Methode f�r die y-Koordinate des Spielers
	public static  int getPosY(){
		return posY;
	}
}
