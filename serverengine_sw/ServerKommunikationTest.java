package serverengine_sw;
import kommunikation.*;
import GUI.Nachricht;;
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
	
	
	public void handleMsg(){
		Nachricht msg = server.getNextMessage();
		//
		switch(msg.getTyp()){
		case 0: System.out.println(msg.getName()+" "+msg.getPasswort()); break;
		case 1:
		case 2:
			
		}
	}

}
