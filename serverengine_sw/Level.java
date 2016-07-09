package serverengine_sw;

import java.io.IOException;

public class Level {

	public static int ID;
	public static int [][] Inhalt;

	public Level(int id, int [][]level) throws IOException {
		 ID = id;
		 Inhalt = level;
	 }
	
	//kein Setter für die Level-ID benötigt, da diese nicht mehr verändert werden soll
	
	//getter-Methode für die Level-ID
	public static int getID(){
		return ID;
	}
	
	//setter-Methode, um bestimmte Felder im Level zu verändern
	public static void setInhalt(int x, int y, int inhalt){
		Inhalt[x][y] = inhalt;
	}
	
	//Hilfsmethode, später wieder löschen, Array voller 0en füllen
	public static void setInhaltNull(){
		for(int i = 0; i<Inhalt.length;i++){
			for (int j  = 0;j<Inhalt[0].length;j++){
				Inhalt[j][i]=0;
			}
		}
	}
	
	//getter-Methode, um das gesamte Level auszulesen
	public static int [][] getKomplettesLevel(){
		return Inhalt;
	}
	
	//getter-Methode, um ein bestimmtes Feld auszulesen
	public static int getBestimmtenInhalt(int x, int y){
		return Inhalt[x][y];
	}
	
	//Methode um Level auf der Konsole auszugeben
	public void ausgabe(){
		for(int i=0;i<Inhalt.length;i++){
			for(int j = 0;j<Inhalt[0].length;j++){
				System.out.print(Inhalt[j][i]);
			}
			System.out.println();
		}
	}
	
	//Länge des Arrays in x-Richtung bestimmen
	public int getLaengeX(){
		return Inhalt.length;
	}
	
	//Länge des Arrays in y-Richtung bestimmen
	public int getLaengeY(){
		return Inhalt[0].length;
	}
}
