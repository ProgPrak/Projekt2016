package shared;

import map.*;

public class Map {
	private Tile[][] Struktur;
	private int[][] TileTypes;
	private Generator Generator;
	private int Hoehe;
	private int Breite;
	private int Schluesselwaechter; //welches Monster hat den Schluessel
	private int Schluesselwaechterdurchlauf; //zur Implementierung benoetigt
	
	
	public Map (int CHoehe, int CBreite, int AnzahlMonster, int AnzahlHiddenMonster, int AnzahlTraenke){
		Hoehe=CHoehe;
		Breite=CBreite;
		Struktur = new Tile[Hoehe][Breite];
		Schluesselwaechterdurchlauf=0;
		double Waechter = Math.random()*AnzahlMonster;
		Schluesselwaechter = (int) Waechter;
		generiereTiles();
		TileTypes = new int[Hoehe][Breite];
		Generator = new Generator (Hoehe,Breite, AnzahlMonster, AnzahlHiddenMonster, AnzahlTraenke);
		TileTypes=Generator.floodfill();
		teileTilesZu(TileTypes);
		
	}
	
	public void generiereTiles(){
		for (int i=0;i<this.Hoehe;i++){
			for (int j=0;j<this.Breite;j++){
				Struktur[i][j]= new Tile();
				Struktur[i][j].Koordinaten[0]=i;
				Struktur[i][j].Koordinaten[1]=j;
			}
		}
	}
	
	public void teileTilesZu(int[][] TileyTypes){
		int x=0;
		for (int i=0;i<this.Hoehe;i++){
			for (int j=0;j<this.Breite;j++){
				x=TileTypes[i][j];
				switch (x){
				case 1: this.Struktur[i][j].surfaceType=x;
				break;
				case 0: this.Struktur[i][j].surfaceType=x;
				break;
				case 2: this.Struktur[i][j].surfaceType=1;
				break;
				case 3: this.Struktur[i][j].surfaceType=x;
				break;
				case 4: this.Struktur[i][j].surfaceType=x;
				break;
				case 5: this.Struktur[i][j].spawneMonster(1);
				this.Struktur[i][j].surfaceType=1;
				if (this.Schluesselwaechter==this.Schluesselwaechterdurchlauf) {
					this.Struktur[i][j].MonsterHatSchluessel=true;
					this.Schluesselwaechterdurchlauf+=1;
				}
				else this.Schluesselwaechterdurchlauf+=1;
				break;
				case 6: this.Struktur[i][j].spawneMonster(2);
				this.Struktur[i][j].surfaceType=1;
				break;
				case 7: this.Struktur[i][j].anzahlTraenke+=1;
				this.Struktur[i][j].surfaceType=1;
				break;
				}
			}
		}
	}
	public Tile[][] gibMap(){
		return Struktur;
	}
	
	public void testePflichtenHeftAnforderungen() {//nur zu Testzwecken
		System.out.println("Tiletypverteilung:");
		this.printTileTypes(); //printed die Typen der jeweiligen Tiles. Siehe Klasse "Tile" f�r genaue Infos
		System.out.println();
		System.out.println();
		System.out.println("Monsterpositionen:");
		this.printMonsterPositionen(); //zeigt Monsterpositionen. 1 f�r Typ 1, 2 f�r Typ 2
		System.out.println();
		System.out.println();
		System.out.println("Waechterposition:");
		this.printWaechterPosition(); //zeigt wo das Monster mit dem Schluessel ist
		System.out.println();
		System.out.println();
		System.out.println("Trankpositionen:");
		this.printTrankPositionen(); //zeigt Positionen der Traenke
		
	}
	public void printWaechterPosition(){ //nur zu Testzwecken
		for (int i=0;i<this.Hoehe;i++){
			for (int j=0;j<this.Breite;j++){
				if (this.Struktur[i][j].MonsterHatSchluessel==true) System.out.print(1);
				else System.out.print(0);
			}
			System.out.println();
		}
	}
	
	public void printMonsterPositionen(){//nur zu Testzwecken
		for (int i=0;i<this.Hoehe;i++){
			for (int j=0;j<this.Breite;j++){
				if (this.Struktur[i][j].zuSpawnendeMonster==1) System.out.print(1);
				else if (this.Struktur[i][j].zuSpawnendeMonster==2) System.out.print(2);
				else System.out.print(0);
			}
			System.out.println();
		}
	}
	
	public void printTileTypes(){ //nur zu Testzwecken
		for (int i=0;i<this.Hoehe;i++){
			for (int j=0;j<this.Breite;j++){
				System.out.print(this.Struktur[i][j].surfaceType);
			}System.out.println();
		}
	}
	public void printTrankPositionen(){ //nur zu Testzwecken
		for (int i=0;i<this.Hoehe;i++){
			for (int j=0;j<this.Breite;j++){
				if (this.Struktur[i][j].anzahlTraenke>0) System.out.print(1);
				else System.out.print(0);
			}
			System.out.println();
		}
	}

}