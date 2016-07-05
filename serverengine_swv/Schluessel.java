package Spielweltverwaltung;

import java.io.IOException;

public class Schluessel {
	
	public static int posX;
	public static int posY;
	public static boolean aufgehoben;
	
	public Schluessel(int anfangsX, int anfangsY) throws IOException {
		 setAufgehoben(false);
		 setPosX(anfangsX);
		 setPosY(anfangsY);
		 
	 }
	
	//setter-Methode, die den Zustand des Schl�ssels �ndert
	public static void setAufgehoben(boolean neuAufgehoben){
		aufgehoben=neuAufgehoben;
	}
	
	//getter-Methode f�r den Zustand des Schl�ssels
	public static boolean getAufgehoben(){
		return aufgehoben;
	}
	
	//setter-Methode f�r die x-Koordinate des Schl�ssels
	public static void setPosX(int neuPosX){
		posX=neuPosX;
	}
	
	//getter-Methode f�r die x-Koordinate des Schl�ssels
	public static int getPosX(){
		return posX;
	}
	
	//setter-Methode f�r die y-Koordinate des Schl�ssels
	public static void setPosY(int neuPosY){
		posY=neuPosY;
	}
	
	//getter-Methode f�r die y-Koordinate des Schl�ssels
	public static  int getPosY(){
		return posY;
	}

}
