package GUI;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class SpielFeld extends JPanel
{
	int malex = 20,maley = 20,bildx,bildy,startx=0,starty=0;
	  //private Image image;
	  GUImain fenster;
	  private Image boden,wand,tuerZu,tuerOffen,schluessel,heiltrank, monster1, monster2;
	  private boolean startpunktSpieler = false;
	  int[][] trankMap={
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	  };
	  int[][] dummyMap={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,1,1,1,1,0,1,1,1,0,0,0,0,0,1,1,1,0,0},
			  {0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,0},
			  {0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,1,0,1,0,0},
			  {0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,1,0,0},
			  {0,0,0,1,1,1,0,1,1,1,0,0,0,0,0,1,0,1,0,0},
			  {0,0,0,0,0,1,0,1,0,3,0,0,0,0,0,1,0,1,0,0},
			  {0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,1,0,1,0,0},
			  {0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,0},
			  {0,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,0,0},
			  {0,0,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,0},
			  {0,1,1,1,0,1,1,1,1,1,0,0,0,1,0,1,0,1,0,0},
			  {0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,1,0,0},
			  {0,1,0,0,0,0,0,1,0,1,1,1,1,1,1,1,0,1,0,0},
			  {0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0},
			  {0,1,1,1,1,1,1,4,1,1,0,1,0,1,1,1,1,1,0,0},
			  {0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0},
			  {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,1,0,1,0,0},
			  {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	  int[][] monsterMap={
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	  };
	  int[][] keyMap={
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	  };
	  // 0 = wand
	  // 1 = boden
	  // 2 = tuerOffen
	  // 3 = tuerZu
	  // 4 = Spieler
	  

	  public SpielFeld(GUImain fenster,int bildx,int bildy) 
	  {
		  this.fenster = fenster;
		 Toolkit tk = Toolkit.getDefaultToolkit();
		 this.bildx = bildx;
		 this.bildy = bildy;
	    //  image = tk.getImage("logo.jpg");
		  
		 boden = tk.getImage("img/boden.png");
		 wand = tk.getImage("img/wand.png");
		 tuerZu = tk.getImage("img/tuer.png");
		 tuerOffen = tk.getImage("img/tueroffen.png");
		 schluessel = tk.getImage("img/schluessel.png");
		 heiltrank = tk.getImage("img/trank.png");
		 monster1 = tk.getImage("img/drache1.png");
		 monster2 = tk.getImage("img/drache2.png");
		 this.requestFocus();
	     
	  }
	  
	  public int[][] getMap()
	  {
		  return dummyMap;
	  }

	    @Override
	   public void paintComponent(Graphics g) 
	    {
	    	
	    	super.paintComponent(g);
	    	if(((fenster.spieler.getYPos()-448)/32) > 0)starty=(fenster.spieler.getYPos()-448)/32+1;
	    	else
	    		{
	    			if(starty>1)starty-=1;
	    			else starty=0;
	    		}
	    	maleMap(g, 20,20,bildx,bildy, dummyMap,monsterMap,trankMap,startx,starty);
	    	
	    	/*if(fenster.spieler.getYPos() >=480)
	    	{
	    		this.setLocation(0, (fenster.spieler.getYPos()-480)/32);
	    		maley = fenster.spieler.getYPos()/32+2;
	    	}*/
	     
	    }

	    public void maleMap(Graphics g, int maleX, int maleY, int bildX, int bildY,int[][] dummyMap, int[][] monsterMap, int[][] trankMap, int startx, int starty) 
	    {
	    	this.startx = startx;
	    	this.starty = starty;
	    	
	    	for(int i = startx; i<maleX;i++)
	    	{
	    		for(int j = starty; j<maleY; j++)
	    		{
	    			if(dummyMap[i][j] == 1)
	    			{
	    				g.drawImage(boden, i*bildX, (j-starty)*bildY, bildX, bildY, this);
	    			}
	    			
	    			if(dummyMap[i][j] == 0)
	    			{
	    				g.drawImage(wand, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			
	    			if(dummyMap[i][j] == 2)
	    			{
	    				g.drawImage(tuerOffen, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			
	    			if(dummyMap[i][j] == 3)
	    			{
	    				g.drawImage(tuerZu, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			
	    			if(dummyMap[i][j] == 4 )
	    			{
	    				g.drawImage(boden, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    				if(startpunktSpieler == false){
	    					fenster.spieler.setPos(i*bildX, (j-starty)*bildY);
	    					startpunktSpieler = true;
	    				}
	    				//g.drawImage(fenster.spieler.getImage(), i*bildX, j*bildY, this);
	    				
	    			}
	    			if(monsterMap[i][j] == 1){
	    				g.drawImage(monster1, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    				g.setColor(Color.GREEN);
	    				g.fillRect(i*bildX, (j-starty)*bildY, bildX, bildY/6);
	    			}
	    			if(monsterMap[i][j] == 2){
	    				g.drawImage(monster2, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    				g.setColor(Color.GREEN);
	    				g.fillRect(i*bildX, (j-starty)*bildY, bildX, bildY/6);
	    			}
	    			if(trankMap[i][j] == 1)
	    			{
	    				g.drawImage(heiltrank, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    		}
	    	}
	    	
	    	g.setColor(Color.BLACK);	
	    	if(fenster.menu.getbKarte() == false)
	    	{
	    		for(int k = 0; k < 20; k++)
	    		{
	    			for(int l = 0; l<20; l++)
	    			{
	    				if(k*32 < fenster.spieler.getXPos()-32*2 || k*32 > fenster.spieler.getXPos()+32*2 || l*32 <fenster.spieler.getYPos()-32*2-getstarty() || l*32 > fenster.spieler.getYPos()+32*2-getstarty()) g.fillRect(k*32, l*32, 32, 32);
	    			}
	    		}
	    	}
	    	g.drawImage(fenster.spieler.getImage(), fenster.spieler.getXPos(), fenster.spieler.getYPos()-(starty*32),bildX,bildY, this);
			
	    	
	    	
		}

		public int[][] getMonsterMap() {
			// TODO Auto-generated method stub
			return monsterMap;
		}
	    public int[][] getTrankMap() {
			// TODO Auto-generated method stub
			return trankMap;
		}
	    
	    public void loescheTrank(int i , int j){
	    	trankMap[i][j] = 0;
	    }
	    
	    public void loescheMonster(int i , int j){
	    	monsterMap[i][j] = 0;
	    }
	    
	    public int getstarty()
	    {
	    	return starty*32;
	    }
	   
}
	 

