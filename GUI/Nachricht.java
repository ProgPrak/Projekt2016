package GUI;

import clientengine.Level;

public class Nachricht {
	int typ;
	int spielerID;
	int xKoo;
	int yKoo;
	boolean aufgenommen;
	String fehlermeldung, benutzername, passwort;
	Level leveldaten;
	
	/* 
	 * Typen von Messages:
	 * type 0 : Login-Message
	 * type 1 : Spielerbewegung
	 * type 2 : Trankaufnahme
	 * type 3 : Level geschafft
	 * type 4 : Schlüsselaufnahme
	 * type 5 : Fehler (z.B. falsche Login-Daten, inkonsistente Bewegungen, usw)
	 * type 6 : Level geladen
	 */
	
	// Nachricht, die gesendet wird, wenn sich der Spieler bewegt, die Koordinaten sind die neuen Koordinaten
	public Nachricht(int t, int p,int x, int y){
		this.typ=t;
		this.spielerID=p;
		this.xKoo=x;
		this.yKoo=y;
	}
	
	// Nachricht, die gesendet wird, wenn ein Trank (type 2) oder der Schluessel (type 4) aufgenommen wurden
	public Nachricht(int t,int a, int b){
		this.typ=t;
		this.xKoo=a;
		this.yKoo=b;
	}
	
	// Nachricht, die ein Level enthält (wird am Anfang vom Server empfangen)
	public Nachricht (int t, Level x){
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
	
	
	public int getTyp(){
		return this.typ;
	}
	public int getID(){
		return this.spielerID;
	}
	public int getxKoo(){
		return this.xKoo;
	}
	public int getyKoo(){
		return this.yKoo;
	}
}
