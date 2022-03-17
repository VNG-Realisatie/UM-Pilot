package um.vum.pojo;

import java.util.regex.Pattern;

/**
 * @author ronny
 * Een object dat bedoeld is om personen of goederen te vervoeren.
 */
public class Vervoermiddel {

    private Integer indicatieBeschikbaarVoorUitvoeringWerk;
    private Integer indicatieBeschikbaarVoorWoonWerkverkeer;
    private Integer codeVervoermiddel;
    
    
	/**
	 * Geeft de mogelijke waarden voor CodeVervoermiddel
	 */
	public enum TypeVervoermiddel { 
		AUTO(1), BROMFIETS(2), FIETS(3), MOTOR(4), OPENBAAR_VERVOER(5), ANDERS(9);
		
		public final int label;
		
		private TypeVervoermiddel(int label) {
			this.label = label;
		}
	}


    public int getIndicatieBeschikbaarVoorUitvoeringWerk() { return indicatieBeschikbaarVoorUitvoeringWerk; }
    /**
     * @param value
     * Indicatie die aangeeft of er een vervoermiddel beschikbaar is voor het uitvoeren van de functie.
     */
    public void setIndicatieBeschikbaarVoorUitvoeringWerk(int value) { 
	    String regex = "[012]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	indicatieBeschikbaarVoorUitvoeringWerk = value;
	    } else {
    		System.out.println("Voor indicatieBeschikbaarVoorUitvoeringWerk is alleen geldig 0 = onbekend, 1 = Ja en 2 = Nee");			
    	}
    	
    }

    	public int getIndicatieBeschikbaarVoorWoonWerkverkeer() { return indicatieBeschikbaarVoorWoonWerkverkeer; }
    /**
     * @param value
     * Indicatie die aangeeft of de werkzoekende het vervoermiddel ter beschikking heeft voor woon-werkverkeer.
     */
    public void setIndicatieBeschikbaarVoorWoonWerkverkeer(int value) {
	    String regex = "[012]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
    		indicatieBeschikbaarVoorWoonWerkverkeer = value;
	    } else {
    		System.out.println("Voor indicatieBeschikbaarVoorWoonWerkverkeer is alleen geldig 0 = onbekend, 1 = Ja en 2 = Nee");			
    	}
    }

    public int getCodeVervoermiddel() { return codeVervoermiddel; }
    /**
     * @param value
     * De code die het soort vervoermiddel aangeeft.
     */
    public void setCodeVervoermiddel(int value) {
	    String regex = "[123459]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	codeVervoermiddel = value;
	    } else {
	    	System.out.println("CodeVervoermiddel \"" + value + "\" heeft ongeldige waarde.");
	    }
    }

}
