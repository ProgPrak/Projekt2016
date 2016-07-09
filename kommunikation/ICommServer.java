package kommunikation;

import GUI.Nachricht;

public interface ICommServer {
	
	public void messageToClient(Nachricht msg);
	public void getNextMessage();
	public void stopConnection();

}
