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


			  // TODO Controleer of alle verplichte velden ook echt gevuld zijn.
			
              // Controleer of het profiel een ID heeft. 			
			  String id = wpSource.getIdWerkzoekende();
			  if (id == null || id.isBlank()) {
		    	  System.out.println("- werkzoekendeID is niet gevonden maar is wel verplicht.");
				  continue;
			  }

			  
			  // Controleer of het werkzoekendeID uniek is
	    	  boolean duplicate = false;
		      for (WerkzoekendeProfiel wpTarget : result) {
		          if (id.equals(wpTarget.getIdWerkzoekende())) {
			    	  duplicate = true;
			    	  System.out.println("- Dubbel werkzoekendeID " + id + " gevonden");
			    	  break;
		          }
		      }
		      if (duplicate) { continue; }

		      
			  // Controleer of er toestemming is gegeven tot delen.
			  boolean toestemming = wpSource.isToestemmingTotDelenProfiel();
			  if (!toestemming) {
		    	  System.out.println("- werkzoekende met ID "+ id +" heeft geen toestemming gegeven tot delen en wordt daarom niet bij UM aangeboden.");
				  continue;
			  }

		      result.add(wpSource);		    	  

		}
		return result.toArray(new WerkzoekendeProfiel[0]);
	}

}
