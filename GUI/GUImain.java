package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	private PasswortFenster pf;
	public SpielFeld spielFeld;
	public Menu menu;
	private Statusleiste leiste;
	private Steuerung steuerung;
	private Highscore highscore;
	public Systemnachrichten sn;
	private Minimap mm;
	public int rand = 480;
	public Spieler spieler;
	public long startZeit;
	public int levelNow = 0;
	public final int BOX = 32, WIDTH = 16, HEIGHT = 16;
	public boolean highscoreAngezeigt = false;
	public int breite,laenge;
	
	private TestClient testClient;
	
	public GUImain()
	{/*
		this.startZeit = System.currentTimeMillis();
		this.pf = new PasswortFenster(this);
		this.spielFeld = new SpielFeld(this);
		spielFeld.setPreferredSize(new Dimension(600,400));
		this.menu = new Menu(this);
		this.leiste = new Statusleiste(this);
		leiste.setPreferredSize(new Dimension(400,32));
		this.setSize(500, 400);
		this.setLocation(960-250, 520-200);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.add(pf);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setResizable(false);
		this.pack();
        this.setLocationRelativeTo(null);
        //setBackground(Color.RED);
        */
		initialisiereJFrame(20*32,15*32,"Hindi Bones");

		this.addMouseListener(this);
		this.addKeyListener(this);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initialisiereJFrame(int width, int height, String title) {
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
		this.mm = new Minimap(this);
		this.testClient = new TestClient(1);
		mm.setPreferredSize(new Dimension(100, 500));
		pf.setPreferredSize(new Dimension(500,50));
		menu.setPreferredSize(new Dimension(width, 25));
		spielFeld.setPreferredSize(new Dimension(width, height));
		leiste.setPreferredSize(new Dimension(width, BOX));
		steuerung.setPreferredSize(new Dimension(width, height));
		highscore.setPreferredSize(new Dimension(width, height));
		this.PasswortFensterAnzeigen();
		//this.setSize(width,height);
		this.setTitle(title);
		this.setBackground(Color.BLACK);
		
	}
	
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
		//spielFeld.repaint();
	}
	
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
					if(gui.pf.getLoginTrue())
					{
						gui.sn.nachricht("User: "+gui.pf.getLoginName()+" ist angemeldet.");
						gui.SpielfeldAnzeigen();
						gui.pf.setBooleanFalse();
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
// maus drücken
	@Override
	public void mousePressed(MouseEvent e) {
		if((e.getY()-55+spielFeld.getstarty() < spieler.getPosY()) && (e.getX()<=spieler.getPosX()+35) && (e.getX()>=spieler.getPosX()) && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] != 0 && spielFeld.getMonsterMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] != 0)
		{
			spielFeld.loescheMonster(spieler.getPosX()/32, ((spieler.getPosY()/32)-1));
			mm.aktualisiereMap(spielFeld.getMap());
			mm.repaint();
		}
		if((e.getY()-55+spielFeld.getstarty() > spieler.getPosY()+32) && (e.getX()<=spieler.getPosX()+35) && (e.getX()>=spieler.getPosX()) && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] != 0 && spielFeld.getMonsterMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] !=0)
		{
			spielFeld.loescheMonster(spieler.getPosX()/32, ((spieler.getPosY()/32)+1));
			mm.aktualisiereMap(spielFeld.getMap());
			mm.repaint();

		}
		if((e.getX() > spieler.getPosX()+32) && (e.getY()-55+spielFeld.getstarty() > spieler.getPosY()) && (e.getY()-55+spielFeld.getstarty() < spieler.getPosY()+32) && spielFeld.getMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] != 0 && spielFeld.getMonsterMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] !=0)
		{
			spielFeld.loescheMonster((spieler.getPosX()/32)+1, ((spieler.getPosY()/32)));
			mm.aktualisiereMap(spielFeld.getMap());
			mm.repaint();

		}
		if((e.getX() < spieler.getPosX()) && (e.getY()-55+spielFeld.getstarty() > spieler.getPosY()) && (e.getY()-55+spielFeld.getstarty() < spieler.getPosY()+32) && spielFeld.getMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] != 0 && spielFeld.getMonsterMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] != 0)
		{
			spielFeld.loescheMonster((spieler.getPosX()/32)-1, ((spieler.getPosY()/32)));
			mm.aktualisiereMap(spielFeld.getMap());
			mm.repaint();

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{

		if(((e.getY()-55+spielFeld.getstarty()) < spieler.getPosY()) && (e.getX()<=spieler.getPosX()+35) && (e.getX()>=spieler.getPosX()) && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] != 0 && spielFeld.getMonsterMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] == 0)
		{
			spieler.hoch();	
			spielFeld.repaint();	
			testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));

		}
		if(((e.getY()-55+spielFeld.getstarty()) > spieler.getPosY()+32) && (e.getX()<=spieler.getPosX()+35) && (e.getX()>=spieler.getPosX()) && spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] != 0 && spielFeld.getMonsterMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] == 0)
		{
			spieler.runter();
			spielFeld.repaint();
			testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));

		}
		if((e.getX() > spieler.getPosX()+32) && ((e.getY()-55+spielFeld.getstarty()) > spieler.getPosY()) && ((e.getY()-55+spielFeld.getstarty()) < spieler.getPosY()+32) && spielFeld.getMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] != 0 && spielFeld.getMonsterMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] ==0)
		{
			spieler.rechts();
			spielFeld.repaint();
			testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));

		}
		if((e.getX() < spieler.getPosX()) && ((e.getY()-55+spielFeld.getstarty()) > spieler.getPosY()) && ((e.getY()-55+spielFeld.getstarty()) < spieler.getPosY()+32) && spielFeld.getMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] != 0 && spielFeld.getMonsterMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] ==0)
		{
			spieler.links();
			spielFeld.repaint();
			testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}		
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(spielFeld.getTrankMap()[spieler.getPosX()/32][spieler.getPosY()/32] == 1)
			{
				sn.nachricht("Trank aufgenommen!");
				sn.repaint();
				spieler.setAnzahlHeiltraenke(spieler.getAnzahlHeiltraenke()+1);
				spielFeld.loescheTrank(spieler.getPosX()/32, spieler.getPosY()/32);
				mm.aktualisiereTrank(spielFeld.getTrankMap());
				mm.repaint();

			}
		}
		if(e.getKeyCode() == KeyEvent.VK_N)
		{
			if(spieler.getAnzahlHeiltraenke() >0)
			{
				spieler.setAnzahlHeiltraenke(spieler.getAnzahlHeiltraenke()-1);
				sn.nachricht("Trank eingesetzt!");
				sn.repaint();

			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(spielFeld.getMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] != 0 && spielFeld.getMonsterMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] !=0)
			{	
			spielFeld.loescheMonster((spieler.getPosX()/32)-1, ((spieler.getPosY()/32)));
			mm.aktualisiereMap(spielFeld.getMap());
			mm.repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(spielFeld.getMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] != 0 && spielFeld.getMonsterMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] !=0)
			{
				spielFeld.loescheMonster((spieler.getPosX()/32)+1, ((spieler.getPosY()/32)));
				mm.aktualisiereMap(spielFeld.getMap());
				mm.repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			
			if(spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] != 0 && spielFeld.getMonsterMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] !=0)
			{
				spielFeld.loescheMonster(spieler.getPosX()/32, ((spieler.getPosY()/32)+1));
				mm.aktualisiereMap(spielFeld.getMap());
				mm.repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			if(spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] != 0 && spielFeld.getMonsterMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] !=0)	
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
			if(spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] != 0 && spielFeld.getMonsterMap()[spieler.getPosX()/32][(spieler.getPosY()/32)-1] == 0)	
			{
				
				spieler.hoch();	
				spielFeld.repaint();	
				testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if(spielFeld.getMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] != 0 && spielFeld.getMonsterMap()[spieler.getPosX()/32][(spieler.getPosY()/32)+1] == 0)
			{
				spieler.runter();
				spielFeld.repaint();				
				testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(spielFeld.getMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] != 0 && spielFeld.getMonsterMap()[(spieler.getPosX()/32)+1][(spieler.getPosY()/32)] ==0)
			{
				
				spieler.rechts();
				spielFeld.repaint();				
				testClient.sende(new Nachricht(1,spieler.getPosX()/32,spieler.getPosY()/32));
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(spielFeld.getMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] != 0 && spielFeld.getMonsterMap()[(spieler.getPosX()/32)-1][(spieler.getPosY()/32)] ==0)
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
