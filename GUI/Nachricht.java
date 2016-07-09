package GUI;

import java.io.Serializable;

// Nachrichten werden zwischen Client und Server gesendet
public class Nachricht implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* 
	 * Typen von Messages:
	 * type 0 : Login-Message
	 * type 1 : Spielerbewegung
	 * type 2 : Trankaufnahme
	 * type 3 : Level geschafft
	 * type 4 : Schlüsselaufnahme
	 * type 5 : Fehler (z.B. falsche Login-Daten, inkonsistente Bewegungen, usw)
	 * type 6 : Level geladen
	 * type 7 : Spielende
	 * type 8 : Ping
	 * type 9 : Aktuelles Level
	 * type 10: Einloggen erfolgreich oder auch nicht
	 */
	int typ;
	
	// Eine Nachricht kann Koordinaten Enthalten
	int xKoo;										
	int yKoo;
	
	boolean status;
	
	// LevelNachricht enthaelt Benutzername und Passwort; Fehlermeldung, falls Verbindung abbricht oder aehnlich
	String fehlermeldung, benutzername, passwort;
	
	//Level-Array kann per Nachricht geschickt werden
	int[][] leveldaten;
	
	public Nachricht(int t, boolean s){
		this.typ=t;
		this.status=s;
	}
	
	// Nachricht, die gesendet wird, wenn sich der Spieler bewegt (type 1), die Koordinaten sind die neuen Koordinaten ¨
	// oder, wenn ein Trank (type 2) oder der Schluessel (type 4) aufgenommen wurden
	public Nachricht(int t,int x, int y){
		this.typ=t;
		this.xKoo=x;
		this.yKoo=y;
	}
	
	// Nachricht, die ein Level enthält (wird am Anfang vom Server empfangen)
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
	
	// Nachricht, die gesendet wird, wenn das Level geschafft wurde(Typ 3) oder gecheatet wurde (Typ 7), oder Spielende(Typ 8)
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
	//Leveldaten werden zurückgegeben
	public int[][] getLevelDaten(){
		return leveldaten;
	}
	public boolean getStatus(){
		return this.status;
	}
	public String getMessage(){
		String nachricht = null;
		switch(this.getTyp()){
		
		case 0:
			nachricht = this.passwort; // + this.benutzername;
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4: 
			break;
		case 5: 
			nachricht = this.fehlermeldung;
			break;
		case 6:
			break;
		case 7:
			break;
		case 8: 
			nachricht = this.fehlermeldung;
			break;
			
		default:
				
		}
		return nachricht;
	}
	public String getName(){
		return this.benutzername;
	}
	public String getPasswort(){
		return this.passwort;
	}
	public String getFehler(){
		return this.fehlermeldung;
	}
}
