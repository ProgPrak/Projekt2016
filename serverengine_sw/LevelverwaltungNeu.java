package serverengine_sw;

import shared.*;
import GUI.Nachricht;


public class LevelverwaltungNeu {
	
	Spieler spieler;
	int spielerKooX, spielerKooY, spielerAnzTraenke, spielerSchaden, spielerGesundheit, spielerMaxGesundheit;
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
	ServerKommunikationTest skt;
	
	
	public LevelverwaltungNeu() {
		//this.groesse = groesse;
		//this.breite=breite;
		//levelInhalt = new Map (this.groesse,this.breite,0,0,anzahlTraenkeImLevel);
<<<<<<< HEAD
		groesse=20;
		breite=20;
		Spieler spieler = new Spieler();
=======
>>>>>>> branch 'master' of https://github.com/ProgPrak/Projekt2016.git
		System.out.println("1");
		l1= new Map (20,20,1,0,5);
		l2= new Map (20,20,2,0,4);
		l3= new Map (20,20,3,0,3);
		l4= new Map (20,20,4,0,2);
		l5= new Map (20,20,5,0,1);
		System.out.println("2");
		l1.wandleZuGuiArrayum();
		l2.wandleZuGuiArrayum();
		l3.wandleZuGuiArrayum();
		l4.wandleZuGuiArrayum();
		l5.wandleZuGuiArrayum();
		System.out.println("3");
		/*Nachricht ll1 = new Nachricht(6,l1.GuiArray);
		Nachricht ll2 = new Nachricht(6,l2.GuiArray);
		Nachricht ll3 = new Nachricht(6,l3.GuiArray);
		Nachricht ll4 = new Nachricht(6,l4.GuiArray);
		Nachricht ll5 = new Nachricht(6,l5.GuiArray);
		server.messageToClient(ll1);
		server.messageToClient(ll2);
		server.messageToClient(ll3);
		server.messageToClient(ll4);
		server.messageToClient(ll5);*/
		levelInhalt=l1;
		levelcounter=1;
		for (int i = 0; i<groesse ; i++){
			for (int j = 0; j<breite ; j++){
				if (levelInhalt.Struktur[i][j].SpielerDrauf==true){
<<<<<<< HEAD
					spieler.setPos(i, j);
=======
					Spieler spieler = new Spieler ();
					spieler.posx=i;
					spieler.posy=j;
>>>>>>> branch 'master' of https://github.com/ProgPrak/Projekt2016.git
				}
			}
<<<<<<< HEAD
			
		}System.out.println(spieler.getPosY());
		System.out.println(spieler.getPosX());
		levelInhalt.Struktur[spieler.getPosY()][spieler.getPosX()].SpielerDrauf=true;
		spielerKooX=spieler.getPosX();
		spielerKooY=spieler.getPosY();
=======
			levelInhalt.Struktur[spieler.posy][spieler.posx].SpielerDrauf=true;
		}
		//TestClient server=new TestClient();
		//TestClient client = new TestClient ();
>>>>>>> branch 'master' of https://github.com/ProgPrak/Projekt2016.git
		
	}
	public  boolean testeTrankBenutzbar(){ //Teste ob Spieler Traenke hat
		boolean funktioniert;
		if (spieler.anzHeiltraenke>0){
			funktioniert = true;
		}else {
			funktioniert = false;
		}
		return funktioniert;
	}
	
	public  void benutzeTrank(){ //benutze Trank
		if (testeTrankBenutzbar()==true){
			spieler.anzHeiltraenke=spieler.anzHeiltraenke-1;
			if (spieler.Gesundheit+10<spieler.MaxGesundheit) spieler.Gesundheit=spieler.Gesundheit+10;
			else spieler.Gesundheit=spieler.MaxGesundheit;
		}
	}
	
	public  boolean behandleTrankaufnahme() { //Kann Spieler einen Trank aufnehmen
		boolean moeglich=false;
		if (levelInhalt.Struktur[spielerKooX][spielerKooY].anzahlTraenke>0) {
			moeglich = true;
			levelInhalt.wandleZuGuiArrayum();
			Nachricht neuesLevel = new Nachricht(6,levelInhalt.GuiArray);
			//server.messageToClient(neuesLevel);
		}
		
		return moeglich;
	}
	public  boolean behandleschluesselaufnahme() { //Kann Spieler einen Schluessel aufnehmen
		boolean moeglich=false;
		if(levelInhalt.Struktur[spielerKooX][spielerKooY].surfaceType==5){
			moeglich = true;
			levelInhalt.wandleZuGuiArrayum();
			Nachricht neuesLevel = new Nachricht(6,levelInhalt.GuiArray);
			//server.messageToClient(neuesLevel);
		}
		
		return moeglich;
	}
	
	public boolean levelGewonnen (){ //falls Spieler auf dem Exit steht und Schluessel hat wird Exit offen
		boolean ergebnis = false;
		if (levelInhalt.Struktur[spielerKooX][spielerKooY].surfaceType==3 && einSpielerHatSchluessel==true){
			ergebnis=true;
			levelInhalt.wandleZuGuiArrayum();
			Nachricht neuesLevel = new Nachricht(6,levelInhalt.GuiArray);
			//server.messageToClient(neuesLevel);
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
<<<<<<< HEAD
					spielerKooX = i;
					spielerKooY = j;
					//spieler.setPos(spielerKooX, spielerKooY);
=======
					Spieler spieler = new Spieler ();
					spieler.posx=i;
					spieler.posy=j;
>>>>>>> branch 'master' of https://github.com/ProgPrak/Projekt2016.git
				}
			}
		}
	}
	
	
	
	public void verarbeiteNachricht(Nachricht Nachricht){
		if (Nachricht.getTyp() ==1){
<<<<<<< HEAD
			Spieler spieler = new Spieler();
			levelInhalt.Struktur[spieler.getPosY()][spieler.getPosX()].SpielerDrauf=false;
			spielerKooX=Nachricht.getxKoo();
			spielerKooY=Nachricht.getyKoo();
			spieler.setPos(spielerKooX, spielerKooY);
			levelInhalt.Struktur[spieler.getPosY()][spieler.getPosX()].SpielerDrauf=true;
=======
			
			levelInhalt.Struktur[spieler.posy][spieler.posx].SpielerDrauf=false;
			this.spieler.posx=Nachricht.getxKoo();
			this.spieler.posy=Nachricht.getyKoo();
			levelInhalt.Struktur[spieler.posy][spieler.posx].SpielerDrauf=true;
>>>>>>> branch 'master' of https://github.com/ProgPrak/Projekt2016.git
			levelInhalt.wandleZuGuiArrayum();
			Nachricht neuesLevel = new Nachricht(6,levelInhalt.GuiArray);
			//server.messageToClient(neuesLevel);
		}
		
		 if (Nachricht.getTyp() == 2)
		 {
			if(this.behandleTrankaufnahme())
			{
				spielerAnzTraenke=+1;
			}
		}
		 else 
			 if (Nachricht.getTyp() == 3){
			if(this.levelGewonnen()){
				System.out.println("Naechstes Level");
			}else{
				System.out.println("Level konnte nicht beendet werden");
				Nachricht Fehlermeldung = new Nachricht (5, "level nicht beendet");
			}
<<<<<<< HEAD
		}else 
			if (Nachricht.getTyp() == 4)
			{
				if(this.behandleschluesselaufnahme())
				{
					einSpielerHatSchluessel = true;
				}

=======
		}else if (Nachricht.getTyp() == 4){
			if(this.behandleschluesselaufnahme()){
				System.out.println("Schluessel aufgehoben");
			}else{
				System.out.println("Schluessel nicht aufgehoben");
				Nachricht Fehlermeldung = new Nachricht (5, "Schluessel nicht bei Spieler");
			}
>>>>>>> branch 'master' of https://github.com/ProgPrak/Projekt2016.git
		
			}
			else 
				if (Nachricht.getTyp() == 11)
				{
						levelInhalt.wandleZuGuiArrayum();
						einSpielerHatSchluessel = true;			
				}
	}

	public static void main(String[]args){
		LevelverwaltungNeu lvwn = new LevelverwaltungNeu();
		while(true)
		{
			lvwn.skt.handler();
		}
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> branch 'master' of https://github.com/ProgPrak/Projekt2016.git
