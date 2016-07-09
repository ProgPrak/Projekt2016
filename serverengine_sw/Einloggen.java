package serverengine_sw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;

public class Einloggen {
	
        private String Nutzer;
        private String PW;
        public LevelverwaltungNeu SWV;
		private static String  nutzer;
	
		/*public static boolean einloggenErfolgreich(String benutzername, String passwort){
			boolean erfolgreich = false;
		
				if (nutzer!= null && nutzer.equals(benutzername+passwort)){
					System.out.println("Willkommen zur�ck");
					return true;
				}
			return erfolgreich;
		}*/
	
		public  boolean LogIn(String benutzername, String passwort){
			try{
				boolean fertig = false;
				FileInputStream fileinptstrm = new FileInputStream("./src/Spielweltverwaltung/NutzerDaten.txt");
				InputStreamReader inptstrmreader = new InputStreamReader(fileinptstrm);
				BufferedReader buffreader = new BufferedReader(inptstrmreader);
				String rLine = buffreader.readLine();
				String Nutzerdaten="";
				while(rLine != null)
				{
					if (rLine.equals(benutzername+passwort)){
						System.out.println("Willkommen zur�ck");
						fertig = true;
						Nutzerdaten  = rLine;
						SWV= new LevelverwaltungNeu();
						break;
					}else{
						Nutzerdaten  = rLine;
						rLine = buffreader.readLine();
						
					}
				}
				if (!fertig)
				{
					buffreader.close();  
					System.out.println("Hallo neuer Spieler");
					BufferedWriter buffwriter = new BufferedWriter(new FileWriter("./src/Spielweltverwaltung/Nutzerdaten.txt", false));
					
					
						buffwriter.write(Nutzerdaten);
						buffwriter.write(System.getProperty("line.separator"));
					
					buffwriter.write(benutzername+passwort);
					buffwriter.flush();
		        	buffwriter.close();
		        	fertig = true;
				}
	        	return true;
			}catch(IOException e){
			System.out.println("Fehler " + e);
			return false;
		}
	}
}
