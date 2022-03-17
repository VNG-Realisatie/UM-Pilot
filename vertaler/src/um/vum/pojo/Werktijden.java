package um.vum.pojo;

import java.util.regex.Pattern;

/**
 * @author ronny
 * De mate waarin de werkzoekende beschikbaar is voor werk.
 */
public class Werktijden {
	private Integer aantalWerkurenPerWeekMinimaal;
	private Integer aantalWerkurenPerWeekMaximaal;
	private Integer indicatieKantoortijden;
	
	
	/**
	 * Geeft de mogelijke waarden voor IndicatieKantoortijden
	 */
	public enum IndicatieKantoortijden { 
		ONBEKEND(0), JA(1), NEE(2);
		
		public final int label;
		
		private IndicatieKantoortijden(int label) {
			this.label = label;
		}
	}

	public int getAantalWerkurenPerWeekMinimaal() { return aantalWerkurenPerWeekMinimaal; }
	/**
	 * @param value Het aantal dat de hoeveelheid minimale werkuren per week aangeeft.
	 */
	public void setAantalWerkurenPerWeekMinimaal(int value) {
	    if (value < 100) {
	    	aantalWerkurenPerWeekMinimaal = value;
	    } else {
	    	System.out.println("AantalWerkurenPerWeekMinimaal \"" + value + "\" mag niet groter zijn dan 99.");	    		    	
	    }
	}

	public int getAantalWerkurenPerWeekMaximaal() { return aantalWerkurenPerWeekMaximaal; }
	/**
	 * @param value Het aantal dat de hoeveelheid maximale werkuren per week aangeeft.
	 */
	public void setAantalWerkurenPerWeekMaximaal(int value) {
	    if (value < 100) {
	    	aantalWerkurenPerWeekMaximaal = value;
	    } else {
	    	System.out.println("AantalWerkurenPerWeekMaximaal \"" + value + "\" mag niet groter zijn dan 99.");	    		    	
	    }
	}

	public int getIndicatieKantoortijden() { return indicatieKantoortijden; }
	/**
	 * @param value
	 * Indicatie die aangeeft of er sprake is van het deel van een dag waarin kantoorwerkzaamheden traditioneel (overdag) worden verricht.
	 * Zie enum voor mogelijke waarden
	 */
	public void setIndicatieKantoortijden(int value) {
	    String regex = "[012]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
			indicatieKantoortijden = value; 
	    } else {
    		System.out.println("Voor indicatieKantoortijden is alleen geldig 0 = onbekend, 1 = Ja en 2 = Nee");			
    	}
	}
}
