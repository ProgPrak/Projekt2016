package Spielweltverwaltung;

import java.io.IOException;

public class Monster {
	//Speicherung der monsterbezogenen Daten
	public static int monsterID;
	public static int lebenspunkte;
	public static int monsterSchaden;
	public static boolean trankVorhanden;
	//Speichert die Position des Monsters
	public static int posX;
	public static int posY;
	
	public Monster(int id, int anfangsLebenspunkte, int anfangsSchaden, boolean initialisierungTrankVorhanden, int anfangsX, int anfangsY) throws IOException {
		 monsterID = id;
		 setLebenspunkte(anfangsLebenspunkte);
		 setMonsterSchaden(anfangsSchaden);
		 setTrankVorhanden(initialisierungTrankVorhanden);
		 setPosX(anfangsX);
		 setPosY(anfangsY);
		 
	 }
		
	//Keine setter-Methode f�r die Monster-ID, da diese nicht ver�ndert werden soll
	
	//getter-Methode f�r die Monster-ID
	public static int getSpielerID(){
		return monsterID;
	}
		
	//setter-Methode f�r die Lebenspunkte
	public static void setLebenspunkte(int neueLebenspunkte){
		lebenspunkte = neueLebenspunkte;
	}
		
	//getter-Methode f�r die Lebenspunkte
	public static int getLebenspunkte(){
			return lebenspunkte;
	}
		
	//setter-Methode f�r den Schaden, den das Monster verursacht
	public static void setMonsterSchaden (int neumonsterSchaden){
		monsterSchaden = neumonsterSchaden;
	}
		
	//getter-Methode f�r den Schaden, den das Monster verursacht
	public static int getMonsterSchaden(){
		return monsterSchaden;
	}
		
	//setter-Methode, ob das Monster einen Trank bei sich tr�gt
	public static void setTrankVorhanden(boolean neuTrankVorhanden){
		trankVorhanden = neuTrankVorhanden;
	}
		
	//getter-Methode f�r die Abfrage, ob das Monster einen Trank bei sich hat
	public static boolean getTrankVorhanden(){
		return trankVorhanden;
	}
		
	//setter-Methode f�r die x-Koordinate des Monsters
	public static void setPosX(int neuPosX){
		posX=neuPosX;
	}
		
	//getter-Methode f�r die x-Koordinate des Monsters
	public static int getPosX(){
		return posX;
	}
		
	//setter-Methode f�r die y-Koordinate des Monsters
	public static void setPosY(int neuPosY){
			posY=neuPosY;
	}
		
	//getter-Methode f�r die y-Koordinate des Monsters
	public static  int getPosY(){
		return posY;
	}

}
