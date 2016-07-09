package serverengine_sw;
import kommunikation.*;
import GUI.Nachricht;
public class TestClient {
	public static void main(String[]args){
		Nachricht login = new Nachricht (0,"HalloWelt","Tschuesswelt");
		CommClient ccTest = new CommClient ("localhost",7891);
		ccTest.addToSendQ(login);
		ccTest.startingProcess();
	}

}
