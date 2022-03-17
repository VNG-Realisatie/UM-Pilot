package um.vum.vertalers;

import um.vum.pojo.WerkzoekendeProfiel;

public class DummyTranslate implements Vertaler{

	public void vertaal(WerkzoekendeProfiel[] werkzoekendeProfielen) {
		System.out.println("Bezig met vertalen van aangeboden gegevens naar VUM formaat (Stap2/3).");		
	}

}
