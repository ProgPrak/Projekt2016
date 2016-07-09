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
	
	
	public static void handler(Nachricht msg){
		switch(msg.getTyp()){
		case 0: System.out.println(msg.getName()+" "+msg.getPasswort());
				if(msg.getName().equals("molina")&&msg.getPasswort().equals("madrid")){
					System.out.println("erfolgreiches einloggen");
				}
				else{
					System.out.println("falsches einloggen");
				}
				break;
		case 5: System.out.println(msg.getFehler());break;
		case 2: break;
		default: break;
			
		}
	}

	public static void main(String[] args){
		ServerKommunikationTest s = new ServerKommunikationTest();
		while(true){
			Nachricht command = s.server.getNextMessage();
			if(command!=null){
				s.handler(command);
				}
			}
	}
}
