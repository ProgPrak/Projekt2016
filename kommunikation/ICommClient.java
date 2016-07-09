package kommunikation;

import GUI.Nachricht;;

public interface ICommClient {
	
	public void sendMessage(Nachricht message);
	public void addToSendQ();
	public Nachricht getNextMessage();
		
}

