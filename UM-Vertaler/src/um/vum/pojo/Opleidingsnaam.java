package um.vum.pojo;

/**
 * @author ronny
 * De naamgegevens van een OPLEIDING. Norminstantie SGR
 * TODO opleiding ongecodeerd / gecodeerd is anders verwerkt
 */
public class Opleidingsnaam {
    // Opleidingsnaam Gecodeerd
    private Long codeOpleidingsnaam;
    private String omschrijvingOpleidingsnaam;
    // Opleidingsnaam Ongecodeerd
    private String naamOpleidingOngecodeerd;
    private String omschrijvingOpleiding;
    
    
    public long getCodeOpleidingsnaam() { return codeOpleidingsnaam; }
    /**
     * @param value onderdeel van OpleidingsnaamGecodeerd, maximum: 9999999999
     * De unieke code van een OPLEIDINGSNAAM.
     */
    public void setCodeOpleidingsnaam(long value) {
		if (value < Long.valueOf(10000000000L) ) {
			codeOpleidingsnaam = value;
	    } else {
    		System.out.println("codeOpleidingsnaam is groter dan 9999999999");			
    	}
    }

    public String getOmschrijvingOpleidingsnaam() { return omschrijvingOpleidingsnaam; }
    /**
     * @param  onderdeel van OpleidingsnaamGecodeerd, value maxLength: 120
     * De naam van de OPLEIDING.
     */
    public void setOmschrijvingOpleidingsnaam(String value) {
	    if (value.length()<121 ) {
	    	omschrijvingOpleidingsnaam = value;
	    } else {
    		System.out.println("omschrijvingOpleidingsnaam is langer dan 120 characters");			
    	}
    }
    
    public String getNaamOpleidingOngecodeerd() { return naamOpleidingOngecodeerd; }
    /**
     * @param  onderdeel van OpleidingsnaamOngecodeerd, value maxLength: 120
     * De omschrijving van de OPLEIDING.
     */
    public void setNaamOpleidingOngecodeerd(String value) {
	    if (value.length()<121 ) {
	    	naamOpleidingOngecodeerd = value;
	    } else {
    		System.out.println("NaamOpleidingOngecodeerd is langer dan 120 characters");			
    	}
    }

    public String getOmschrijvingOpleiding() { return omschrijvingOpleiding; }
    /**
     * @param onderdeel van OpleidingsnaamOngecodeerd, value maxLength: 2000
     * De eigen omschrijving van de OPLEIDING.
     */
    public void setOmschrijvingOpleiding(String value) {
	    if (value.length()<2001 ) {
	    	omschrijvingOpleiding = value;
	    } else {
    		System.out.println("omschrijvingOpleiding is langer dan 2000 characters");			
    	}
    }
    
}