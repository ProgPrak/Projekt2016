package kommunikation;

import GUI.Nachricht;
import shared.Message;

public interface ICommServer {
	
	public void messageToClient(Nachricht msg);
	public void getNextMessage();
	public void stopConnection();

}
