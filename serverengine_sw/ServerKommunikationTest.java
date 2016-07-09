package serverengine_sw;
import Kommunikation.*;
import shared.Message;
public class ServerKommunikationTest {
	public CommServer server;
	
	
	public ServerKommunikationTest (){
		try {
			server = new CommServer (7891);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void hadnleMsg(){
		Message msg = server.getNextMessage();
		
		switch(msg.getTyp()){
		case 1:
		case 2:
			
		}
	}

}
