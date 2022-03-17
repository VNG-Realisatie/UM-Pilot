package um.vum.vertalers;

import um.vum.pojo.WerkzoekendeProfiel;
//import um.vum.pojo.WerkzoekendeProfielException;

public class SonarTranslate implements Vertaler{
	
	@SuppressWarnings("unused")
	public void vertaal(WerkzoekendeProfiel[] werkzoekendeProfielen) {

		System.out.println("Bezig met vertalen van aangeboden Excel-gegevens naar VUM formaat (Stap2/3).");		

		for (WerkzoekendeProfiel wzp : werkzoekendeProfielen){
        	try {
				// TODO Hier wordt het werk gedaan
        	} finally {
        		
        	}
        }
		
	}

}
