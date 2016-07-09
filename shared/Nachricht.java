package shared;

import clientengine.Level;

// Nachrichten werden zwischen Client und Server gesendet
public class Nachricht {
	/* 
	 * Typen von Messages:
	 * type 0 : Login-Message
	 * type 1 : Spielerbewegung
	 * type 2 : Trankaufnahme
	 * type 3 : Level geschafft
	 * type 4 : Schl�sselaufnahme
	 * type 5 : Fehler (z.B. falsche Login-Daten, inkonsistente Bewegungen, usw)
	 * type 6 : Level geladen
	 */
	public int typ;
	
	// Eine Nachricht kann Koordinaten Enthalten
	public int xKoo;										
	public int yKoo;
	
	// LevelNachricht enthaelt Benutzername und Passwort; Fehlermeldung, falls Verbindung abbricht oder aehnlich
	String fehlermeldung, benutzername, passwort;
	
	//Level kann per Nachricht geschickt werden
	int[][] leveldaten;
	
	// Nachricht, die gesendet wird, wenn sich der Spieler bewegt (type 1), die Koordinaten sind die neuen Koordinaten �
	// oder, wenn ein Trank (type 2) oder der Schluessel (type 4) aufgenommen wurden
	public Nachricht(int t,int x, int y){
		this.typ=t;
		this.xKoo=x;
		this.yKoo=y;
	}
	
	// Nachricht, die ein Level enth�lt (wird am Anfang vom Server empfangen)
	public Nachricht (int t, int[][] x){
		this.typ = t;
		this.leveldaten = x;
	}

	// Fehlermeldung, String enthaelt die Fehlermeldung in Textform
	public Nachricht (int t, String s){
		this.typ=t;
		this.fehlermeldung = s;
	}
	
	// Einlognachricht, sendet eingegebenen Benutzernamen und Passwort an den Server, um zu testen, ob der User mit dem Passwort existiert
	public Nachricht (int t, String name, String pw){
		this.typ=t;
		this.benutzername = name;
		this.passwort = pw;
	}
	
	// Nachricht, die gesendet wird, wenn das Level geschafft wurde(Typ 3) oder gecheatet wurde (Typ 7)
	public Nachricht (int t){
		this.typ = t;
	}
	
	//Typ der Nachricht muss beim Empfangen geladen werden
	public int getTyp(){
		return this.typ;
	}
	
	//Enthalten x-Koordinate muss bei Standort geladen werden
	public int getxKoo(){
		return this.xKoo;
	}
	
	//Enthalten y-Koordinate muss bei Standort geladen werden
	public int getyKoo(){
		return this.yKoo;
	}
}
