package serverengine_sw;
import Kommunikation.*;
public class TestClient {
	public static void main(String[]args){
		Message login = new Message (0,"HalloWelt","Tschuesswelt");
		CommClient ccTest = new CommClient ("localhost",7891);
		ccTest.addToSendQ(login);
		ccTest.startingProcess();
	}

}
