package shared;

import java.io.Serializable;
import java.util.UUID;

public class Message implements Serializable {

	int typ;
	int spielerID;
	int xKoo;
	int yKoo;
	long Erstellzeit;
	boolean aufgenommen;
	String fehlermeldung, benutzername, passwort,s;
	int[][] leveldaten;
	
	/* 
	 * Typen von Messages:
	 * type 0 : Login-Message
	 * type 1 : Spielerbewegung
	 * type 2 : Trankaufnahme
	 * type 3 : Level geschafft
	 * type 4 : Schluesselaufnahme
	 * type 5 : Fehler (z.B. falsche Login-Daten, inkonsistente Bewegungen, usw)
	 * type 6 : Level geladen
	 * typ 7: cheat
	 * typ 8: ping
	 */
	
	
	// Nachricht, die gesendet wird, wenn sich der Spieler bewegt, die Koordinaten sind die neuen Koordinaten
	public Message(int t, int p,int x, int y){
		this.typ=t;
		this.spielerID=p;
		this.xKoo=x;
		this.yKoo=y;
	}
	
	// Nachricht, die gesendet wird, wenn ein Trank (type 2) oder der Schluessel (type 4) aufgenommen wurden
	public Message(int t,int a, int b){
		this.typ=t;
		this.xKoo=a;
		this.yKoo=b;
	}
	
	// Nachricht, die ein Level enth‰lt (wird am Anfang vom Server empfangen)
	public Message (int t, int[][] x){
		this.typ = t;
		this.leveldaten = x;
	}

	// Fehlermeldung, String enthaelt die Fehlermeldung in Textform und für PING-Message
	public Message (int t, String s){
		this.typ=t;
		this.fehlermeldung = s;
	}
	
	// Einlognachricht, sendet eingegebenen Benutzernamen und Passwort an den Server, um zu testen, ob der User mit dem Passwort existiert
	public Message (int t, String name, String pw){
		this.typ=t;
		this.benutzername = name;
		this.passwort = pw;
	}
	
	// Nachricht, die gesendet wird, wenn das Level geschafft wurde(Typ 3) oder gecheatet wurde (Typ 7)
	public Message (int t){
		this.typ = t;
	}
	
	
	public int getTyp(){
		return this.typ;
	}
	
	public int getxKoo(){
		return this.xKoo;
	}
	public int getyKoo(){
		return this.yKoo;
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
	
}
