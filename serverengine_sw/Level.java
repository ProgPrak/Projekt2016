package serverengine_sw;

import java.io.IOException;

public class Level {

	public static int ID;
	public static int [][] Inhalt;

	public Level(int id, int [][]level) throws IOException {
		 ID = id;
		 Inhalt = level;
	 }
	
	//kein Setter f�r die Level-ID ben�tigt, da diese nicht mehr ver�ndert werden soll
	
	//getter-Methode f�r die Level-ID
	public static int getID(){
		return ID;
	}
	
	//setter-Methode, um bestimmte Felder im Level zu ver�ndern
	public static void setInhalt(int x, int y, int inhalt){
		Inhalt[x][y] = inhalt;
	}
	
	//Hilfsmethode, sp�ter wieder l�schen, Array voller 0en f�llen
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
	
	//L�nge des Arrays in x-Richtung bestimmen
	public int getLaengeX(){
		return Inhalt.length;
	}
	
	//L�nge des Arrays in y-Richtung bestimmen
	public int getLaengeY(){
		return Inhalt[0].length;
	}
}
