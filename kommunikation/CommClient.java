package kommunikation;

import shared.Message;
import java.net.Socket;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * 
 * in dieser Klasse wird eine Verbindung zum Server hergstellt, falls möglich
 * die verbindung, Austausch von Informationen wird von der Methode connecting() realisiert   
 * @author Max Wuestenberg
 *
 */

public class CommClient{

	private ConcurrentLinkedQueue<Message> receivingQ = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Message> sendingQ = new ConcurrentLinkedQueue<>();
	Socket server = null;
	InputStream is = null;
	OutputStream os = null;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private boolean isOpen;
	
	/**
	 * Konstruktor wird ausgestattet mit unten angegebenen Parametern. Die Socket-Verbindung zum Server wird
	 * hergestellt, indem die Web-Adresse und der zuvor bestimmte Port gebracuht werden.
	 * Des weitern werden Output und InputStream erzeugt um Austausch von Objekten zu gewährleisten.
	 * @param targetAdd Adresse des Servers
	 * @param sPort	alles über ... ist hier in Ordnung
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
	 * gefüllt ist. Falls dies der Fall ist. wird die Nachricht vom Kopf versan
	 * 
	 */
	
	public void startingProcess(){
		ServerListener s = new ServerListener();
		s.start();
		//PingMsg p = new PingMsg();
		//p.start();
		isOpen = true;
		try{
			int waiting=0;
			while(isOpen){
				if(sendingQ.isEmpty()){
					waiting++;
					Thread.sleep(1000);
				}else{
					Message msg = (Message)sendingQ.poll();
					this.sendMessage(msg);
					waiting =0;
					Thread.sleep(1000);	
				}
				
				if(waiting>5){
					isOpen = false;
					Message logout = new Message(5,"Logout");
					this.sendMessage(logout);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/** 
	 * Input des Client wird vom Server bearbeitet und die Antwort des Servers kann verwaltet werden.
	 * Der Output welcher vom InputStream gelesen wird, speichert die Methode in der Queue inputQ ab
	 * @param der zu bearbeitende Befehl
	 * @author Max Wuestenberg 
	 */
	public synchronized void sendMessage(Message clientCommand) throws ClassNotFoundException {
			
		try {
				
			outputStream.writeObject((Message) clientCommand);
			outputStream.flush();
			outputStream.reset();
			System.out.println("Message sent: "+ clientCommand.getTyp());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error sending..");
		}
	}
	/**
	 * elementare Methode für den Client, "Antworten" des Servers werden aus Queue geholt
	 * @return das element welches am längsten in der Queue verweilte
	 * @author Max Wuestenberg 
	 */
	public Message getNextMessage(){
		return receivingQ.poll();	
	}
	
	/**
	 * 
	 * @param msg
	 */
	public void addToSendQ(Message msg){
		sendingQ.add(msg);	
	}
	
	/**
	 * 
	 */

	public void disconnect(){
		System.out.println("einmal abmelden bitte");
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
				while(true){
					sendingQ.add(new Message(8, "Ping"));
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
					Message incomMsg = (Message) inputStream.readObject();
					if(incomMsg!= null){
						System.out.println("saving Msg");
						receivingQ.add(incomMsg);
						System.out.println(incomMsg.getMessage());
						if(incomMsg.getMessage().equals("Logout-Sucsess")){
							this.stop();
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