package kommunikation;

import java.net.Socket;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import GUI.Nachricht;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * 
 * in dieser Klasse wird eine Verbindung zum Server hergstellt, falls m�glich
 * die verbindung, Austausch von Informationen wird von der Methode connecting() realisiert   
 * @author Max Wuestenberg
 *
 */

public class CommClient{

	private ConcurrentLinkedQueue<Nachricht> receivingQ = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Nachricht> sendingQ = new ConcurrentLinkedQueue<>();
	Socket server = null;
	InputStream is = null;
	OutputStream os = null;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private boolean isOpen;
	
	/**
	 * Konstruktor wird ausgestattet mit unten angegebenen Parametern. Die Socket-Verbindung zum Server wird
	 * hergestellt, indem die Web-Adresse und der zuvor bestimmte Port gebracuht werden.
	 * Des weitern werden Output und InputStream erzeugt um Austausch von Objekten zu gew�hrleisten.
	 * @param targetAdd Adresse des Servers
	 * @param sPort	alles �ber ... ist hier in Ordnung
	 */

	public CommClient(String targetAdd, int sPort) {
		
		try {
			//Socket wird erstellt
			server = new Socket(targetAdd, sPort);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println("Connected to: " + server); //Test
		
		try{
			outputStream = new ObjectOutputStream(new BufferedOutputStream(server.getOutputStream()));
			outputStream.flush();
			inputStream = new ObjectInputStream(new BufferedInputStream(server.getInputStream()));	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** startet Nachrichtenaustausch vom Client aus. Diese Methode schaut, ob die Sendeschlange mit Objekten 
	 * gef�llt ist. Falls dies der Fall ist. wird die Nachricht vom Kopf versan
	 * 
	 */
	
	public void startingProcess(){
		ServerListener s = new ServerListener();
		s.start();
		//PingMsg p = new PingMsg();
		//p.start();
		isOpen = true;
	}
		/*try{
			//int waiting=0;
			while(isOpen){
				if(sendingQ.isEmpty()){
					//waiting++;
					Thread.sleep(500);
				}else{
					Nachricht msg = (Nachricht)sendingQ.poll();
					this.sendMessage(msg);
					//waiting =0;	
				}
				
				//if(waiting>5){
					//isOpen = false;
					//Nachricht logout = new Nachricht(5,"Logout");
					//this.sendMessage(logout);
				//}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/

	/** 
	 * Input des Client wird vom Server bearbeitet und die Antwort des Servers kann verwaltet werden.
	 * Der Output welcher vom InputStream gelesen wird, speichert die Methode in der Queue inputQ ab
	 * @param der zu bearbeitende Befehl
	 * @author Max Wuestenberg 
	 */
	public synchronized void sendMessage(Nachricht clientCommand) throws ClassNotFoundException {
			
		try {
			addToSendQ(clientCommand);
			outputStream.writeObject((Nachricht) clientCommand);
			outputStream.flush();
			outputStream.reset();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error sending..");
		}
	}
	/**
	 * elementare Methode f�r den Client, "Antworten" des Servers werden aus Queue geholt
	 * @return das element welches am l�ngsten in der Queue verweilte
	 * @author Max Wuestenberg 
	 */
	public Nachricht getNextMessage(){
		return receivingQ.poll();	
	}
	
	/**
	 * 
	 * @param m
	 */
	public void addToSendQ(Nachricht m){
		sendingQ.add(m);
	}
	
	/**
	 * 
	 */

	public void disconnect(){
		isOpen = false;
		try{
			if (inputStream != null)
				inputStream.close();
				System.out.println("input closed");
			if (outputStream != null)
				outputStream.close();
				System.out.println("output closed");
			if (server!= null) 
				server.close();
				System.out.println("server closed");
		}catch(IOException ioe){
			System.out.println("Error closing...");
		}
	}
	
	/**
	 * 
	 * @author mwuesten
	 *
	 */
	
	public class PingMsg extends Thread {	
		public void run(){
			try{
				while(isOpen){
					sendingQ.add(new Nachricht(8, "Ping"));
					Thread.sleep(1000);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @author mwuesten
	 *
	 */
	
	class ServerListener extends Thread {
		boolean slrun = true;
		public void run(){
			while(slrun){
				try{
					Nachricht incomMsg = (Nachricht) inputStream.readObject();
					if(incomMsg!= null){
						receivingQ.add(incomMsg);
						if(incomMsg.getMessage() != null && incomMsg.getMessage().equals("Logout-Sucess")){
							//this.stop();
							disconnect();
						}	
					}
				}catch(EOFException e){
					System.out.println("Daten vom server falsch gelesen");
					break;
				}catch(IOException ioe){
					ioe.printStackTrace();
					break;
				}catch(ClassNotFoundException cnf_event){
					cnf_event.printStackTrace();
				}
			}
		}
	}
}