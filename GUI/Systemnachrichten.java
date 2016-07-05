package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Systemnachrichten extends JFrame {
ArrayList<String> nachricht = new ArrayList<>();

	private static final long serialVersionUID = 1L;
	JPanel nachrichten = new JPanel();
	public Systemnachrichten()
	{
		this.setVisible(true);
		this.setTitle("Systemnachrichten");
		this.setLocation(750,0);
		this.setSize(300, 300);
		//this.setResizable(false);
		nachricht("Für Test: User:molina, Passwort:madrid");
	}
	
	public void nachricht(String n)
	{
		nachricht.add(n);
	}
	
	public void paint(Graphics g)
	{
		int x = 1;
		super.paint(g);
		if(nachricht.size() != 0){
			for(int i = nachricht.size(); i>0; i--)
			{
				
				g.setColor(Color.BLACK);
				g.drawString(nachricht.get(i-1), 35, x*20+25);
				x++;
			}
		}
	}
}
