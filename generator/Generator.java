package pp2016.team20.server.map;


public class Generator {
	int[][] Tile1Storage; //Speichert Positionen aller Bodentiles. Ist fuer die Verteilung der sonstigen Elemente wichtig.
	int[][] schrittHistory;
	int MaxBoden;
	boolean keineMoeglichenRichtungen;
	int schrittCounter;
	int AnzahlMonster;
	int AnzahlMonsterHidden;
	int AnzahlHeiltraenke;
	int[][] Map; //Die Map
	int naechsteRichtung;
	int[] Position;
	int xPos; //GenerierStarpos x
	int yPos; //GenerierStarpos y
	int Hoehe; //Maphoehe
	int Breite; //Mapbreite
	final int[] moeglicheRichtungen = {0,1,2,3}; //0=Links; 1=Rechts; 2=Hoch; 3=Runter
	int[] erlaubteRichtungen ; //Man soll nicht direkt um 180 zurueck gehen, daher 1 Element weniger als moegliche Richtungen
	int[] erlaubteRichtungenAdv; //Falls man gegen die Grenze oder ein Pufferelement laufen wuerde, wird die Richtung rausgenommen
	int erlaubteRichtungenAdvLaenge;
	public Generator(int NHoehe, int NBreite, int Monster, int HMonster, int Traenke){
		Breite=NBreite;
		Hoehe=NHoehe;
		schrittHistory = new int[Hoehe*Breite][2];
		for (int i=0;i<Hoehe*Breite;i++){
			schrittHistory[i][0]=0;
			schrittHistory[i][1]=0;
		}
		schrittCounter=0;
		keineMoeglichenRichtungen=false;
		MaxBoden=0;
		AnzahlMonster=Monster;
		AnzahlMonsterHidden=HMonster;
		AnzahlHeiltraenke=Traenke;
		erlaubteRichtungen=new int[3];
		erlaubteRichtungenAdv=new int[3];
		erlaubteRichtungenAdvLaenge=0;
		naechsteRichtung=3;
		Tile1Storage=new int[Hoehe*Breite][2];
		Map = new int [Breite][Hoehe];
		Position = new int [2];
		xPos=2;
		yPos=2;
		Position[0]=yPos;
		Position[1]=xPos;
		for (int i=0;i<this.Hoehe;i++){ //Map wird mit 0 gefuellt
			for (int j=0;j<this.Breite;j++){
				Map[i][j]=0;
			}
		}
		Map[2][2]=1;
	}

