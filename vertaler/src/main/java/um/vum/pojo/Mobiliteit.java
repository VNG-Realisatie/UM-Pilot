package um.vum.pojo;

import java.util.regex.Pattern;

/**
 * @author ronny
 * Gewenste reisafstand woon-werk.
 */
public class Mobiliteit {

    private String bemiddelingspostcode;
    private Integer maximaleReisafstand;
    private Integer maximaleReistijd;

    public String getBemiddelingspostcode() { return bemiddelingspostcode; }
    /**
     * @param value
     * De postcode van waaruit de werkzoekende bemiddeld wenst te worden. 
     */
    public void setBemiddelingspostcode(String value) {
    	value = value.toUpperCase();
	    String regex = "[1-9][0-9]{3}(?!SA|SD|SS)[A-Z]{2}";
	    if (Pattern.matches(regex, value)) {
			bemiddelingspostcode = value;
	    } else {
	    	System.out.println("Bemiddelingspostcode \"" + value + "\" is niet in een geldig formaat.");
	    }
    }

    public int getMaximaleReisafstand() { return maximaleReisafstand; }
    /**
     * @param value
     * De maximale afstand, uitgedrukt in kilometers, waarbinnen de werkzoekende wil worden bemiddeld.
     */
    public void setMaximaleReisafstand(int value) {
	    if (value < 1000) {
	    	this.maximaleReisafstand = value;
	    } else {
	    	System.out.println("MaximaleReisafstand \"" + value + "\" mag niet groter zijn dan 999.");	    	
	    }
    }

    public int getMaximaleReistijd() { return maximaleReistijd; }
    /**
     * @param value
     * De maximale acceptabele reistijd in minuten die de werkzoekende accepteert in verband met het verrichten van werk.
     */
    public void setMaximaleReistijd(int value) {
	    if (value < 1000) {
	    	this.maximaleReistijd = value;
	    } else {
	    	System.out.println("MaximaleReistijd \"" + value + "\" mag niet groter zijn dan 999.");	    		    	
	    }
    }
}
