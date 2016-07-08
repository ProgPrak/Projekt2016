package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import GUI.Highscore;
import GUI.Menu;
import GUI.SpielFeld;
import GUI.Statusleiste;
import GUI.Steuerung;
import datenstruktur.Spieler;

public class GUImain extends JFrame implements MouseListener, KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Die für das Spiel relevanten Panels und Variablen werden deklariert
	private PasswortFenster pf;	
	public SpielFeld spielFeld;
	public Menu menu;
	private Statusleiste leiste;
	private Steuerung steuerung;
	private Highscore highscore;
	public Systemnachrichten sn;
	private Minimap mm;
	
	public Spieler spieler;
	public int rand = 480;
	public long startZeit;
	public int levelNow = 0;
	public final int BOX = 32, WIDTH = 16, HEIGHT = 16;
	public boolean highscoreAngezeigt = false;
	public int breite,laenge;
	
	//Test-Client zum übermitteln der Nachrichten
	private TestClient testClient;
	
	public GUImain()
	{
		initialisiereJFrame(20*32,15*32,"Hindi Bones");

		//Event-Listener werden eingebaut
		this.addMouseListener(this);
		this.addKeyListener(this);
		
		//Weitere wichtige Eigenschaften des Frames werden definiert
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// Initialisierung der Komponenten des Frames und der Panels
	// Einzelne Panels werden zugewiesen
	public void initialisiereJFrame(int width, int height, String title) 
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		this.setLocation((int)tk.getScreenSize().getWidth()/2-250, (int)tk.getScreenSize().getHeight()/2-117);
		breite=width;
		laenge = height;
		this.setLayout(new BorderLayout());
		this.spielFeld = new SpielFeld(this, 32 ,32);
		this.leiste = new Statusleiste(this);
		this.steuerung = new Steuerung();
		this.highscore = new Highscore();
		this.menu = new Menu(this);
		this.pf = new PasswortFenster(this);
		this.spieler = new Spieler(this);
		this.sn = new Systemnachrichten();
		sn.setLocation((int)tk.getScreenSize().getWidth()/2+500, (int)tk.getScreenSize().getHeight()/2-117);
		this.mm = new Minimap(this);
		this.testClient = new TestClient(1);
		mm.setPreferredSize(new Dimension(100, 500));
		pf.setPreferredSize(new Dimension(500,235));
		menu.setPreferredSize(new Dimension(width, 25));
		spielFeld.setPreferredSize(new Dimension(width, height));
		leiste.setPreferredSize(new Dimension(width, BOX));
		steuerung.setPreferredSize(new Dimension(width, height));
		highscore.setPreferredSize(new Dimension(width, height));
		this.PasswortFensterAnzeigen();
		this.setTitle(title);
		this.setBackground(Color.BLACK);
		
	}
	
	// Entfernt Container, die nicht angezeigt werden sollen und zeigt Spielfeld
	public void SpielfeldAnzeigen() 
	{
		this.remove(highscore);
		this.remove(pf);
		this.remove(steuerung);
		this.add(leiste, BorderLayout.SOUTH);
		this.getContentPane().add(spielFeld, BorderLayout.CENTER);
		this.add(menu, BorderLayout.NORTH);
		this.getContentPane().add(mm, BorderLayout.EAST);
		this.requestFocus();
		this.pack();	
	}
	
	// Entfernt Container, die nicht angezeigt werden sollen und zeigt Highscore
	public void highscoreAnzeigen()
	{
		this.remove(spielFeld);
		this.remove(leiste);
		this.remove(steuerung);
		this.remove(mm);
		this.add(highscore, BorderLayout.CENTER);
		this.repaint();
		highscore.addSpielerToHighScore((int)2);
		this.requestFocus();
		this.pack();
		highscore.repaint();		
	}
	
	// Entfernt Container, die nicht angezeigt werden sollen und zeigt Steuerung
	public void SteuerungAnzeigen()
	{
		this.remove(pf);
		this.remove(spielFeld);
		this.remove(leiste);
		this.remove(mm);
		this.remove(highscore);
		this.add(steuerung, BorderLayout.CENTER);
		this.requestFocus();
		this.pack();
		steuerung.repaint();
	}
	
	// Entfernt Container, die nicht angezeigt werden sollen und zeigt PasswortFenster
	public void PasswortFensterAnzeigen()
	{
		this.remove(spielFeld);
		this.remove(mm);
		this.remove(leiste);
		this.remove(highscore);
		this.remove(steuerung);
		this.remove(menu);
		this.add(pf);
		this.pack();
		
	}

	// Startet Thread und erstellt GUI
	public static void main(String[] args)
	{
		GUImain gui = new GUImain();
		class DemoThread extends Thread 
		{
			

			public void run()
			{
				//gui.repaint();
				//gui.spielFeld.repaint();
				//gui.sn.repaint();
				//gui.mm.repaint();
				while(true)
				{
					//gui.mm.repaint();
					try
					{
						sleep(100);
					}
					catch(InterruptedException e){
					}
					if(gui.pf.getEinloggenWahr())
					{
						gui.sn.nachricht("User: "+gui.pf.getLoginName()+" ist angemeldet.");
						gui.SpielfeldAnzeigen();
						gui.pf.setEinloggenFalsch();
						gui.startZeit = System.currentTimeMillis();
					}
					if(gui.menu.getbNeustart() == true)
					{
						gui.sn.dispose();
						gui.remove(gui.spielFeld);
						gui.remove(gui.menu);
						gui.remove(gui.steuerung);
						gui.remove(gui.highscore);
						gui.remove(gui.leiste);
						gui.remove(gui.mm);
						gui.menu.setFalsebNeustart();
						gui.initialisiereJFrame(20*32, 15*32, "Hindi Bones");
					}
					
				}
			}
		}
		
		DemoThread dt;
		dt = new DemoThread();
		dt.start();
		
		
		
		
	}

	public void beenden() 
	{
		System.exit(0);
	}

	public void karteaufdecken() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
			
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
// Bewegungen in alle Richtungen mit der Maustaste, pixelgenau
	@Override
	public void mousePressed(MouseEvent e) 
	{
		if((e.getY()-55+spielFeld.getstarty() < spieler.getPosY()) && (e.getX()<=spieler.getPosX()+35) && (e.getX()>=spieler.getPosX()) && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] != 0 && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] ==5)
		{
			spielFeld.loescheMonster(spieler.getPosX()/32, ((spieler.getPosY()/32)-1));
			mm.aktualisiereMap(spielFeld.getMap());
			mm.repaint();
		}
		if((e.getY()-55+spielFeld.getstarty() > spieler.getPosY()+32) && (e.getX()<=spieler.getPosX()+35) && (e.getX()>=spieler.getPosX()) && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] != 0 && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] ==5)
		{
			spielFeld.loescheMonster(spieler.getPosX()/32, ((spieler.getPosY()/32)+1));
			mm.aktualisiereMap(spielFeld.getMap());
			mm.repaint();

		}
		if((e.getX() > spieler.getPosX()+32) && (e.getY()-55+spielFeld.getstarty() > spieler.getPosY()) && (e.getY()-55+spielFeld.getstarty() < spieler.getPosY()+32) && spielFeld.getMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] != 0 && spielFeld.getMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] ==5)
		{
			spielFeld.loescheMonster((spieler.getPosX()/32)+1, ((spieler.getPosY()/32)));
			mm.aktualisiereMap(spielFeld.getMap());
			mm.repaint();

		}
		if((e.getX() < spieler.getPosX()) && (e.getY()-55+spielFeld.getstarty() > spieler.getPosY()) && (e.getY()-55+spielFeld.getstarty() < spieler.getPosY()+32) && spielFeld.getMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] != 0 && spielFeld.getMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] ==5)
		{
			spielFeld.loescheMonster((spieler.getPosX()/32)-1, ((spieler.getPosY()/32)));
			mm.aktualisiereMap(spielFeld.getMap());
			mm.repaint();

		}
	}

	//Bewegung des Spielers über den Mouse-Listener
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
		// Wird das Feld über dem Spieler angeklickt, so bewegt sich der Spieler ein Feld nach oben, sofern dies möglich ist
		// Zudem sendet der Client eine Message an den Server, dass der Spieler nun an einer neuen Position ist
		if(((e.getY()-55+spielFeld.getstarty()) < spieler.getPosY()) && (e.getX()<=spieler.getPosX()+35) && (e.getX()>=spieler.getPosX()) && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] != 0 && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] !=5)
		{
			spieler.hoch();	
			spielFeld.repaint();	
			testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));
		}
		
		// Wird das Feld unter dem Spieler angeklickt, so bewegt sich der Spieler ein Feld nach unten, sofern dies möglich ist
		// Zudem sendet der Client eine Message an den Server, dass der Spieler nun an einer neuen Position ist
		if(((e.getY()-55+spielFeld.getstarty()) > spieler.getPosY()+32) && (e.getX()<=spieler.getPosX()+35) && (e.getX()>=spieler.getPosX()) && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] != 0 && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] !=5)
		{
			spieler.runter();
			spielFeld.repaint();
			testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));
		}
		
		// Wird das Feld rechts neben dem Spieler angeklickt, so bewegt sich der Spieler ein Feld nach rechts, sofern dies möglich ist
		// Zudem sendet der Client eine Message an den Server, dass der Spieler nun an einer neuen Position ist
		if((e.getX() > spieler.getPosX()+32) && ((e.getY()-55+spielFeld.getstarty()) > spieler.getPosY()) && ((e.getY()-55+spielFeld.getstarty()) < spieler.getPosY()+32) && spielFeld.getMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] != 0 && spielFeld.getMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] !=5)
		{
			spieler.rechts();
			spielFeld.repaint();
			testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));
		}
		
		// Wird das Feld links neben dem Spieler angeklickt, so bewegt sich der Spieler ein Feld nach links, sofern dies möglich ist
		// Zudem sendet der Client eine Message an den Server, dass der Spieler nun an einer neuen Position ist
		if((e.getX() < spieler.getPosX()) && ((e.getY()-55+spielFeld.getstarty()) > spieler.getPosY()) && ((e.getY()-55+spielFeld.getstarty()) < spieler.getPosY()+32) && spielFeld.getMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] != 0 && spielFeld.getMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] !=5)
		{
			spieler.links();
			spielFeld.repaint();
			testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// Escape-Taste endet Programm
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}		
		
		// Leertaste nimmt Trank auf, wenn er unter Spieler ist und schickt Nachricht an Server.
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(spielFeld.getMap()[spieler.getPosX()/32][spieler.getPosY()/32] == 2)
			{
				sn.nachricht("Trank aufgenommen!");
				sn.repaint();
				spieler.setAnzahlHeiltraenke(spieler.getAnzahlHeiltraenke()+1);
				spielFeld.loescheTrank(spieler.getPosX()/32, spieler.getPosY()/32);
				testClient.sende(new Nachricht(2,spieler.getPosX()/32, spieler.getPosY()/32));
				mm.aktualisiereTrank(spielFeld.getMap());
				mm.repaint();

			}
		}
		
		// N-Taste setzt einen Trank ein.
		if(e.getKeyCode() == KeyEvent.VK_N)
		{
			if(spieler.getAnzahlHeiltraenke() >0)
			{
				spieler.setAnzahlHeiltraenke(spieler.getAnzahlHeiltraenke()-1);
				sn.nachricht("Trank eingesetzt!");
				sn.repaint();

			}
		}
		
		// Spieler Bewegung
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(spielFeld.getMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] != 0 && spielFeld.getMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] ==5)
			{	
			spielFeld.loescheMonster((spieler.getPosX()/32)-1, ((spieler.getPosY()/32)));
			mm.aktualisiereMap(spielFeld.getMap());
			mm.repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(spielFeld.getMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] != 0 && spielFeld.getMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] ==5)
			{
				spielFeld.loescheMonster((spieler.getPosX()/32)+1, ((spieler.getPosY()/32)));
				mm.aktualisiereMap(spielFeld.getMap());
				mm.repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			
			if(spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] != 0 && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] ==5)
			{
				spielFeld.loescheMonster(spieler.getPosX()/32, ((spieler.getPosY()/32)+1));
				mm.aktualisiereMap(spielFeld.getMap());
				mm.repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			if(spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] != 0 && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] ==5)	
			{
				spielFeld.loescheMonster(spieler.getPosX()/32, ((spieler.getPosY()/32)-1));
				mm.aktualisiereMap(spielFeld.getMap());
				mm.repaint();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			if(spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] != 0 && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] !=5)	
			{
				
				spieler.hoch();	
				spielFeld.repaint();	
				testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if(spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] != 0 && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] !=5)
			{
				spieler.runter();
				spielFeld.repaint();				
				testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(spielFeld.getMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] != 0 && spielFeld.getMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] !=5)
			{
				
				spieler.rechts();
				spielFeld.repaint();				
				testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(spielFeld.getMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] != 0 && spielFeld.getMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] !=5)
			{
				spieler.links();
				spielFeld.repaint();				
				testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	

	
}
