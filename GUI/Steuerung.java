package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Steuerung extends JPanel {

	private static final long serialVersionUID = 1L;

	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image img = null, boden = null;
				
		try{
			img = ImageIO.read(new File("img/steuerung.png"));
			boden = ImageIO.read(new File("img/status.png"));
		}catch(IOException e){ }
		
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				g.drawImage(boden, 32*i,32*j,null);
			}
		}
		g.drawImage(img, 0, 0, this);
		
	}
	
}