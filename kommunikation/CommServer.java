package kommunikation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

import GUI.Nachricht;


/**
* Das andere Ende der Kommunikation. Für Die Berechnung, Reaktionen, usw auf die Befehle des Clients 
* ist u.a die Spielweltverwaltung zuständig. Schlangen inputQ und outputQ werden dieser zur Verfuegung gestellt
* @author Max Wuestenberg  
*/

	 public class CommServer{
		 
		private ConcurrentLinkedQueue<Nachricht> inputQ = new ConcurrentLinkedQueue<>();
		private ConcurrentLinkedQueue<Nachricht> outputQ = new ConcurrentLinkedQueue<>();
		private ServerSocket serverSocket = null;
		public Socket client = null;
		InputStream is = null;
		OutputStream os = null;
		ObjectOutputStream outStream = null;
		ObjectInputStream inStream = null;
		Date stillThere;
		StillThereCheck stc;
		ListenFromClient lfc;
		int port;
		boolean serverIsOpen=true;
		boolean connected;
		
		/**
		 * Konstruktor der Klasse CommServer, in welchem die Verbindung geschlossen wird falls
		 * der Client sich einwählt. Diese erzeugte Verbindung wird schliesslich von der Methode 
		 * connectionHandle verwaltet.
		 * @param port, dem Client zur Verfügung gestellter Port, diese Integer "Zahl" muss verwendet werden.
		 * @throws ClassNotFoundException
		 * @throws InterruptedException
		 * @author Max Wüstenberg 5792312
		 */
		
		public CommServer(int port) throws ClassNotFoundException, InterruptedException {

			this.port = port;
			
			try {
				serverSocket = new ServerSocket(port);
				//stc = new StillThereCheck();
				//stc.start();
				client = serverSocket.accept();
				connected = client.isConnected();
				lfc=new ListenFromClient(client);
				lfc.start();
				System.out.println("gestartet");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Input des Clients wird in der Queue InputQ gespeichert, sie wird der (ServerEngine)/Spielweltverwaltung d
		 * bereitgestellt.
		 * wenn die Befehle bearbeitet wurden, Berechnungen abgeschlossen sind usw. generiert uns die Spielweltv. e
		 * eine Queue outputQ. Die einzelnen Elemente dieser Schlange werden zurück zum Client geschickt
		 * @param client Verbindungsglied des Servers
		 * @throws ClassNotFoundException falls kein socket gefunden wurde
		 * @author Max Wuestenberg 5792312
		 * @throws IOException 
		 * @throws InterruptedException 
		 */
		
		/**Beim Aufruf dieser Methode wird Die Verbindung zum Client gekappt,
		 * vorher werden die Streams geschlossen für einen reibunglosen Abbruch.
		 * @author Max Wüstenberg 5792312
		 */
		
		public void stopConnection(){
			try{
				if(outStream!=null)
					outStream.close();
					System.out.println("outstream closed");
			}catch(Exception e){}
			try{
				if(inStream != null)
					inStream.close();
					System.out.println("instream closed");
			}catch(Exception e){}
			try{
				if(client!=null)
					client.close();
					System.out.println("client closed");
			}catch(Exception e){}
		}
		
		public Nachricht getNextMessage(){
			return inputQ.poll();
		}
		
		public void addToOutputQ(Nachricht msg){
			outputQ.add(msg);
		}
		
		public void stopLfc(){
			serverIsOpen = false;
		}
		
		/** 
		 * arbeitet die verschiedenen typen der inputMsg ab
		 * @param InputMsg
		 * @throws IOException
		 * @author Max Wüstenberg 5792312
		 */
		
		
		/*public void handleMessage (Nachricht InputMsg) throws IOException {
			
			switch (InputMsg.getTyp()) {
			case 8:
				inputQ.add(InputMsg);	
				Nachricht msg = new Nachricht (8, "Pong");
				messageToClient(msg);
				break;
			case 3:
				inputQ.add(InputMsg);
				messageToClient(new Nachricht(9,"Level geschafft"));
				break;					//folgen weitere!
				
			case 1: 
				inputQ.add(InputMsg); // ab hier kümmert sich die ServerEngine
				messageToClient(new Nachricht(1));
				break;
			case 5:
				inputQ.add(InputMsg);
				if (InputMsg.getMessage().equals("Logout")){
					Nachricht m = new Nachricht(5, "Logout-Sucsess");
					messageToClient(m);	
				}
				break;
			case 0:
				inputQ.add(InputMsg);
				if(InputMsg.getMessage().equals("max")){
					Nachricht msg1 = new Nachricht(5,"Login sucseed");
					messageToClient(msg1);
				}else{
					Nachricht msg2 = new Nachricht(5, "wrong Password");
					messageToClient(msg2);
				}
				break;
			default:
			}
		}*/
		
		/**
		 * Speichert Nachricht in die Sendeschlange und schickt diese in Richtung Client
		 * @param msg ist ein Objekt der Klasse Nachricht und wird verschickt
		 * @author Max Wüstenberg 5792312
		 */
		
		public synchronized void messageToClient(Nachricht msg){
			if(!client.isConnected()){
				stopConnection();
			}
			try {
				outputQ.add(msg);
				lfc.outStream.writeObject(msg);
				lfc.outStream.flush();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	
	/**	Klasse prüft fortwährend ob die Verbindung zum Cleint noch besteht.
	 * wenn keine "ich bin noch da"-Nachricht innerhalb von 3-4 sek. empfangen wird, 
	 *Verbindungsabbruch!
	 * @author Max Wüstenberg 5792312
	 */
		
	public class StillThereCheck extends Thread{
			boolean run = true;
			public void run(){
				try{
					while(run){
						if(stillThere != null){
							long timer = new Date().getTime() - stillThere.getTime();
							if(timer>3000)
								//dump();
								//this.stop();
								run = false;
							System.out.println("Still There");
						}
						Thread.sleep(500);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				stopLfc(); // stoppe LFC
			}
	}	
	
	public class ListenFromClient	extends Thread{
		
		Socket client;
		ObjectInputStream inStream;
		ObjectOutputStream outStream;
		
		ListenFromClient(Socket socket){
			this.client = socket;
			try{
				outStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				outStream.flush();
				inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			}catch(IOException e){
				e.printStackTrace();
				return;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public void run(){	
		Nachricht command;
		//stc = new StillThereCheck();
		//stc.start();	
		stillThere = null;
			while (serverIsOpen) {
				try{
					command = (Nachricht) inStream.readObject();
					if (command != null){
						stillThere = new Date();
						//handleMessage(command);
						inputQ.add(command);
						//Thread.sleep(100);
					}
				}catch(IOException e){
					e.printStackTrace();
					break;
				}catch(ClassNotFoundException cnf_event){
					cnf_event.printStackTrace();
					break;
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		stopConnection();
		}
	}
}
