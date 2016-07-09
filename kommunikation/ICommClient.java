package kommunikation;

import shared.Message;

public interface ICommClient {
	
	public void sendMessage(Message message);
	
	public Message getNextMessage();
		
}

