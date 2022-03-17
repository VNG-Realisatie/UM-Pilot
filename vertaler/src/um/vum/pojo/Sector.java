package um.vum.pojo;

/**
 * @author ronny
 * De sector bedroeps- en bedrijfsleven waarin de werkzoekende bemiddeld wenst te worden.
 */
public class Sector {
    private Integer codeSbi;

    public int getCodeSbi() { return codeSbi; }
    /**
     * @param value
     * De code die de indeling van een bedrijf aangeeft volgens de Standaard Bedrijfs Indeling van het Centraal Bureau voor de Statistiek.
     */
    public void setCodeSbi(int value) {
	    if (value < 100000) {
	    	codeSbi = value;
	    } else {
	    	System.out.println("codeSbi \"" + value + "\" mag niet groter zijn dan 99999.");	    		    	
	    }
    }
}

