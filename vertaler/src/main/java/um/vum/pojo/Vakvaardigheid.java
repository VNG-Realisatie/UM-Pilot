package um.vum.pojo;

/**
 * @author ronny
 * Het vermogen om een handeling in een bepaalde mate bekwaam uit te voeren.
 */
public class Vakvaardigheid {
	   private String omschrijving;

	    public String getOmschrijving() { return omschrijving; }
	    /**
	     * @param value maxLength: 120
	     * De naamgeving van een handeling die in een bepaalde mate bekwaam uitgevoerd kan worden.
	     */
	    public void setOmschrijving(String value) {
		    if (value.length() < 121) {
		    	omschrijving = value;
		    } else {
		    	System.out.println("Omschrijving vakbekwaamheid \"" + value + "\" mag niet langer zijn dan 120 characters.");	    		    	
		    }
	    }

}
