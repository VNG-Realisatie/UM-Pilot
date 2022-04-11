package um.vum.vertalers;

import um.vum.pojo.WerkzoekendeProfiel;

public class SonarTranslate implements Vertaler{
	
	@SuppressWarnings("unused")
	public void vertaal(WerkzoekendeProfiel[] werkzoekendeProfielen) {

		System.out.println("Bezig met vertalen van aangeboden Excel-gegevens naar VUM formaat (Stap2/3).");		

		for (WerkzoekendeProfiel wzp : werkzoekendeProfielen){
        	try {
				// Hier wordt het werk gedaan maar vooralsnog hoeft hier niet vertaald te worden.
        	} finally {
        		
        	}
        }
		
	}

}
