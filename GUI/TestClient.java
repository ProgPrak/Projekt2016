package GUI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

import datenstruktur.Spieler;
import kommunikation.CommClient;


public class TestClient
{
	GUImain gui;																
	Queue<Nachricht> Nachrichten = new LinkedList<Nachricht>();				
	Queue<Nachricht> NachrichtenEmpfangen = new LinkedList<Nachricht>();
	int[][] aktuellesLevel, level1, level2, level3, level4, level5;
	ArrayList<int[][]> alleLevel;
	String benutzername, passwort;
	int levelnummer;
	Spieler spieler;
	int[][] dummyMap;
	CommClient commClient;
			String s;  
	boolean anmeldungerfolgreich;
	int nummerzumladen;
	//
	final int[][] dummyMap2={
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
			  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3},
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

	public TestClient(GUImain gui, int port){
		
		
		this.gui = gui;
		levelnummer = 1;
		/*dummyMap = new int[][]{
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
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,s0,0,0,0,0,0}};
				  
		aktuellesLevel = dummyMap;*/
		this.commClient=new CommClient("localhost",port);
		this.commClient.startingProcess();
		this.anmeldungerfolgreich=false;
		nummerzumladen=0;
		System.out.println("bis hier hin");
	}		
	
	public void aktualisiereArray(int i , int j)
    {
    	aktuellesLevel[i][j] = 1;
    }
    
    public void aktualisiereArrayFlexibel(int i , int j, int k)
    {
    	aktuellesLevel[i][j] = k;
    }
    
	public void sende(Nachricht m){
		try {
			this.commClient.sendMessage(m);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void aktualisiere(int ereignis){
		spieler = gui.spieler;
		if(ereignis==8){
			sende(new Nachricht(8));
		}
		else if(ereignis == 4){
			sende(new Nachricht(ereignis,gui.spielFeld.schluessel.getPosX(),gui.spielFeld.schluessel.getPosX()));
		}
		else{
			sende(new Nachricht(ereignis,spieler.getPosX()/32,spieler.getPosY()/32));
		}
	}
	
	public int[][] getAktuellesLevel(){
		return aktuellesLevel;
	}
	
	/*public void ausgabe(){
		while(!Nachrichten.isEmpty()){
			Nachricht m = Nachrichten.poll();
			
				switch (m.getTyp()){
				
				  !Die hier angegebenen Reaktionen auf die Messages sind nur zu Testzwecken und werden bei der Integration der anderen Komponenten ausgebessert!
				 
					case 0: this.benutzername=m.benutzername;this.passwort=m.passwort; System.out.println("Einloggen von " + benutzername + " mit Passwort "+passwort+" erfolgreich!"); break;
					case 1: System.out.println("Position des Spielers: " + m.getxKoo()+ ", " + m.getyKoo());break;
					case 2: System.out.println("Der Trank an der Position " + m.getxKoo() + ", " + m.getyKoo() + " wurde aufgenommen");break;
					case 3: System.out.println("Das Level wurde abgeschlossen!");break;
					case 4: System.out.println("Der Schluessel an der Stelle "+m.getxKoo()+", "+m.getyKoo()+" wurde aufgenommen");break;
					case 5: System.out.println("Ein Fehler ist aufgetreten!");break;
					case 6: System.out.println("Level wurde geladen!");break;
					case 7: System.out.println("Der Schlüssel wurde durch cheaten aufgenommen.");break;
					case 8: System.out.println("DU bist tot, Opfer!");
				}
			
		}
	}
	*/
	public void uebertrage(TestClient empfaenger){
		for(Nachricht n : this.Nachrichten)
		{
			empfaenger.NachrichtenEmpfangen.offer(n);
		}
	}
	
	public void empfange()
	{
		Nachricht m = this.commClient.getNextMessage();
		switch(m.getTyp()){
		case 0: this.anmeldungerfolgreich=true;break;
		case 5: this.anmeldungerfolgreich=false; break;
		case 6: if(this.nummerzumladen==1){
					this.level1=m.getLevelDaten(); 
					this.nummerzumladen++;
				}
				else if(this.nummerzumladen==2){
					this.level1=m.getLevelDaten(); 
					this.nummerzumladen++;
				}
				else if(this.nummerzumladen==3){
					this.level1=m.getLevelDaten(); 
					this.nummerzumladen++;
				}
				else if(this.nummerzumladen==4){
					this.level1=m.getLevelDaten(); 
					this.nummerzumladen++;
				}
				if(this.nummerzumladen==5){
					this.level1=m.getLevelDaten(); 
					this.nummerzumladen++;
				}
				break;
		}
				
		
	}
		/*
		 * for(int i=0;i<NachrichtenEmpfangen.size();i++){
		 *
		*	Nachricht m = NachrichtenEmpfangen.poll();
		*	
		*		switch (m.getTyp()){
		*		
		*		 // !Die hier angegebenen Reaktionen auf die Messages sind nur zu Testzwecken und werden bei der Integration der anderen Komponenten ausgebessert!
		*		 
		*			case 0: System.out.println("Einloggen erfolgreich!"); break;
		*			case 1: System.out.println("Position des Spielers: " + m.getxKoo()+ ", " + m.getyKoo());break;
		*			case 2: System.out.println("Der Trank an der Position " + m.getxKoo() + ", " + m.getyKoo() + " wurde aufgenommen");break;
		*			case 3: System.out.println("Das Level wurde abgeschlossen!");break;
		*			case 4: System.out.println("Der Schluessel an der Stelle "+m.getxKoo()+", "+m.getyKoo()+" wurde aufgenommen");break;
		*			case 5: System.out.println("Ein Fehler ist aufgetreten!");break;
		*			case 6: System.out.println("Level wurde geladen!");break;
		*			case 7: System.out.println("Der Schlüssel wurde durch cheaten aufgenommen.");break;
		*		}
		*}
*/
		public void nextLevel(){
			
			if(levelnummer == 1)
			{
				aktuellesLevel = this.dummyMap2;
			}
			if(levelnummer == 2)
			{
				aktuellesLevel = level3;
				levelnummer++;
			}
			if(levelnummer == 3)
			{
				aktuellesLevel = level4;
				levelnummer++;
			}
			if(levelnummer == 4)
			{
				aktuellesLevel = level5;
				levelnummer++;
			}
			levelnummer++;
			this.sende(new Nachricht(3));
		}
	public boolean getAnmeldungerfolgreich(){
		return this.anmeldungerfolgreich;
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
