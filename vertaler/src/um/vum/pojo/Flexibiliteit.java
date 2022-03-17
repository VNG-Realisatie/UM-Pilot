package um.vum.pojo;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * @author ronny
 * De bereidheid van de werkzoekende om concessies te doen aan de eisen die hij stelt aan het aanvaarden van arbeid. Norminstantie SGR
 */
public class Flexibiliteit {

    private Integer codeRegiostraal;
    private Integer indicatieOnregelmatigWerkOfPloegendienst;
    private LocalDate datumAanvangBeschikbaarVoorWerk;
    private LocalDate datumEindeBeschikbaarVoorWerk;

    public int getCodeRegiostraal() { return codeRegiostraal; }
    /**
     * @param value
     */
    public void setCodeRegiostraal(int value) {
	    if (value < 10) {
	    	codeRegiostraal = value;
	    } else {
	    	System.out.println("CodeRegiostraal \"" + value + "\" mag niet groter zijn dan 9.");	    		    	
	    }
    }

    public int getIndicatieOnregelmatigWerkOfPloegendienst() { return indicatieOnregelmatigWerkOfPloegendienst; }
    /**
     * @param value
     * Indicatie die aangeeft of het werk op onregelmatige tijden en/of in ploegendienst plaatsvindt.
     */
    public void setIndicatieOnregelmatigWerkOfPloegendienst(int value) { 
	    String regex = "[0,1,2]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	indicatieOnregelmatigWerkOfPloegendienst = value; 
	    } else {
    		System.out.println("Voor indicatieOnregelmatigWerkOfPloegendienst is alleen geldig 0 = onbekend, 1 = Ja en 2 = Nee");			
    	}  	
    }

    public LocalDate getDatumAanvangBeschikbaarVoorWerk() { return datumAanvangBeschikbaarVoorWerk; }
    /**
     * @param value De datum van de eerste werkdag.
     */
    public void setDatumAanvangBeschikbaarVoorWerk(LocalDate value) {
    	this.datumAanvangBeschikbaarVoorWerk = value; 
    	}

    public LocalDate getDatumEindeBeschikbaarVoorWerk() { return datumEindeBeschikbaarVoorWerk; }
    /**
     * @param value De datum van de laatste werkdag.
     */
    public void setDatumEindeBeschikbaarVoorWerk(LocalDate value) {
    	this.datumEindeBeschikbaarVoorWerk = value; 
    	}
}