	public void documentSchritt (){
		this.schrittHistory[this.schrittCounter][0]=this.Position[0];
		this.schrittHistory[this.schrittCounter][1]=this.Position[1];
		this.schrittCounter+=1;
	}
	public void breakWall (int direction){ //ersetzt Wand mit Boden
		switch (direction){
		case 0: if (this.Map[this.Position[0]][this.Position[1]-1]==0){
			this.Map[this.Position[0]][this.Position[1]-1]=1;
		}
		this.Position[1]-=1;
		
		break;
		case 1: if (this.Map[this.Position[0]][this.Position[1]+1]==0){
			this.Map[this.Position[0]][this.Position[1]+1]=1;
		}
		this.Position[1]+=1;
		break;
		case 2: if (this.Map[this.Position[0]-1][this.Position[1]]==0) {
			this.Map[this.Position[0]-1][this.Position[1]]=1;
		}
		this.Position[0]-=1;
		break;
		case 3: if (this.Map[this.Position[0]+1][this.Position[1]]==0) {
			this.Map[this.Position[0]+1][this.Position[1]]=1;
		}
		this.Position[0]+=1;
		break;
		}
	}
	public boolean testForBorder (int direction){ //testet ob man gegen die Grenze gehen wuerde
		boolean result=false;
		switch (direction){
		case 0:if (this.Position[1]==2) result = true;
		break;
		case 1:if (this.Position[1]==this.Breite-3) result = true;
		break;
		case 2:if (this.Position[0]==2) result = true;
		break;
		case 3:if (this.Position[0]==this.Hoehe-3) result = true;
		break;
		}
		
		return result;
	}
	public boolean testForRoad (int direction){
		boolean result=false;
		switch (direction){
		case 0:if (this.Map[this.Position[0]][this.Position[1]-1]==1) result = true;
		break;
		case 1:if (this.Map[this.Position[0]][this.Position[1]+1]==1) result = true;
		break;
		case 2:if (this.Map[this.Position[0]-1][this.Position[1]]==1) result = true;
		break;
		case 3:if (this.Map[this.Position[0]+1][this.Position[1]]==1) result = true;
		break;
		}
		
		return result;
	}
	public boolean testForRoadConflicts (int direction){
		boolean result=false;
		switch (direction){
		case 0:if (this.Map[this.Position[0]][this.Position[1]-2]==1||this.Map[this.Position[0]+1][this.Position[1]-1]==1||this.Map[this.Position[0]-1][this.Position[1]-1]==1) result = true;
		break;
		case 1:if (this.Map[this.Position[0]][this.Position[1]+2]==1||this.Map[this.Position[0]+1][this.Position[1]+1]==1||this.Map[this.Position[0]-1][this.Position[1]+1]==1) result = true;
		break;
		case 2:if (this.Map[this.Position[0]-2][this.Position[1]]==1||this.Map[this.Position[0]-1][this.Position[1]+1]==1||this.Map[this.Position[0]-1][this.Position[1]-1]==1) result = true;
		break;
		case 3:if (this.Map[this.Position[0]+2][this.Position[1]]==1||this.Map[this.Position[0]+1][this.Position[1]+1]==1||this.Map[this.Position[0]+1][this.Position[1]-1]==1) result = true;
		break;
		}
		
		return result;
	}
	public void goBack (){
		this.Position[0]=this.schrittHistory[this.schrittCounter-1][0];
		this.Position[1]=this.schrittHistory[this.schrittCounter-1][1];
		this.schrittCounter-=1;
	}
	public void Bewege (int direction) { //Falls man nicht gegen die Grenze oder einen Puffer lauft soll die Wand zerstoert werden
		
		
			this.breakWall(direction);	
			this.documentSchritt();
		
	}
	/*public void GenerierSchritt (int direction) {
		this.Bewege(direction);
		this.Bewege(direction);
		if (this.ExtraSchritt[direction]==true){
			this.ExtraSchritt[direction]=false;
			this.Bewege(direction);
		}
	}*/
	public void GeneriereBasicMap() { //generiert Map mit Boeden und Wanden
		double nextDirDouble;
		do{
			if (this.naechsteRichtung==3){
				this.Bewege(3);
				this.erlaubteRichtungenAdv[0]=3;
				this.erlaubteRichtungen[0]=3;
				/*System.out.println();
				System.out.println();
				System.out.println(" erlaubteRichtungenAdv[0]= "+this.erlaubteRichtungenAdv[0]+" erlaubteRichtungenAdv[1]= "+this.erlaubteRichtungenAdv[1]+" erlaubteRichtungenAdv[2]= "+this.erlaubteRichtungenAdv[2]);
				System.out.println();
				System.out.println(" erlaubteRichtungen[0]= "+this.erlaubteRichtungen[0]+" erlaubteRichtungen[1]= "+this.erlaubteRichtungen[1]+" erlaubteRichtungen[2]= "+this.erlaubteRichtungen[2]);
				System.out.println();*/
				for (int i=0;i<3;i++){
					this.erlaubteRichtungenAdv[i]=9;
				}
				this.naechsteRichtung=0;
			}
			else{
				this.erlaubteRichtungenAdvLaenge=0;
				int i1=0;
				for (int i=0;i<4;i++){
					if (this.testForRoad(i)==false) {
						this.erlaubteRichtungen[i1]=i;
						i1++;
					}
				}
				i1=0;
				for (int i=0; i<3;i++)
				{
					if (this.testForBorder(this.erlaubteRichtungen[i])==false&&this.testForRoadConflicts(this.erlaubteRichtungen[i])==false){
						this.erlaubteRichtungenAdv[i1]=this.erlaubteRichtungen[i];
						i1++;
						this.erlaubteRichtungenAdvLaenge+=1;
					}
					
				}
				if (this.erlaubteRichtungenAdvLaenge==0&&this.schrittCounter==0){
					this.keineMoeglichenRichtungen=true;
				}
				else if (this.erlaubteRichtungenAdvLaenge==0){
					this.goBack();
				}
				else {
					nextDirDouble=Math.random()*this.erlaubteRichtungenAdvLaenge;
					this.Bewege(erlaubteRichtungenAdv[(int) nextDirDouble]);
					/*System.out.println();
					System.out.println();
					System.out.println(" erlaubteRichtungenAdv[0]= "+this.erlaubteRichtungenAdv[0]+" erlaubteRichtungenAdv[1]= "+this.erlaubteRichtungenAdv[1]+" erlaubteRichtungenAdv[2]= "+this.erlaubteRichtungenAdv[2]);
					System.out.println();
					System.out.println(" erlaubteRichtungen[0]= "+this.erlaubteRichtungen[0]+" erlaubteRichtungen[1]= "+this.erlaubteRichtungen[1]+" erlaubteRichtungen[2]= "+this.erlaubteRichtungen[2]);
					System.out.println();*/
					for (int i=0;i<3;i++){
						this.erlaubteRichtungenAdv[i]=9;
					}
				}
			}
			
			//this.printMap();
			
		}
		while (this.keineMoeglichenRichtungen==false);
	}
	public void sammle1Tiles (){ //Merkt sich die Koordinaten aller Bodentiles
		int x=0;
		for (int i=0;i<this.Hoehe;i++){
			for (int j=0;j<this.Breite;j++){
				if (this.Map[i][j]==1){
					this.Tile1Storage[x][0]=i;
					this.Tile1Storage[x][1]=j;
					x++;
					this.MaxBoden+=1;
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
		this.setzeSonstigeSpielelemente();
		ergebnis=this.gibMap();
		return ergebnis;
		
	}
	/*public static void main (String[]args) {
		Generator Test= new Generator(100,100,1,1,2);
		Test.floodfill();
		Test.printMap();
	}*/

}
