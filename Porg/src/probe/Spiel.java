package probe;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Spiel {

	Level level;
	Spieler s;
	Heiltrank t;
	Ziel z;
	key k;
	Client client;
	
	//Das Level wird als Array generiert
	//Die 1 steht für den Spieler
	//Die 2 steht für das Ziel
	//Die 3 steht für den Heiltrank
	//Die 4 steht für den Schlüssel
	//Im Moment ist es nur möglich sich ins Ziel zu bewegen
	
	public Spiel(){
		
		//Generierung
		try {
			level = new Level(1,new int[5][5]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		s = new Spieler(1);
		t = new Heiltrank(50,2,2);
		z = new Ziel (1,1);
		k = new key (1,0);
		client = new Client(1);
		
		//Solange das Ziel nicht erreicht ist
		while(!ImZielAngekommen()){
			
			//Level wird mit 0en überschrieben
				Level.setInhaltNull();
			
			//Felder mit Spieler etc. werden entsprechend gesetzt
				Level.setLevelInhalt(z.getPosX(), z.getPosY(), 2);
				if(!t.getStatus()){
					level.setLevelInhalt(t.getPosX(), t.getPosY(), 3);
				}
				if (!k.getStatus()){
					level.setLevelInhalt(k.getPosX(), k.getPosY(), 4);
				}
				level.setLevelInhalt(s.getPosX(),s.getPosY(), 1);
				level.ausgabe();
				System.out.println("Gesundheit: "+s.getGesundheit()+"  Anzahl Tränke: "+s.AnzahlTrank() + " Schlüssel:" + k.getStatus());
			
			//Spieler kann sich bewegen, durch eingabe von hoch, runter, links, rechts
				String eingabe = JOptionPane.showInputDialog("Was nun? ¦¦ rauf, runter, rechts, links, cheat?");
				SpielerHandlung(eingabe);
			
			//Wenn der Spieler auf ein Feld mit einem Trank kommt, wird dieser gefragt ob er ihn aufnehmen will
				if(KannTrankAufgenommenwerden()){
					switch(JOptionPane.showInputDialog("Du stehts auf einem Trank, möchstest du ihn aufnehmen")){
					case "ja":	s.anzHeiltraenke++;
								t.aufnehmen();
								client.sende(new Message(2,t.getPosX(),t.getPosY()));
								break;
					}
			}
					
			//Wenn der Spieler auf ein Feld mit einem Schlüssel kommt, wird dieser gefragt ob er ihn aufnehmen will
				if(KannKeyAufgenommenwerden()){
					switch(JOptionPane.showInputDialog("Du stehts auf einem Key, möchstest du ihn aufnehmen")){
					case "ja":	k.aufnehmen();
								client.sende(new Message(4,k.getPosX(),k.getPosY()));
								break;
					}
			}
			
		}
		
		level.setInhaltNull();
		level.setLevelInhalt(z.getPosX(), z.getPosY(), 2);
		if(!t.getStatus()){
			level.setLevelInhalt(t.getPosX(),t.getPosY(), 3);
		}
		if (!k.getStatus()){
			level.setLevelInhalt(k.getPosX(), k.getPosY(), 4);
		}
		level.setLevelInhalt(s.getPosX(), s.getPosY(), 1);
		level.ausgabe();
		System.out.println("Du hast gewonnen!!!");
		client.sende(new Message(3));
		System.out.println("");
		client.ausgabe();
	}
	
	// Methode zur Überpüfung der Trank-Aufnahme
	public boolean KannTrankAufgenommenwerden(){
		if(t.getPosX()==s.getPosX()&&t.getPosY()==s.getPosY()){
			if(!t.getStatus()){
				return true;
			}	
		}
		return false;
	}
	
	// Methode zur Überpüfung der Schlüssel-Aufnahme
	public boolean KannKeyAufgenommenwerden(){
		if(k.getPosX()==s.getPosX()&&k.getPosY()==s.getPosY()){
			if(!k.getStatus()){

				return true;
			}	
		}
		return false;
	}
	
	// Methode zur Überpüfung ob der Spieler im Ziel ist und den Schlüssel besitzt
	public boolean ImZielAngekommen(){
		if(s.getPosX()==z.getPosX()&&s.getPosY()==z.getPosY() && k.getStatus()){
			return true;
		}
		if(s.getPosX()==z.getPosX()&&s.getPosY()==z.getPosY() && !k.getStatus()){
			System.err.println("Du hast den Schluüssel noch nicht!");
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
						break;
		}
		client.sende(new Message(1,s.getID(),s.getPosX(),s.getPosY()));
	}
	
	public static void main(String[] args){
		Spiel s = new Spiel();
	}
}

