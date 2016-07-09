package serverengine_sw;

import shared.*;
import GUI.*;
import GUI.Nachricht;
import kommunikation.*;


public class LevelverwaltungNeu {
	
	static Spieler spieler;
	public static int groesse;
	public static int breite;
	public Map levelInhalt;
	public Map l1,l2,l3,l4,l5;
	public int levelcounter;
	public static int anzahlTraenkeImLevel;
	public boolean einSpielerHatSchluessel;
	public Nachricht nachricht;
	public TestClient client;
	//public TestClient server;
	//public ServerKommunikationTest server;
	public CommServer server;
	
	
	public LevelverwaltungNeu() {
		this.groesse = groesse;
		this.breite=breite;
		//levelInhalt = new Map (this.groesse,this.breite,0,0,anzahlTraenkeImLevel);
		l1= new Map (20,20,1,0,5);
		l2= new Map (20,20,2,0,4);
		l3= new Map (20,20,3,0,3);
		l4= new Map (20,20,4,0,2);
		l5= new Map (20,20,5,0,1);
		l1.wandleZuGuiArrayum();
		l2.wandleZuGuiArrayum();
		l3.wandleZuGuiArrayum();
		l4.wandleZuGuiArrayum();
		l5.wandleZuGuiArrayum();
		Message ll1 = new Message(6,l1.GuiArray);
		Message ll2 = new Message(6,l2.GuiArray);
		Message ll3 = new Message(6,l3.GuiArray);
		Message ll4 = new Message(6,l4.GuiArray);
		Message ll5 = new Message(6,l5.GuiArray);
		server.messageToClient(ll1);
		server.messageToClient(ll2);
		server.messageToClient(ll3);
		server.messageToClient(ll4);
		server.messageToClient(ll5);
		levelInhalt=l1;
		levelcounter=1;
		try {
			server= new CommServer(7891);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i<groesse ; i++){
			for (int j = 0; j<breite ; j++){
				if (levelInhalt.Struktur[i][j].SpielerDrauf==true){
					Spieler spieler = new Spieler (0);
					spieler.posx=i;
					spieler.posy=j;
				}
			}
			levelInhalt.Struktur[spieler.posy][spieler.posx].SpielerDrauf=true;
		}
		TestClient server=new TestClient();
		TestClient client = new TestClient ();
		
	}
	public static boolean testeTrankBenutzbar(){ //Teste ob Spieler Traenke hat
		boolean funktioniert;
		if (spieler.anzHeiltraenke>0){
			funktioniert = true;
		}else {
			funktioniert = false;
		}
		return funktioniert;
	}
	
	public static void benutzeTrank(){ //benutze Trank
		if (testeTrankBenutzbar()==true){
			spieler.anzHeiltraenke=spieler.anzHeiltraenke-1;
			if (spieler.Gesundheit+10<spieler.MaxGesundheit) spieler.Gesundheit=spieler.Gesundheit+10;
			else spieler.Gesundheit=spieler.MaxGesundheit;
		}
	}
	
	public  boolean behandleTrankaufnahme() { //Kann Spieler einen Trank aufnehmen
		boolean moeglich=false;
		if (levelInhalt.Struktur[spieler.posy][spieler.posx].anzahlTraenke>0) {
			moeglich = true;
			levelInhalt.wandleZuGuiArrayum();
			Message neuesLevel = new Message(6,levelInhalt.GuiArray);
			server.messageToClient(neuesLevel);
		}
		
		return moeglich;
	}
	
	public  boolean behandleschluesselaufnahme() { //Kann Spieler einen Schluessel aufnehmen
		boolean moeglich=false;
		if(levelInhalt.Struktur[spieler.posy][spieler.posx].surfaceType==5){
			moeglich = true;
			levelInhalt.wandleZuGuiArrayum();
			Message neuesLevel = new Message(6,levelInhalt.GuiArray);
			server.messageToClient(neuesLevel);
		}
		
		return moeglich;
	}
	
	public boolean levelGewonnen (){ //falls Spieler auf dem Exit steht und Schluessel hat wird Exit offen
		boolean ergebnis = false;
		if (levelInhalt.Struktur[spieler.posy][spieler.posx].surfaceType==3&&this.einSpielerHatSchluessel==true){
			levelInhalt.Struktur[spieler.posy][spieler.posx].surfaceType=2;
			ergebnis=true;
			levelInhalt.wandleZuGuiArrayum();
			Message neuesLevel = new Message(6,levelInhalt.GuiArray);
			server.messageToClient(neuesLevel);
		}
		return ergebnis;
	}
	
	
	public void newLevel() { //generiere neues Level
		
		switch (levelcounter){
		case 1: levelInhalt=l2;
		levelcounter=levelcounter+1;
		break;
		case 2: levelInhalt=l3;
		levelcounter=levelcounter+1;
		break;
		case 3: levelInhalt=l4;
		levelcounter=levelcounter+1;
		break;
		case 4: levelInhalt=l5;
		levelcounter=levelcounter+1;
		break;
		}
		for (int i = 0; i<groesse ; i++){
			for (int j = 0; j<breite ; j++){
				if (levelInhalt.Struktur[i][j].SpielerDrauf==true){
					Spieler spieler = new Spieler (0);
					spieler.posx=i;
					spieler.posy=j;
				}
			}
		}
	}
	
	
	
	public void verarbeiteNachricht(Nachricht Nachricht){
		if (Nachricht.typ ==1){
			levelInhalt.Struktur[spieler.posy][spieler.posx].SpielerDrauf=false;
			this.spieler.posx=Nachricht.xKoo;
			this.spieler.posy=Nachricht.yKoo;
			levelInhalt.Struktur[spieler.posy][spieler.posx].SpielerDrauf=true;
			levelInhalt.wandleZuGuiArrayum();
			Message neuesLevel = new Message(6,levelInhalt.GuiArray);
			server.messageToClient(neuesLevel);
		}
		
		 if (Nachricht.typ == 2){
			System.out.println(spieler.anzHeiltraenke);
			if(this.behandleTrankaufnahme()){
				System.out.println("Spieler beim Trank");
				System.out.println(spieler.anzHeiltraenke);
			}else{
				System.out.println("Spieler nicht beim Trank");
				System.out.println(spieler.anzHeiltraenke);
			}
		}else if (Nachricht.typ == 3){
			if(this.levelGewonnen()){
				System.out.println("N�chstes Level");
			}else{
				System.out.println("Level konnte nicht beendet werden");
				Nachricht Fehlermeldung = new Nachricht (7, "level nicht beendet");
			}
		}else if (Nachricht.typ == 4){
			if(this.behandleschluesselaufnahme()){
				System.out.println("Schl�ssel aufgehoben");
			}else{
				System.out.println("Schl�ssel nicht aufgehoben");
				Nachricht Fehlermeldung = new Nachricht (7, "Schl�ssel nicht bei Spieler");
			}
		
		}
	}

}
