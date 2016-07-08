package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
 
@SuppressWarnings("serial")
public class PasswortFenster extends JPanel
                          implements ActionListener {
    private static String OK = "ok";
    private String nutzer = "molina";
    private JFrame kontrollFenster; 
    private JPasswordField passwortFeld;
    private JTextField nutzerFeld;
    private JLabel hintergrundBild;
	private boolean einloggenWahr = false;
 
	
	// Erstellt Labels, sowie Passwort- und Nutzer-Fenster und addet diese zum Panel
    public PasswortFenster(JFrame f) {
       
        kontrollFenster = f;
 
        hintergrundBild = new JLabel(new ImageIcon("img/giphy1.gif"));
        hintergrundBild.setLayout(null);
        hintergrundBild.setPreferredSize(new Dimension(500, 200));
        
        passwortFeld = new JPasswordField(10);
        passwortFeld.setActionCommand(OK);
        passwortFeld.addActionListener(this);
        nutzerFeld = new JTextField(15);
        nutzerFeld.setActionCommand(OK);
        nutzerFeld.addActionListener(this);
       
        JLabel nutzerLabel = new JLabel("Nutzer: ");
        nutzerLabel.setLabelFor(nutzerFeld);
        JLabel passwortLabel = new JLabel("Passwort: ");
        passwortLabel.setLabelFor(passwortFeld);
 
        JComponent buttonPane = erstelleKnopf();
 
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(nutzerLabel);
        textPane.add(nutzerFeld);
        
        textPane.add(passwortLabel);
        textPane.add(passwortFeld);
        
        add(hintergrundBild);
        add(textPane);
        add(buttonPane);
        
    }
 
    //
    protected JComponent erstelleKnopf() {
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton okKnopf = new JButton("OK");
 
        okKnopf.setActionCommand(OK);
        okKnopf.addActionListener(this);
        p.add(okKnopf);
 
        return p;
    }
    
    public void setEinloggenFalsch()
    {
    	einloggenWahr = false;
    }
 
    // Prüft Methoden bei bestimmter Aktion
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
 
        if (OK.equals(cmd)) { 
            char[] input = passwortFeld.getPassword();
            String input2 = nutzerFeld.getText();
            
            	if (istPasswortKorrekt(input2, input)) 
            	{
            			einloggenWahr = true;
            			passwortFeld.setText(null);
            			nutzerFeld.setText(null);
            		
            	} 
            	else 
            	{
            		passwortFeld.setText(null);
        			nutzerFeld.setText(null);
            		JOptionPane.showMessageDialog(kontrollFenster,
            				"Invalid password/username. Try again.",
            				"Error Message",
            				JOptionPane.ERROR_MESSAGE);
            	}
            	
            
            Arrays.fill(input, '0');
 
            passwortFeld.selectAll();
            resetFocus();
        } else { 
            JOptionPane.showMessageDialog(kontrollFenster,
                "You can get the password by searching this example's\n"
              + "source code for the string \"correctPassword\".\n"
              + "Or look at the section How to Use Password Fields in\n"
              + "the components section of The Java Tutorial.");
        }
    }

    // Überprüft Login-Daten
    private boolean istPasswortKorrekt(String nutzerEingabe, char[] passwortEingabe) {
        boolean isCorrect = true;
        char[] correctPassword = { 'm','a','d','r','i', 'd' };
        try
        {
        	for(int i = 0; i<correctPassword.length;i++)
        	{
        	
        		if(passwortEingabe[i] != correctPassword[i])
        		{
        			isCorrect = false;
        			break;
        		}
        	}
        	for(int i1 = 0; i1<nutzer.length()-1;i1++)
        	{
        		if(nutzer.charAt(i1) != nutzerEingabe.charAt(i1))
        		{
        			isCorrect = false;
        			break;
        		}
        	}}catch(ArrayIndexOutOfBoundsException e){}
        
        		if(nutzerEingabe.length() != nutzer.length() || passwortEingabe.length != correctPassword.length)isCorrect = false;
        		return isCorrect;
        
        	
        }
 

    protected void resetFocus() {
        passwortFeld.requestFocusInWindow();
    }

	public boolean getEinloggenWahr() {
		return einloggenWahr ;
	}
	
	public String getLoginName()
	{
		return nutzer;
	}
}