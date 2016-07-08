package GUI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import clientengine.Level;
import datenstruktur.Spieler;


public class TestClient
{
	GUImain gui;
	int id;																	
	Queue<Nachricht> Nachrichten = new LinkedList<Nachricht>();				
	Queue<Nachricht> NachrichtenEmpfangen = new LinkedList<Nachricht>();
	int[][] aktuellesLevel, level1, level2, level3, level4, level5;
	ArrayList<int[][]> alleLevel;
	String benutzername, passwort;
	int levelnummer;
	Spieler spieler;
	int[][] dummyMap={
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,1,5,1,1,0,1,1,1,0,0,0,0,0,1,1,1,0,0},
			  {0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,0},
			  {0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,5,0,1,0,0},
			  {0,0,0,2,0,0,0,1,0,1,0,0,0,0,0,1,0,5,0,0},
			  {0,0,0,1,1,1,0,1,1,1,0,0,0,0,0,1,0,1,0,0},
			  {0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,0},
			  {0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,1,0,1,0,0},
			  {0,1,0,5,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,0},
			  {0,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,0,0},
			  {0,0,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,0},
			  {0,1,1,1,0,1,1,1,1,1,0,0,0,1,0,1,0,5,0,0},
			  {0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,1,0,0},
			  {0,1,0,0,0,0,0,1,0,1,1,1,1,1,1,1,0,1,0,0},
			  {0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0},
			  {0,1,1,1,1,1,1,4,1,2,0,1,0,1,1,1,1,1,0,0},
			  {0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0},
			  {0,0,0,0,0,0,0,0,0,1,3,1,0,0,0,1,0,1,0,0},
			  {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,7,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		  // 0 = Wand
		  // 1 = Boden
		  // 2 = Trank
		  // 3 = Schluessel
		  // 4 = Spieler
		  // 5 = Monster
		  // 6 = Tuer offen
		  // 7 = Tuer zu

	public TestClient(int i, GUImain gui){
		this.id=i;
		this.gui = gui;
		aktuellesLevel = dummyMap;
		alleLevel = new ArrayList<int[][]>(5);
		level1=dummyMap;
		level2=dummyMap;
		level3=dummyMap;
		level4=dummyMap;
		level5=dummyMap;
		alleLevel.add(level1);
		alleLevel.add(level2);
		alleLevel.add(level3);
		alleLevel.add(level4);
		alleLevel.add(level5);
		
	}		
	public void sende(Nachricht m){
		Nachrichten.add(m);
		ausgabe();
	}
	
	public void aktualisiere(int ereignis){
		aktuellesLevel=gui.spielFeld.getKarte();
		spieler = gui.spieler;
		sende(new Nachricht(ereignis,spieler.getPosX(),spieler.getPosY()));
	}
	
	public int[][] getAktuellesLevel(){
		return aktuellesLevel;
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
					case 6: System.out.println("Level wurde geladen!");break;
					case 7: System.out.println("Der Schlüssel wurde durch cheaten aufgenommen.");break;
				}
		}
	}
		public int[][] getNaechstesLevel(){
			levelnummer++;
			aktuellesLevel = alleLevel.get(levelnummer);
			return alleLevel.get(levelnummer);
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
