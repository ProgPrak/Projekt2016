package GUI;

	import java.awt.Container;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


	@SuppressWarnings("serial")
	public class Menu extends JMenuBar implements ActionListener 
	{
 	     JFrame applikation;
	     Container container;
	     
	     JMenuBar menueLeiste;
	     GUImain gui;
	     JMenu optionen, anzeige;
	     
	     JMenuItem beenden;
	     JMenuItem highscore;
	     JMenuItem neustart;
	     JMenuItem karteaufdecken;
	     JMenuItem steuerung;
	     JMenuItem karteanzeigen;
	     JMenuItem ausloggen;
	     
	     boolean bKarte, bNeustart, bBeenden, bHighscore, bSteuerung;

	     
	     // Erstelle Men�punkte und f�ge diese Obermen� hinzu. Die beiden Obermen�s werden der Men�leiste hinzugef�gt.
	     public Menu(GUImain gui) {
	           
	    	 this.gui = gui;
	    	 beenden = new JMenuItem("Beenden");
	         highscore = new JMenuItem("Highscore");
	         neustart = new JMenuItem("Neustart");
	         karteaufdecken = new JMenuItem("Karte aufdecken");    
	         steuerung = new JMenuItem("Steuerung");
	        karteanzeigen = new JMenuItem("Karte anzeigen");
	        ausloggen = new JMenuItem("Ausloggen"); 
	        
	         optionen = new JMenu("Optionen");
	         anzeige = new JMenu("Anzeige");
	         
	         beenden.addActionListener(this);
	         highscore.addActionListener(this);
	         neustart.addActionListener(this);
	         karteaufdecken.addActionListener(this);
	         steuerung.addActionListener(this);
	         karteanzeigen.addActionListener(this);
	         ausloggen.addActionListener(this);
	         optionen.add(beenden);
	         anzeige.add(highscore);
	         optionen.add(neustart);
	         anzeige.add(karteaufdecken);
	         anzeige.add(steuerung);
	         optionen.add(ausloggen);
	        anzeige.add(karteanzeigen);
	        this.add(optionen);
	        this.add(anzeige);
	     }
	     
	    
	    // F�hrt bestimmte Aktionen bei dr�cken auf die einzelnen Men�punkte aus
		public void actionPerformed(ActionEvent object) 
	     {
	          if (object.getSource() == neustart)
	          {
	        	  bNeustart = true;
	        	  gui.sn.nachricht("Spiel wird neu gestartet!");
	        	  gui.sn.repaint();
	          }
	          if (object.getSource() == beenden)
	          {
	        	  gui.beenden();
	        	  
	          }
	          if (object.getSource() == highscore)
	          {
	        	 gui.highscoreAnzeigen();
	        	 gui.sn.nachricht("Highscore anzeigen!");
	        	 gui.sn.repaint();
	          }
	          if (object.getSource() == karteaufdecken)
	          {
	        	 if(bKarte == false)
	        	 {
	        		 karteaufdecken.setText("Karte zudecken");bKarte = true;
	        		 gui.sn.nachricht("Karte aufdecken!");
		        	  gui.sn.repaint();
	        	 }
	        	 else
	        	 {
	        		 bKarte = false;
	        		 karteaufdecken.setText("Karte aufdecken");
	        		 gui.sn.nachricht("Karte zudecken!");
		        	  gui.sn.repaint();
	        	 }
	          }
	          if(object.getSource() == steuerung)
	          {
	        	  gui.SteuerungAnzeigen();
	        	  gui.sn.nachricht("Steuerung anzeigen!");
	        	  gui.sn.repaint();
	          }
	          if(object.getSource() == karteanzeigen)
	          {
	        	  gui.SpielfeldAnzeigen();
	          }
	          if (object.getSource() == ausloggen)
	          {
	        	  gui.PasswortFensterAnzeigen();
	        	  gui.sn.nachricht("Spieler wurde ausgeloggt!");
	        	  gui.sn.repaint();
	          }
	     }
		
	     
	     public boolean getbHighscore()
	     {
	    	 return bHighscore;
	     }
	     public boolean getbBeenden()
	     {
	    	 return bBeenden;
	     }
	     public boolean getbNeustart()
	     {
	    	 return bNeustart;
	     }
	     public boolean getbKarte()
	     {
	    	 return bKarte;
	     }
	     public void setFalsebHighscore()
	     {
	    	 bHighscore = false;
	     }
	     public void setFalschNeustartBoolean()
	     {
	    	 bNeustart = false;
	     }
	     public void setFalschKarteAufdeckenBoolean()
	     {
	    	 bKarte = false;
	     }
	     public void setFalschSteuerungBoolean()
	     {
	    	 bSteuerung = false;
	     }
	     public boolean getSteuerungBoolean()
	     {
	    	 return bSteuerung;
	     }
}
