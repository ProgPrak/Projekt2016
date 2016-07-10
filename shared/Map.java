
package shared;
 
import generator.*;
 
public class Map {
<<<<<<< HEAD
    public int[][] GuiArray; //Array was die GUI zum painten braucht
    public Tile[][] Struktur;
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
        GuiArray = new int[this.Hoehe][this.Breite];
        initialisiereGuiArray();
        wandleZuGuiArrayum();
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
     
    public void initialisiereGuiArray(){
        for (int i=0;i<this.Hoehe;i++){
            for (int j=0;j<this.Breite;j++){
                GuiArray[i][j]= 0;
            }
        }
    }
     
    public void wandleZuGuiArrayum(){
        for (int i=0; i<this.Hoehe;i++){
            for (int  j=0;j<this.Breite;j++){
                if (this.Struktur[i][j].SpielerDrauf==true) this.GuiArray[i][j]=4;
                else if (this.Struktur[i][j].anzahlTraenke>0) this.GuiArray[i][j]=2;
                else {
                    int a = this.Struktur[i][j].surfaceType;
                    switch (a){
                    case 5: this.GuiArray[i][j]=3;
                    break;
                    case 0: this.GuiArray[i][j]=0;
                    break;
                    case 1:this.GuiArray[i][j]=1;
                    break;
                    case 2: this.GuiArray[i][j]=7;
                    break;
                    case 3: this.GuiArray[i][j]=6;
                    break;
                    case 4: this.GuiArray[i][j]=6;
                    break;
                    }
                }
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
                case 2: this.Struktur[i][j].surfaceType=5;
                break;
                case 3: this.Struktur[i][j].surfaceType=x;
                break;
                case 4: this.Struktur[i][j].surfaceType=x;
                this.Struktur[i][j].SpielerDrauf=true;
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
        this.printTileTypes(); //printed die Typen der jeweiligen Tiles. Siehe Klasse "Tile" für genaue Infos
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("GUI Array:");
        this.printGuiArray();
        System.out.println();
        System.out.println();
        System.out.println("Monsterpositionen:");
        this.printMonsterPositionen(); //zeigt Monsterpositionen. 1 für Typ 1, 2 für Typ 2
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
     
    public void printGuiArray(){
        for (int i=0;i<this.Hoehe;i++){
            for (int j=0; j<this.Breite;j++){
                System.out.print(this.GuiArray[i][j]);
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
=======
	public int[][] GuiArray; //Array was die GUI zum painten braucht
	public Tile[][] Struktur;
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
		GuiArray = new int[this.Hoehe][this.Breite];
		this.initialisiereGuiArray();
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
	
	public void initialisiereGuiArray(){
		for (int i=0;i<this.Hoehe;i++){
			for (int j=0;j<this.Breite;j++){
				GuiArray[i][j]= 0;
			}
		}
	}
	
	public void wandleZuGuiArrayum(){
		for (int i=0; i<this.Hoehe;i++){
			for (int  j=0;j<this.Breite;j++){
				if (this.Struktur[i][j].SpielerDrauf==true) this.GuiArray[i][j]=4;
				else if (this.Struktur[i][j].anzahlTraenke>0) this.GuiArray[i][j]=2;
				else {
					int a = this.Struktur[i][j].surfaceType;
					switch (a){
					case 5: this.GuiArray[i][j]=3;
					break;
					case 0: this.GuiArray[i][j]=0;
					break;
					case 1:this.GuiArray[i][j]=1;
					break;
					case 2: this.GuiArray[i][j]=7;
					break;
					case 3: this.GuiArray[i][j]=6;
					break;
					case 4: this.GuiArray[i][j]=6;
					break;
					}
				}
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
				case 2: this.Struktur[i][j].surfaceType=5;
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
		this.printTileTypes(); //printed die Typen der jeweiligen Tiles. Siehe Klasse "Tile" für genaue Infos
		System.out.println();
		System.out.println();
		System.out.println("Monsterpositionen:");
		this.printMonsterPositionen(); //zeigt Monsterpositionen. 1 für Typ 1, 2 für Typ 2
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
>>>>>>> branch 'master' of https://github.com/ProgPrak/Projekt2016.git
