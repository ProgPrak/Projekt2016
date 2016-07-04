package probe;

public class Client {

	int id;
	Message[] Nachrichten;
	int stelle;
	
	public Client(int i){
		this.id=i;
		Nachrichten = new Message[100];
		stelle=0;
	}
	
	public void sende(Message m){
		Nachrichten[stelle]=m;
		stelle++;
	}
	
	public void ausgabe(){
		for(int i=0;i<stelle;i++){
			Message m = Nachrichten[i];
			
				switch (m.getType()){
					case 1: System.out.println("Position des Spielers: " + m.getxKoo()+ ", " + m.getyKoo());break;
					case 2: System.out.println("Der Trank an der Position " + m.getxKoo() + ", " + m.getyKoo() + " wurde aufgenommen");break;
					case 3: System.out.println("Das Level wurde abgeschlossen!");break;
					case 4: System.out.println("Der Schlüssel an der Stelle "+m.getxKoo()+", "+m.getyKoo()+" wurde aufgenommen");break;
					case 5: System.out.println("");break;
				}
			
		}
	}
	/*
	 * Nachrichtentypen
	 * 1 - Eigene Position
	 * 2 - Heiltrankposition
	 * 3 - Level Abgeschlossen
	 * 4 - Schlüssel aufgenommen
	 */
}
