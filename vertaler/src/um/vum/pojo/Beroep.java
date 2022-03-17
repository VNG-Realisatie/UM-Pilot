package um.vum.pojo;

/**
 * @author ronny
 * TODO Nog kijken of dit klopt 
 * Dit object wijkt af van de YAML en wat ik verwacht vanuit VUM
 */
public class Beroep {
    private String codeBeroepsnaam;
    private String omschrijvingBeroepsnaam;
    private String naamBeroepOngecodeerd;

    public String getCodeBeroepsnaam() { return codeBeroepsnaam; }
    /**
     * @param value
     */
    public void setCodeBeroepsnaam(String value) {
    	
	    if (value.length() < 11) {
	    	this.codeBeroepsnaam = value;
	    } else {
	    	System.out.println("CodeBeroepsnaam \"" + value + "\" mag niet meer dan 10 characters lang zijn.");	    	
	    }
    }

    public String getOmschrijvingBeroepsnaam() { return omschrijvingBeroepsnaam; }
    /**
     * @param value
     */
    public void setOmschrijvingBeroepsnaam(String value) {
	    if (value.length() < 121) {
	    	this.omschrijvingBeroepsnaam = value;
	    } else {
	    	System.out.println("omschrijvingBeroepsnaam \"" + value + "\" mag niet meer dan 120 characters lang zijn.");	    	
	    }
    }

    public String getNaamBeroepOngecodeerd() { return naamBeroepOngecodeerd; }
    /**
     * @param value
     */
    public void setNaamBeroepOngecodeerd(String value) { 
	    if (value.length() < 121) {
	    	this.naamBeroepOngecodeerd = value;
	    } else {
	    	System.out.println("naamBeroepOngecodeerd \"" + value + "\" mag niet meer dan 120 characters lang zijn.");	    	
	    }
    }

}
