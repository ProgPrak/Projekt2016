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
	LevelverwaltungNeu lv;
	boolean empfangen;
	private Nachricht m;
	
	public ServerKommunikationTest (){
		this.lv=new LevelverwaltungNeu();
		try {
			server = new CommServer (7891);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.empfangen=false;
	}
	
	//Testzwecke, später löschen
	/*public ServerKommunikationTest() {
		try {
			server = new CommServer (7891);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/


	public void handler(){
		while(empfangen==false){
			m = server.getNextMessage();
			if(m!=null){
				empfangen=true;
				}
			}
		empfangen=false;
		
		Nachricht n = new Nachricht(10,true);
		switch(m.getTyp()){
		
		case 0: 
				if(m.getName().equals("molina")&&m.getPasswort().equals("madrid")){
					server.messageToClient(n);
					
				}
				else{
					server.messageToClient(new Nachricht(10,false));
				}
				break;
		case 1: this.lv.verarbeiteNachricht(m);
				server.messageToClient(new Nachricht(9, lv.levelInhalt.GuiArray));
				break;
		case 2: this.lv.verarbeiteNachricht(m);
				server.messageToClient(new Nachricht(9, lv.levelInhalt.GuiArray));
				break;
		case 3: if(this.lv.levelGewonnen())
				{
				lv.newLevel();
				server.messageToClient(new Nachricht(9, lv.levelInhalt.GuiArray));
				}
		case 4: this.lv.verarbeiteNachricht(m);
				server.messageToClient(new Nachricht(9, lv.levelInhalt.GuiArray));
				break;
		case 5: break;
		case 11:this.lv.verarbeiteNachricht(m);
				server.messageToClient(new Nachricht(9, lv.levelInhalt.GuiArray));
				break;
		case 20: 
			server.messageToClient(new Nachricht(20,lv.l1.GuiArray));
			break;
		default: break;
			
		}
	}
	public static void main(String[]args){
		ServerKommunikationTest neu = new ServerKommunikationTest();
		while(true){
			neu.handler();
		}
	}
}
