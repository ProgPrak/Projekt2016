package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Statusleiste extends JPanel
{
	private Image schluessel, trank, hintergrund, spieler;
	
	private GUImain fenster;
	
	Statusleiste(GUImain fenster)
	{	
		this.fenster = fenster;
		Toolkit tk = Toolkit.getDefaultToolkit();
		hintergrund = tk.getImage("img/status.png");
		schluessel = tk.getImage("img/schluessel.png");
		trank = tk.getImage("img/trank.png");
		spieler = tk.getImage("img/spieler.png");
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);		
		for(int i = 0; i < 100; i++){
			g.drawImage(hintergrund, i*32, 0, null);
		}	
		if(spieler !=null)g.drawImage(spieler, 0, 0, this);
		g.setColor(Color.WHITE);
		g.drawString("Level: " + fenster.levelNow +1, 56, 20);
		g.setColor(Color.WHITE);
		g.drawString("Zeit: " + (System.currentTimeMillis()-fenster.startZeit)/1000, 126, 20);
		g.setColor(Color.GREEN);
		g.fillRect(200, 15, 60, 4);
		g.setColor(Color.WHITE);
		g.drawString(fenster.spieler.getAnzahlHeiltraenke()+"x", 300, 20);
		g.drawImage(trank, 310, 0, this);
		repaint();
		if(fenster.spieler.hatSchluessel())g.drawImage(schluessel, 400, 0, this);
	}
}
