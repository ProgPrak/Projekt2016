package serverengine_sw;
import kommunikation.*;

import java.io.Serializable;

import GUI.Nachricht;;
public class ServerKommunikationTest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		case 5: System.out.println(msg.getFehler());
		case 2:
			
		}
	}

	public static void main(String[] args){
		ServerKommunikationTest s = new ServerKommunikationTest();
		while(true){
			if(s.server.getNextMessage()!=null){
				System.out.println("next message ist nicht null");
				s.handleMsg();
				}
			}
	}
}
