package clientengine;

import java.io.IOException;

import javax.swing.JOptionPane;

import datenstruktur.Nachricht;
import datenstruktur.TestClient;

public class Spiel {

	Level level, LevelServerseite;
	Spieler s;
	Heiltrank t;
	Ziel z;
	Schluessel k;
	TestClient client;
	
	//Das Level wird als Array generiert
	//Die 1 steht f�r den Spieler
	//Die 2 steht f�r das Ziel
	//Die 3 steht f�r den Heiltrank
	//Die 4 steht f�r den Schl�ssel
	
	public Spiel(){

		s = new Spieler(1);
		t = new Heiltrank(50,2,2);
		z = new Ziel (1,1);
		k = new Schluessel (1,0);
		client = new TestClient(1);
		// Behelfsm��iger Server, der dem Client ein Level sendet
		TestClient server = new TestClient(0);
		//Generierung
		try {
			LevelServerseite = new Level(1,new int[5][5]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Level wird an Client gesendet
		server.sende(new Nachricht(6, LevelServerseite));
		server.�bertrage(client);
		
		
		// Client empf�ngt Level und benutzt dieses
		client.empfange();
		level = client.aktuellesLevel;
		
		// Testbenutzer wird eingeloggt
		client.sende(new Nachricht(0, "Test-Benutzer", "Passwort123"));
		client.ausgabe();
		//Solange das Ziel nicht erreicht ist
		while(!ImZielAngekommen()){
			
			//Level wird mit 0en ueberschrieben
				Level.setInhaltNull();
			
			//Felder mit Spieler etc. werden entsprechend gesetzt; Nur als Testlevel, spaeter wird das Level vom Server uebernommen
				Level.setInhalt(z.getPosX(), z.getPosY(), 2);
				if(!t.getStatus()){
					level.setInhalt(t.getPosX(), t.getPosY(), 3);
				}
				if (!k.getStatus()){
					level.setInhalt(k.getPosX(), k.getPosY(), 4);
				}
				level.setInhalt(s.getPosX(),s.getPosY(), 1);
				level.ausgabe();
				System.out.println("Gesundheit: "+s.getGesundheit()+"  Anzahl Traenke: "+s.AnzahlTrank() + " Schluessel:" + k.getStatus());
			
			//Spieler kann sich bewegen, durch eingabe von hoch, runter, links, rechts; cheat gibt dem Spieler sofort den Schl�ssel
				String eingabe = JOptionPane.showInputDialog("Was nun? �� hoch, runter, rechts, links, cheat?");
				SpielerHandlung(eingabe);
			
			//Wenn der Spieler auf ein Feld mit einem Trank kommt, wird dieser gefragt ob er ihn aufnehmen will
				if(KannTrankAufgenommenwerden()){
					switch(JOptionPane.showInputDialog("Du stehts auf einem Trank, moechtest du ihn aufnehmen?")){
					case "ja":	s.anzHeiltraenke++;
								t.aufnehmen();
								client.sende(new Nachricht(2,t.getPosX(),t.getPosY()));
								break;
					}
			}
					
			//Wenn der Spieler auf ein Feld mit einem Schl�ssel kommt, wird dieser gefragt ob er ihn aufnehmen will
				if(KannKeyAufgenommenwerden()){
					switch(JOptionPane.showInputDialog("Du stehst auf einem Schluessel, moechtest du ihn aufnehmen?")){
					case "ja":	k.aufnehmen();
								client.sende(new Nachricht(4,k.getPosX(),k.getPosY()));
								break;
					}
			}
			
		}
		
		level.setInhaltNull();
		level.setInhalt(z.getPosX(), z.getPosY(), 2);
		if(!t.getStatus()){
			level.setInhalt(t.getPosX(),t.getPosY(), 3);
		}
		if (!k.getStatus()){
			level.setInhalt(k.getPosX(), k.getPosY(), 4);
		}
		level.setInhalt(s.getPosX(), s.getPosY(), 1);
		level.ausgabe();
		System.out.println("Du hast gewonnen!!!");
		client.sende(new Nachricht(1,s.getID(),s.getPosX(),s.getPosY()));
		client.sende(new Nachricht(3));
		System.out.println("");
		client.ausgabe();
	}
	
	// Methode zur �berp�fung der Trank-Aufnahme
	public boolean KannTrankAufgenommenwerden(){
		if(level.getBestimmtenInhalt(s.posx, s.posy) == 3){
			return true;
		}
		return false;
	}
	
	// Methode zur �berp�fung der Schl�ssel-Aufnahme
	public boolean KannKeyAufgenommenwerden(){
		if(k.getPosX()==s.getPosX()&&k.getPosY()==s.getPosY()){
			if(!k.getStatus()){

				return true;
			}	
		}
		return false;
	}
	
	// Methode zur �berp�fung ob der Spieler im Ziel ist und den Schl�ssel besitzt
	public boolean ImZielAngekommen(){
		if(s.getPosX()==z.getPosX()&&s.getPosY()==z.getPosY() && k.getStatus()){
			return true;
		}
		if(s.getPosX()==z.getPosX()&&s.getPosY()==z.getPosY() && !k.getStatus()){
			System.err.println("Du hast den Schluessel noch nicht!");
		}
		return false;
	}
	
	
	public void SpielerHandlung(String e){
		switch(e){
		case "hoch": 	if(s.getPosY()!=0){s.hoch();}
						break;
		case "runter": 	if(s.getPosY()!=level.getLaengeY()){s.runter();}
						break;
		case "links": 	if(s.getPosX()!=0){s.links();}
						break;
		case "rechts": 	if(s.getPosX()!=level.getLaengeX()){s.rechts();}
						break;
		case "trank": 	s.benutzeHeiltrank(t);
						break;
		case "cheat":	k.aufnehmen();
						client.sende(new Nachricht(7));
						break;
		}
		client.sende(new Nachricht(1,s.getID(),s.getPosX(),s.getPosY()));
	}
	
	public static void main(String[] args){
		Spiel s = new Spiel();
	}
}

