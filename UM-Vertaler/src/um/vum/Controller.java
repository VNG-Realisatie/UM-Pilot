package um.vum;

import java.util.ArrayList;
import java.util.List;

import um.vum.pojo.WerkzoekendeProfiel;

public class Controller {

	/**
	 * Controleert of de ID's uniek zijn en of verplichte velden ook daadwerkelijk gevuld zijn
	 * @param werkzoekendeProfielen
	 * @return werkzoekendeProfielen na de check
	 */
	public static WerkzoekendeProfiel[] CheckWP(WerkzoekendeProfiel[] werkzoekendeProfielen) {
		List<WerkzoekendeProfiel> result = new ArrayList<WerkzoekendeProfiel>();
		
		for (WerkzoekendeProfiel wpSource : werkzoekendeProfielen){
			
			// TODO Controlleer eerst of de verplichte velden ook echt gevuld zijn.
			  String id = wpSource.getIdWerkzoekende();

			  if (id == null || id.isBlank()) {
		    	  System.out.println("werkzoekendeID is niet gevonden maar is wel verplicht.");
				  continue;
			  }
			
			  boolean found = false;
		      for (WerkzoekendeProfiel wpTarget : result) {
		          if (id.equals(wpTarget.getIdWerkzoekende())) {
		        	  found = true;
		          }
		      }
		      if (found) {
		    	  System.out.println("Dubbel werkzoekendeID " + id + "gevonden");
		      } else {
	              result.add(wpSource);		    	  
		      }
		}
		return werkzoekendeProfielen;
	}

}
