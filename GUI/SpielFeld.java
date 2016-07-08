package GUI;

import javax.swing.*;

import datenstruktur.Schluessel;

import java.awt.*;

@SuppressWarnings("serial")
public class SpielFeld extends JPanel
{
	
	int malex = 20,maley = 20,bildx,bildy,startx=0,starty=0;

	  GUImain fenster;
	  private Image boden,wand,tuerZu,tuerOffen,schluesselImg,heiltrank, monster1;
	   boolean startpunktSpieler = false;
	  Schluessel schluessel;
	  int[][] aktuellesLevel;
	  
	  
	  //Lädt Bilder aus Dateien
	  public SpielFeld(GUImain fenster,int bildx,int bildy) 
	  {
		 this.fenster = fenster;
		 Toolkit tk = Toolkit.getDefaultToolkit();
		 this.bildx = bildx;
		 this.bildy = bildy;
		 aktualisiereArray();
		 boden = tk.getImage("img/boden.png");
		 wand = tk.getImage("img/wand.png");
		 tuerZu = tk.getImage("img/tuer.png");
		 tuerOffen = tk.getImage("img/tueroffen.png");
		 schluesselImg = tk.getImage("img/schluessel.png");
		 heiltrank = tk.getImage("img/trank.png");
		 monster1 = tk.getImage("img/drache1.png");
		 this.requestFocus();
		 

	     
	  }
	  
	  public void aktualisiereArray()
	  {
		  aktuellesLevel = fenster.testClient.aktuellesLevel;
	  }
	  
	  public int[][] getKarte()
	  {
		  return aktuellesLevel;
	  }

	  //Allgemeine "Paint" Methode, scrollen der Map ist eingebaut
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
	    	maleMap(g, 20,20,bildx,bildy, aktuellesLevel,startx,starty);
	    		     
	    }

	    // Zeichnet die Komponenten auf das Panel, abhängig von Zahlen im Array
	    public void maleMap(Graphics g, int maleX, int maleY, int bildX, int bildY,int[][] aktuellesLevel, int startx, int starty) 
	    {
	    	this.startx = startx;
	    	this.starty = starty;
	    	
	    	for(int i = startx; i<maleX;i++)
	    	{
	    		for(int j = starty; j<maleY; j++)
	    		{
	    			if(aktuellesLevel[i][j] != 0)
	    			{
	    				g.drawImage(boden, i*bildX, (j-starty)*bildY, bildX, bildY, this);
	    			}
	    			
	    			if(aktuellesLevel[i][j] == 0)
	    			{
	    				g.drawImage(wand, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			
	    			if(aktuellesLevel[i][j] == 2)
	    			{
	    				g.drawImage(heiltrank, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			
	    			if(aktuellesLevel[i][j] == 3)
	    			{
	    				g.drawImage(schluesselImg, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			
	    			if(aktuellesLevel[i][j] == 4 )
	    			{
	    				g.drawImage(boden, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    				if(startpunktSpieler == false){
	    					System.out.println(i*bildX+"  "+(j-starty)*bildY);
	    					fenster.spieler.setPos(i*bildX, (j-starty)*bildY);
	    					startpunktSpieler = true;
	    				}
	    				//g.drawImage(fenster.spieler.getBild(), i*bildX, j*bildY, this);
	    				
	    			}
	    			if(aktuellesLevel[i][j] == 6)
	    			{
	    				g.drawImage(tuerOffen, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			if(aktuellesLevel[i][j] == 7)
	    			{
	    				g.drawImage(tuerZu, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    			}
	    			if(aktuellesLevel[i][j] == 5)
	    			{
	    				g.drawImage(monster1, i*bildX, (j-starty)*bildY,bildX,bildY, this);
	    				g.setColor(Color.GREEN);
	    				g.fillRect(i*bildX, (j-starty)*bildY, bildX, bildY/6);
	    			}
	    			
	    			if(aktuellesLevel[i][j] == 2)
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

	    public void aktualisiereArray(int i , int j)
	    {
	    	aktuellesLevel[i][j] = 1;
	    }
	    
	    public void aktualisiereArrayFlexibel(int i , int j, int k)
	    {
	    	aktuellesLevel[i][j] = k;
	    }
	    
	    public int getstarty()
	    {
	    	return starty*32;
	    }
	   
}
	 

