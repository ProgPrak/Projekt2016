package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
 
@SuppressWarnings("serial")
public class PasswortFenster extends JPanel
                          implements ActionListener {
    private static String OK = "ok";
    private String user = "molina";
    private JFrame controllingFrame; 
    private JPasswordField passwordField;
    private JTextField userField;

	private boolean loginTrue = false;
 
    public PasswortFenster(JFrame f) {
       
        controllingFrame = f;
 
        
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand(OK);
        passwordField.addActionListener(this);
        userField = new JTextField(15);
        userField.setActionCommand(OK);
        userField.addActionListener(this);
       
        JLabel userlabel = new JLabel("User: ");
        userlabel.setLabelFor(userField);
        JLabel label = new JLabel("Passwort: ");
        label.setLabelFor(passwordField);
 
        JComponent buttonPane = createButtonPanel();
 
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(userlabel);
        textPane.add(userField);
        
        textPane.add(label);
        textPane.add(passwordField);
        
 
        add(textPane);
        add(buttonPane);
    }
 
    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton okButton = new JButton("OK");
 
        okButton.setActionCommand(OK);
        okButton.addActionListener(this);
        p.add(okButton);
 
        return p;
    }
    
    public void setBooleanFalse()
    {
    	loginTrue = false;
    }
 
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
 
        if (OK.equals(cmd)) { 
            char[] input = passwordField.getPassword();
            String input2 = userField.getText();
            
            	if (isPasswordCorrect(input2, input)) 
            	{
            			loginTrue = true;
            			passwordField.setText(null);
            			userField.setText(null);
            		
            	} 
            	else 
            	{
            		passwordField.setText(null);
        			userField.setText(null);
            		JOptionPane.showMessageDialog(controllingFrame,
            				"Invalid password/username. Try again.",
            				"Error Message",
            				JOptionPane.ERROR_MESSAGE);
            	}
            	
            
            Arrays.fill(input, '0');
 
            passwordField.selectAll();
            resetFocus();
        } else { 
            JOptionPane.showMessageDialog(controllingFrame,
                "You can get the password by searching this example's\n"
              + "source code for the string \"correctPassword\".\n"
              + "Or look at the section How to Use Password Fields in\n"
              + "the components section of The Java Tutorial.");
        }
    }

    private boolean isPasswordCorrect(String userInput, char[] passwordInput) {
        boolean isCorrect = true;
        char[] correctPassword = { 'm','a','d','r','i', 'd' };
        try
        {
        	for(int i = 0; i<correctPassword.length;i++)
        	{
        	
        		if(passwordInput[i] != correctPassword[i])
        		{
        			isCorrect = false;
        			break;
        		}
        	}
        	for(int i1 = 0; i1<user.length()-1;i1++)
        	{
        		if(user.charAt(i1) != userInput.charAt(i1))
        		{
        			isCorrect = false;
        			break;
        		}
        	}}catch(ArrayIndexOutOfBoundsException e){}
        
        		if(userInput.length() != user.length() || passwordInput.length != correctPassword.length)isCorrect = false;
        		return isCorrect;
        
        	
        }
 

    protected void resetFocus() {
        passwordField.requestFocusInWindow();
    }

	public boolean getLoginTrue() {
		return loginTrue ;
	}
	
	public String getLoginName()
	{
		return user;
	}
}