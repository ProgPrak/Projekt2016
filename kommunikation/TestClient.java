package kommunikation;

import javax.swing.JOptionPane;
import shared.Message;

/**
	* Testumgebung
	* @author Max Wuestenberg
	*/


public class TestClient {
	static String s = null;
	static String t = null;
	static String l = null;
		public static void main(String[] args) throws ClassNotFoundException {
			
			s = JOptionPane.showInputDialog("username: ");
			t = JOptionPane.showInputDialog("password: ");
			Message befehl = new Message(0,s,t);
		
			CommClient ccTest = new CommClient("localhost", 7891);
			ccTest.addToSendQ(befehl);
			ccTest.startingProcess();
		}
}