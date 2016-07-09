package shared;
public class Tile {
	public int[] Koordinaten;
	public int surfaceType;
	/* Erklärung zu surfaceTypes:
	 * 0=Boden
	 * 1=Wand
	 * 2=Ausgang(offen)
	 * 3=Ausgang(zu)
	 * 4=Eingang=Startpunkt des Spielers  // "3" muss von der Spielweltverwaltung bei Schluesselgebrauch zu "4" gemacht werden
	 * 5= Schluessel
	 */
	public int anzahlTraenke;
	public boolean enthaeltSchluessel;
	public int zuSpawnendeMonster; // 0 falls kein Monster, 1 falls Monster (typ=0), 2 falls Monster (typ=1)
	public boolean MonsterHatSchluessel;
	public boolean SpielerDrauf;
	
	public  Tile(){
		anzahlTraenke=0;
		enthaeltSchluessel=false;
		surfaceType=0;
		zuSpawnendeMonster = 0;
		Koordinaten = new int[2];
		MonsterHatSchluessel=false;
		SpielerDrauf=false;
	}
	public void spawneMonster(int typ){
		this.zuSpawnendeMonster=typ;
	}
	

}
