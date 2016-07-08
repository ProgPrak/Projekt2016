package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Minimap extends JPanel
{
	
	GUImain gui;
	int[][] karte;
	
	private Image boden, wand, tuerZu, tuerOffen, schluessel, heiltrank, monster1,monster2;

	private static final long serialVersionUID = 1L;

	// Im Konstruktor werden Bilder aus Datei geladen
	public Minimap(GUImain gui) {
		this.gui = gui;
		 Toolkit tk = Toolkit.getDefaultToolkit();
		 karte = gui.spielFeld.dummyMap;
		 boden = tk.getImage("img/boden.png");
		 wand = tk.getImage("img/wand.png");
		 tuerZu = tk.getImage("img/tuer.png");
		 tuerOffen = tk.getImage("img/tueroffen.png");
		 schluessel = tk.getImage("img/schluessel.png");
		 heiltrank = tk.getImage("img/trank.png");
		 monster1 = tk.getImage("img/drache1.png");
		 monster2 = tk.getImage("img/drache2.png");
	}

	// Zeichnet alle Kartengegenstände auf das Panel, dabei wird auf die Karte des Spielfelds zugegegriffen
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i<20; i++)
		{
			for(int j = 0 ; j<20; j++)
			{
				
		    			if(karte[i][j] == 1 || karte[i][j] == 4 || karte[i][j] == 5 || karte[i][j] == 2)
		    			{
		    				g.drawImage(boden, i*5, j*5, 5, 5, this);
		    			}
		    			
		    			if(karte[i][j] == 0)
		    			{
		    				g.drawImage(wand, i*5, j*5,5,5, this);
		    			}
		    			
		    			if(karte[i][j] == 7)
		    			{
		    				g.drawImage(tuerOffen, i*5, j*5,5,5, this);
		    			}
		    			
		    			if(karte[i][j] == 6)
		    			{
		    				g.drawImage(tuerZu, i*5, j*5,5,5, this);
		    			}
		    			if(karte[i][j] == 5){
		    				g.drawImage(monster1, i*5, j*5,5,5, this);
		    			}
		    			if(karte[i][j] == 2)
		    			{
		    				g.drawImage(heiltrank, i*5, j*5,5,5, this);
		    			}
		    		}
		    	}

		g.setColor(Color.BLACK);
		g.fillRect(0, 100, 100, 400);

	}

	public void aktualisiereMap(int[][] karte) 
	{
		this.karte = karte;
	}
}
