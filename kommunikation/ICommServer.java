package kommunikation;

import shared.Message;

public interface ICommServer {
	
	public void messageToClient(Message msg);
	
	public void stopConnection();

}
