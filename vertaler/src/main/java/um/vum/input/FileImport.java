package um.vum.input;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//import org.json.JSONObject;

import um.vum.pojo.ContactPersoon;
import um.vum.pojo.WerkzoekendeProfiel;

 /**
 * @author ronny
 * FileImport leest een bestand in als een CSV waarbij elementen gescheiden zijn door een puntcomma
 * 
 *
 */
public class FileImport implements Importer{
	
	private ArrayList<WerkzoekendeProfiel> werkzoekendeProfielen = new ArrayList<WerkzoekendeProfiel>();

	public FileImport() {
	}

	@Override
	public WerkzoekendeProfiel[] read(String filename) {
		
		System.out.println("Bezig met inlezen van CSV bestand \"" + filename + "\" (Stap1/3).");
		
		// Lees CSV bestand in array
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {

			inputStream = new FileInputStream(filename);
		    sc = new Scanner(inputStream, "UTF-8");
		    
		    // lees regel voor regel tot het einde van het bestand is bereikt
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		        
		        // regels die starten met een # zijn bedoelt als opmerking en worden overgeslaan
		        if (!line.startsWith("#")) {
		        	String[] elements = line.split(";");
		        	
		        	// controleer in de basis of de elementen voldoen
		        	if (checkline(elements)){
		        		
		        		// Schrijf de elementen weg in het POJO array van werkzoekenden 
		        		werkzoekendeProfielen.add(placeInObject(elements));
		        	} 
		        }
		    }
		    
		    if (sc.ioException() != null) { throw sc.ioException();}
		} catch (Exception e) {
			System.err.println("Fout met lezen invoerbestand. Melding:"+e.getLocalizedMessage());
			System.exit(2); 				
		} finally {
		    if (inputStream != null) {
		        try {
					inputStream.close();
				} catch (IOException e) {
					System.err.println("Fout met lezen invoerbestand. Melding:"+e.getLocalizedMessage());
					System.exit(2); 				
				}
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
		
		// Bestand is geheel verwerkt.
		return werkzoekendeProfielen.toArray(new WerkzoekendeProfiel[0]);
	}
	
	
	private boolean checkline(String[] elements) {
		if (elements.length < 5) {
			return false;
		}
		return true;
	}

	//plaats elementen uit regel in de juiste Werkzoekende-Objecten 
	private WerkzoekendeProfiel placeInObject(String[] elements) {
		WerkzoekendeProfiel wp = new WerkzoekendeProfiel();
		wp.setIdWerkzoekende(elements[0]);
		if (elements[1].equalsIgnoreCase("JA")) { wp.setToestemmingTotDelenProfiel(true); }
	    	else { wp.setToestemmingTotDelenProfiel(false); }
		ArrayList<ContactPersoon> contactpersonen = new ArrayList<ContactPersoon>();
		ContactPersoon cp = new ContactPersoon();
		cp.setNaamContactpersoonAfdeling(elements[2]);
		cp.setTelefoonnummer(elements[3]);
		cp.setEmailadres(elements[4]);
		contactpersonen.add(cp);
		wp.setContactpersoon(contactpersonen.toArray(new ContactPersoon[0]));		
		
		//JSONObject jsonObject = new JSONObject(wp);
        // System.out.println(jsonObject.toString());

		
		return wp;
	}

}
