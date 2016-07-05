package map;


public class Generator {
	int[][] Tile1Storage; //Speichert Positionen aller Bodentiles. Ist fuer die Verteilung der sonstigen Elemente wichtig.
	int AnzahlMonster;
	int AnzahlMonsterHidden;
	int AnzahlHeiltraenke;
	int[][] Map; //Die Map
	int letzteRichtung;
	int naechsteRichtung;
	int MaxBoden; // Max Anzahl an Bodentiles
	int CurrentBoden;
	int[] Position;
	int xPos; //GenerierStarpos x
	int yPos; //GenerierStarpos y
	boolean[] ExtraSchritt; //Habe urspruenglich versucht damit einen funktionierenden Alg. zu machen, ist aber letztendlich
	                        //nicht noetig. Da jedoch zu viele Methoden diese Variable benutzen bleibt sie sicherheitshalber drin
	int Hoehe; //Maphoehe
	int Breite; //Mapbreite
	final int[] moeglicheRichtungen = {0,1,2,3}; //0=Links; 1=Rechts; 2=Hoch; 3=Runter
	int[] erlaubteRichtungen ; //Man soll nicht direkt um 180 zurueck gehen, daher 1 Element weniger als moegliche Richtungen
	int[] erlaubteRichtungenAdv; //Falls man gegen die Grenze oder ein Pufferelement laufen wuerde, wird die Richtung rausgenommen
	int erlaubteRichtungenAdvLaenge;
	public Generator(int NHoehe, int NBreite, int Monster, int HMonster, int Traenke){
		ExtraSchritt = new boolean[4];
		for (int i=0;i<4;i++){
			this.ExtraSchritt[i]=false;
		}
		AnzahlMonster=Monster;
		AnzahlMonsterHidden=HMonster;
		AnzahlHeiltraenke=Traenke;
		erlaubteRichtungen=new int[3];
		erlaubteRichtungenAdv=new int[3];
		erlaubteRichtungenAdvLaenge=0;
		letzteRichtung=2;
		naechsteRichtung=3;
		Breite=NBreite;
		Hoehe=NHoehe;
		CurrentBoden=0;
		MaxBoden= (int) Math.round(Hoehe*Breite*0.35);
		Tile1Storage=new int[Hoehe*Breite][2];
		Map = new int [Breite][Hoehe];
		Position = new int [2];
		xPos=1;
		yPos=1;
		Position[0]=yPos;
		Position[1]=xPos;
		for (int i=0;i<this.Hoehe;i++){ //Map wird mit 0 gefuellt
			for (int j=0;j<this.Breite;j++){
				Map[i][j]=0;
			}
		}
		int i1=0;
		int j1=0;
		for (int i=1;i<this.Hoehe-1;i++){ //Pufferelemente werden generiert, damit Gaenge nur 1 Tile breit sind
			if (i1>0){
				for (int j=1;j<this.Breite-1;j++){
					if (j1>0){
						Map[i][j]=8;
						j1=0;
					}
					else j1++;
				}
				i1=0;
			}
			else i1++;
		}
	}
	public int gib180 (int direction){ //gibt die umgekehrte Richtung zurueck
		int ergebnis=0;
		switch (direction) {
		case 0: ergebnis= 1;
		break;
		case 1: ergebnis= 0;
		break;
		case 2: ergebnis= 3;
		break;
		case 3: ergebnis= 2;
		break;
		}
		return ergebnis;
	}
	public boolean testForPuffer(int direction){ //testet ob man gegen Puffer laufen wuerde
		boolean ergebnis=false;
		switch (direction){
		
		case 0:if (this.Map[this.Position[0]][this.Position[1]-1]==8) ergebnis=true;
			break;
		case 1:if (this.Map[this.Position[0]][this.Position[1]+1]==8) ergebnis=true;
			break;
		case 2:if (this.Map[this.Position[0]-1][this.Position[1]]==8) ergebnis=true;
			break;
		case 3:if (this.Map[this.Position[0]+1][this.Position[1]]==8) ergebnis=true;
			break;
		}
		return ergebnis;
	}
	public void breakWall (int direction){ //ersetzt Wand mit Boden
		switch (direction){
		case 0: if (this.Map[this.Position[0]][this.Position[1]-1]==0){
			this.CurrentBoden+=1;
			this.Map[this.Position[0]][this.Position[1]-1]=1;
		}
		this.Position[1]-=1;
		this.letzteRichtung=0;
		break;
		case 1: if (this.Map[this.Position[0]][this.Position[1]+1]==0){
			this.CurrentBoden+=1;
			this.Map[this.Position[0]][this.Position[1]+1]=1;
		}
		this.Position[1]+=1;
		this.letzteRichtung=1;
		break;
		case 2: if (this.Map[this.Position[0]-1][this.Position[1]]==0) {
			this.CurrentBoden+=1;
			this.Map[this.Position[0]-1][this.Position[1]]=1;
		}
		this.Position[0]-=1;
		this.letzteRichtung=2;
		break;
		case 3: if (this.Map[this.Position[0]+1][this.Position[1]]==0) {
			this.CurrentBoden+=1;
			this.Map[this.Position[0]+1][this.Position[1]]=1;
		}
		this.Position[0]+=1;
		this.letzteRichtung=3;
		break;
		}
	}
	public boolean testForBorder (int direction){ //testet ob man gegen die Grenze gehen wuerde
		boolean result=false;
		switch (direction){
		case 0:if (this.Position[1]==1) result = true;
		break;
		case 1:if (this.Position[1]==this.Breite-2) result = true;
		break;
		case 2:if (this.Position[0]==1) result = true;
		break;
		case 3:if (this.Position[0]==this.Hoehe-2) result = true;
		break;
		}
		
		return result;
	}
	public void Bewege (int direction) { //Falls man nicht gegen die Grenze oder einen Puffer lauft soll die Wand zerstoert werden
		if (this.testForBorder(direction)==true) this.ExtraSchritt[direction]=true;
		else if(this.testForPuffer(direction)==false)this.breakWall(direction);	
	}
	public void GenerierSchritt (int direction) {
		this.Bewege(direction);
		this.Bewege(direction);
		if (this.ExtraSchritt[direction]==true){
			this.ExtraSchritt[direction]=false;
			this.Bewege(direction);
		}
	}
	public void GeneriereBasicMap() { //generiert Map mit Boeden und Wanden
		double nextDirDouble;
		int j;
		do{
			j=0;
			this.erlaubteRichtungenAdvLaenge=0;
			switch (this.letzteRichtung){
			case 0: 
				this.erlaubteRichtungen[0]=0;
				this.erlaubteRichtungen[1]=2;
				this.erlaubteRichtungen[2]=3;
				break;
			case 1: 
				this.erlaubteRichtungen[0]=1;
				this.erlaubteRichtungen[1]=2;
				this.erlaubteRichtungen[2]=3;
				break;
			case 2: 
				this.erlaubteRichtungen[0]=2;
				this.erlaubteRichtungen[1]=1;
				this.erlaubteRichtungen[2]=0;
				break;
			case 3: 
				this.erlaubteRichtungen[0]=3;
				this.erlaubteRichtungen[1]=1;
				this.erlaubteRichtungen[2]=0;
				break;
			}
			for (int i=2; i>=0;i--)
			{
				if (this.testForBorder(this.erlaubteRichtungen[i])==false) {
					this.erlaubteRichtungenAdv[j]=this.erlaubteRichtungen[i];
					j++;
					this.erlaubteRichtungenAdvLaenge+=1;
				}
				else {
					this.erlaubteRichtungenAdv[j]=this.gib180(this.erlaubteRichtungen[i]);
							j++;
					this.erlaubteRichtungenAdvLaenge+=1;
				}
			}
			nextDirDouble=Math.random()*this.erlaubteRichtungenAdvLaenge;
			this.GenerierSchritt(erlaubteRichtungenAdv[(int) nextDirDouble]);
			for (int i=0;i<3;i++){
				this.erlaubteRichtungenAdv[i]=9;
			}
		}
		while (CurrentBoden<MaxBoden+2);
	}
	public void bereinigeVonPuffertiles(){ //ersetzt Puffer durch Waende
		for (int i=0;i<this.Hoehe;i++){
			for (int j=0;j<this.Breite;j++){
				if(this.Map[i][j]==8) this.Map[i][j]=0;
			}
		}
	}
	public void sammle1Tiles (){ //Merkt sich die Koordinaten aller Bodentiles
		int x=0;
		for (int i=0;i<this.Hoehe;i++){
			for (int j=0;j<this.Breite;j++){
				if (this.Map[i][j]==1){
					this.Tile1Storage[x][0]=i;
					this.Tile1Storage[x][1]=j;
					x++;
				}
			}
		}
	}
	public void setzeSonstigeSpielelemente() { //Teilt Monster, Schluessel, Tranke und Tueren zu, wobei Eingang = Startpos. des Spielers
		this.sammle1Tiles();
		int[] MonsterPos = new int[this.AnzahlMonster];
		int[] HiddenMonsterPos= new int[this.AnzahlMonsterHidden];
		int[] HeiltraenkePos= new int[this.AnzahlHeiltraenke];
		int SchluesselPos;
		int ExitPos;
		int EntryPos;
		double SPD=Math.random()*this.MaxBoden;
		double ExPD=Math.random()*this.MaxBoden;
		double EnPD=Math.random()*this.MaxBoden;
		SchluesselPos=(int) SPD;
		ExitPos=(int) ExPD;
		if (ExitPos==SchluesselPos){
			if (ExitPos>1) ExitPos-=1;
			else ExitPos+=1;
		}
		EntryPos=(int)EnPD;
		if(EntryPos==ExitPos||EntryPos==SchluesselPos) EntryPos= Math.abs(ExitPos-SchluesselPos);
		for (int i=0;i<this.AnzahlMonster;i++){
			double randomPosD;
			int randomPos;
			boolean MonsterOverlapping=false;
			do{
				randomPosD=Math.random()*this.MaxBoden;
				randomPos = (int) randomPosD;
				for (int j=0;j<i;j++){
					if(MonsterPos[j]==randomPos) MonsterOverlapping=true;
				}
			}while (randomPos==ExitPos||randomPos==EntryPos||randomPos==SchluesselPos||MonsterOverlapping==true);
			MonsterPos[i]=randomPos;
		}
		for (int i=0;i<this.AnzahlMonsterHidden;i++){
			double randomPosD;
			int randomPos;
			boolean MonsterOverlapping=false;
			boolean HiddenMonsterOverlapping=false;
			do{
				randomPosD=Math.random()*this.MaxBoden;
				randomPos = (int) randomPosD;
				for (int j=0;j<AnzahlMonster;j++){
					if(MonsterPos[j]==randomPos) MonsterOverlapping=true;
				}
				for (int j=0;j<i;j++){
					if(HiddenMonsterPos[j]==randomPos) HiddenMonsterOverlapping=true;
				}
			}while (randomPos==ExitPos||randomPos==EntryPos||randomPos==SchluesselPos||MonsterOverlapping==true||HiddenMonsterOverlapping==true);
			MonsterOverlapping=false;
			HiddenMonsterPos[i]=randomPos;
		}
		for (int i=0;i<this.AnzahlHeiltraenke;i++){
			double randomPosD;
			int randomPos;
			boolean MonsterOverlapping=false;
			boolean HiddenMonsterOverlapping=false;
			boolean TrankOverlapping=false;
			do{
				randomPosD=Math.random()*this.MaxBoden;
				randomPos = (int) randomPosD;
				for (int j=0;j<AnzahlMonster;j++){
					if(MonsterPos[j]==randomPos) MonsterOverlapping=true;
				}
				for (int j=0;j<AnzahlMonsterHidden;j++){
					if(HiddenMonsterPos[j]==randomPos) HiddenMonsterOverlapping=true;
				}
				for (int j=0;j<i;j++){
					if(HeiltraenkePos[j]==randomPos) TrankOverlapping=true;
				}
			}while (randomPos==ExitPos||randomPos==EntryPos||randomPos==SchluesselPos||MonsterOverlapping==true||HiddenMonsterOverlapping==true||TrankOverlapping==true);
			MonsterOverlapping=false;
			HiddenMonsterOverlapping=false;
			HeiltraenkePos[i]=randomPos;
		}
		int x;
		x=EntryPos;
		this.Map[this.Tile1Storage[x+1][0]][this.Tile1Storage[x+1][1]]=4;
		x=ExitPos;
		this.Map[this.Tile1Storage[x+1][0]][this.Tile1Storage[x+1][1]]=3;
		x=SchluesselPos;
		this.Map[this.Tile1Storage[x+1][0]][this.Tile1Storage[x+1][1]]=2;
		for (int i=0;i<MonsterPos.length;i++){
			x=MonsterPos[i];
			this.Map[this.Tile1Storage[x+1][0]][this.Tile1Storage[x+1][1]]=5;
		}
		for (int i=0;i<HiddenMonsterPos.length;i++){
			x=HiddenMonsterPos[i];
			this.Map[this.Tile1Storage[x+1][0]][this.Tile1Storage[x+1][1]]=6;
		}
		for (int i=0;i<HeiltraenkePos.length;i++){
			x=HeiltraenkePos[i];
			this.Map[this.Tile1Storage[x+1][0]][this.Tile1Storage[x+1][1]]=7;
		}
		/*
		 * 0=Wand
		 * 1=Boden
		 * 2=Key   //wird spaeter wieder rausgenommen
		 * 3=Exit
		 * 4=Entry
		 * 5=Monster(von Anfang an da)
		 * 6=Monster(kommt wenn Key genommen wird)
		 * 7=Trank
		 */

	}
	public void printMap(){  //nur zu Testzwecken
		for (int i=0;i<this.Hoehe;i++){
			for (int j=0;j<this.Breite;j++){
				System.out.print(this.Map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	public int[][] gibMap(){
		int[][] ergebnis = new int[this.Hoehe][this.Breite];
		for (int i=0;i<this.Hoehe;i++){
			for (int j=0;j<this.Breite;j++){
				ergebnis[i][j]=this.Map[i][j];
			}
		}
		return ergebnis;
		
	}
	public int[][] floodfill (){
		int[][] ergebnis = new int[this.Hoehe][this.Breite];
		this.GeneriereBasicMap();
		this.bereinigeVonPuffertiles();
		this.setzeSonstigeSpielelemente();
		ergebnis=this.gibMap();
		return ergebnis;
		
	}
	public static void main (String[]args) {
		Generator Test= new Generator(20,20,1,1,2);
		Test.floodfill();
		Test.printMap();
	}

}
