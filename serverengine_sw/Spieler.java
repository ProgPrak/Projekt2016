package serverengine_sw;
//Alessio
//Der Spieler ist eine Unterklasse der Klasse Figur
public class Spieler extends Figur {
	
	int anzHeiltraenke;
	
	//Spieler erhält bei der Erstellung eine ID
	public Spieler(int i){
		setID(i);
		setPos(0,0);
		anzHeiltraenke=0;
		setMaxGesundheit(200);
		setGesundheit(100);
	}
	
	//Falls vorhanden, benutzt der Spieler einen Heiltrank
	public void benutzeHeiltrank(Heiltrank h){
		if(hatHeiltraenke()){
			this.setGesundheit(this.getGesundheit()+h.getWirkung());
			anzHeiltraenke--;
		}
	}
	
	//Spieler hebt einen Heiltrank vom Boden auf
	public void HeiltrankAufheben(Heiltrank h){
		anzHeiltraenke++;
	}
	
	//Es wird geprüft, ob der Spieler Heiltränke besitzt
	public boolean hatHeiltraenke(){
		if(anzHeiltraenke>0){
			return true;
		}
		return false;
	}
	
	
	//Anzahl der Tränke wird Zurückgegeben
	public int AnzahlTrank(){
		return this.anzHeiltraenke;
	}
}
