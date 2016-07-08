package GUI;

import javax.swing.*;

import datenstruktur.Schluessel;

import java.awt.*;

@SuppressWarnings("serial")
public class SpielFeld extends JPanel
{
	int malex = 20,maley = 20,bildx,bildy,startx=0,starty=0;
	  //private Image image;
	  GUImain fenster;
	  private Image boden,wand,tuerZu,tuerOffen,schluessel,heiltrank, monster1;
	  private boolean startpunktSpieler = false;
	  
	  int[][] dummyMap={
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,0,1,5,1,1,0,1,1,1,0,0,0,0,0,1,1,1,0,0},
			  {0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,0},
			  {0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,5,0,1,0,0},
			  {0,0,0,2,0,0,0,1,0,1,0,0,0,0,0,1,0,5,0,0},
			  {0,0,0,1,1,1,0,1,1,1,0,0,0,0,0,1,0,1,0,0},
			  {0,0,0,0,0,1,0,1,0,7,0,0,0,0,0,1,0,1,0,0},
			  {0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,1,0,1,0,0},
			  {0,1,0,5,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,0},
			  {0,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,0,0},
			  {0,0,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,0},
			  {0,1,1,1,0,1,1,1,1,1,0,0,0,1,0,1,0,5,0,0},
			  {0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,1,0,0},
			  {0,1,0,0,0,0,0,1,0,1,1,1,1,1,1,1,0,1,0,0},
			  {0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0},
			  {0,1,1,1,1,1,1,4,1,2,0,1,0,1,1,1,1,1,0,0},
			  {0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0},
			  {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,1,0,1,0,0},
			  {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,6,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	  // 0 = Wand
	  // 1 = Boden
	  // 2 = Trank
	  // 3 = tuerZu
	  // 4 = Spieler
	  // 5 = Monster
	  // 6 = Tuer offen
	  // 7 = Tuer zu
	  

	  public SpielFeld(GUImain fenster,int bildx,int bildy) 
	  {
		 this.fenster = fenster;
		 Toolkit tk = Toolkit.getDefaultToolkit();
		 this.bildx = bildx;
		 this.bildy = bildy;
		  
		 boden = tk.getImage("img/boden.png");
		 wand = tk.getImage("img/wand.png");
		 tuerZu = tk.getImage("img/tuer.png");
		 tuerOffen = tk.getImage("img/tueroffen.png");
		 schluessel = tk.getImage("img/schluessel.png");
		 heiltrank = tk.getImage("img/trank.png");
		 monster1 = tk.getImage("img/drache1.png");
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
	    	if(((fenster.spieler.getPosY()-448)/32) > 0)starty=(fenster.spieler.getPosY()-448)/32+1;
	    	else
	    		{
	    			if(starty>1)starty-=1;
	    			else starty=0;
	    		}
	    	maleMap(g, 20,20,bildx,bildy, dummyMap,startx,starty);
	    	
	    	/*if(fenster.spieler.getPosY() >=480)
	    	{
	    		this.setLocation(0, (fenster.spieler.getPosY()-480)/32);
	    		maley = fenster.spieler.getPosY()/32+2;
	    	}*/
	     
	    }

	    public void maleMap(Graphics g, int maleX, int maleY, int bildX, int bildY,int[][] dummyMap, int startx, int starty) 
	    {
	    	this.startx = startx;
	    	this.starty = starty;
	    	
	    	for(int i = startx; i<maleX;i++)
	    	{
	    		for(int j = starty; j<maleY; j++)
	    		{
	    			if(dummyMap[i][j] != 0)
	    			{
	    				g.drawImage(boden, i*bildX, (j-starty)*bildY, bildX, bildY, this);
	    			}
	    			
	    			if(dummyMap[i][j] == 0)
	    			{
	    				g.drawImage(wand, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			
	    			if(dummyMap[i][j] == 2)
	    			{
	    				g.drawImage(heiltrank, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			
	    			if(dummyMap[i][j] == 3)
	    			{
	    				g.drawImage(schluessel, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			
	    			if(dummyMap[i][j] == 4 )
	    			{
	    				g.drawImage(boden, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    				if(startpunktSpieler == false){
	    					fenster.spieler.setPos(i*bildX, (j-starty)*bildY);
	    					startpunktSpieler = true;
	    				}
	    				//g.drawImage(fenster.spieler.getBild(), i*bildX, j*bildY, this);
	    				
	    			}
	    			if(dummyMap[i][j] == 6)
	    			{
	    				g.drawImage(tuerOffen, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			if(dummyMap[i][j] == 7)
	    			{
	    				g.drawImage(tuerZu, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			if(dummyMap[i][j] == 5)
	    			{
	    				g.drawImage(monster1, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    				g.setColor(Color.GREEN);
	    				g.fillRect(i*bildX, (j-starty)*bildY, bildX, bildY/6);
	    			}
	    			
	    			if(dummyMap[i][j] == 2)
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
	    				if(k*32 < fenster.spieler.getPosX()-32*2 || k*32 > fenster.spieler.getPosX()+32*2 || l*32 <fenster.spieler.getPosY()-32*2-getstarty() || l*32 > fenster.spieler.getPosY()+32*2-getstarty()) g.fillRect(k*32, l*32, 32, 32);
	    			}
	    		}
	    	}
	    	g.drawImage(fenster.spieler.getBild(), fenster.spieler.getPosX(), fenster.spieler.getPosY()-(starty*32),bildX,bildY, this);
			
	    	
	    	
		}

	    public void loescheTrank(int i , int j){
	    	dummyMap[i][j] = 1;
	    }
	    
	    public void loescheMonster(int i , int j){
	    	dummyMap[i][j] = 1;
	    }
	    
	    public int getstarty()
	    {
	    	return starty*32;
	    }
	   
}
	 

