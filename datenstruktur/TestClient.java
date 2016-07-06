package datenstruktur;

import java.util.LinkedList;
import java.util.Queue;

import clientengine.Level;

public class TestClient {

	int id;
	Queue<Nachricht> Nachrichten = new LinkedList<Nachricht>();
	Queue<Nachricht> NachrichtenEmpfangen = new LinkedList<Nachricht>();
	Level aktuellesLevel;
	String benutzername, passwort;
	public TestClient(int i){
		this.id=i;
	}
	
	public void sende(Nachricht m){
		Nachrichten.add(m);
	}
	
	public void ausgabe(){
		while(!Nachrichten.isEmpty()){
			Nachricht m = Nachrichten.poll();
			
				switch (m.getTyp()){
				/*
				 * !Die hier angegebenen Reaktionen auf die Messages sind nur zu Testzwecken und werden bei der Integration der anderen Komponenten ausgebessert!
				 */
					case 0: this.benutzername=m.benutzername;this.passwort=m.passwort; System.out.println("Einloggen von " + benutzername + " erfolgreich!"); break;
					case 1: System.out.println("Position des Spielers: " + m.getxKoo()+ ", " + m.getyKoo());break;
					case 2: System.out.println("Der Trank an der Position " + m.getxKoo() + ", " + m.getyKoo() + " wurde aufgenommen");break;
					case 3: System.out.println("Das Level wurde abgeschlossen!");break;
					case 4: System.out.println("Der Schluessel an der Stelle "+m.getxKoo()+", "+m.getyKoo()+" wurde aufgenommen");break;
					case 5: System.out.println("Ein Fehler ist aufgetreten!");break;
					case 6: System.out.println("Level wurde geladen!");break;
					case 7: System.out.println("Der Schlüssel wurde durch cheaten aufgenommen.");break;
				}
			
		}
	}
	
	public void übertrage(TestClient empfaenger){
		for(Nachricht n : this.Nachrichten)
		{
			empfaenger.NachrichtenEmpfangen.offer(n);
		}
	}
	
	public void empfange()
	{
		for(int i=0;i<NachrichtenEmpfangen.size();i++){
			Nachricht m = NachrichtenEmpfangen.poll();
			
				switch (m.getTyp()){
				/*
				 * !Die hier angegebenen Reaktionen auf die Messages sind nur zu Testzwecken und werden bei der Integration der anderen Komponenten ausgebessert!
				 */
					case 0: System.out.println("Einloggen erfolgreich!"); break;
					case 1: System.out.println("Position des Spielers: " + m.getxKoo()+ ", " + m.getyKoo());break;
					case 2: System.out.println("Der Trank an der Position " + m.getxKoo() + ", " + m.getyKoo() + " wurde aufgenommen");break;
					case 3: System.out.println("Das Level wurde abgeschlossen!");break;
					case 4: System.out.println("Der Schluessel an der Stelle "+m.getxKoo()+", "+m.getyKoo()+" wurde aufgenommen");break;
					case 5: System.out.println("Ein Fehler ist aufgetreten!");break;
					case 6: System.out.println("Level wurde geladen!");this.aktuellesLevel=m.leveldaten;break;
					case 7: System.out.println("Der Schlüssel wurde durch cheaten aufgenommen.");break;
				}
			
		}
	}
	/*
	 * Nachrichtentypen
	 * 0 - Einloggen
	 * 1 - Eigene Position
	 * 2 - Heiltrankposition
	 * 3 - Level Abgeschlossen
	 * 4 - Schlüssel aufgenommen
	 * 5 - Fehlermeldung
	 * 6 - Level geladen
	 * 7 - Cheat benutzt
	 */
}
