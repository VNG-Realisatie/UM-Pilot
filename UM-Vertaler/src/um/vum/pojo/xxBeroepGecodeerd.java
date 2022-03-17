package um.vum.pojo;

import java.util.regex.Pattern;

public class xxBeroepGecodeerd {
    private String codeBeroepsnaam;
    private String omschrijvingBeroepsnaam;

    public String getCodeBeroepsnaam() { return codeBeroepsnaam; }
    /**
     * @param value maxLength: 10
     * De unieke code van de beroepsnaam uit het BO&C register.
     */
    public void setCodeBeroepsnaam(String value) {
	    String regex = "[0-9]*";
	    if (Pattern.matches(regex, value) && value.length()<11 ) {
	    	codeBeroepsnaam = value;
	    } else {
    		System.out.println("CodeBeroepsnaam voldoet niet aan VUM specificaties");			
    	}
    }

    public String getOmschrijvingBeroepsnaam() { return omschrijvingBeroepsnaam; }
    /**
     * @param value maxLength: 120
     * De naam van het BEROEP
     */
    public void setOmschrijvingBeroepsnaam(String value) {
	    if (value.length()<121 ) {
	    	omschrijvingBeroepsnaam = value;
	    } else {
    		System.out.println("OmschrijvingBeroepsnaam is langer dan 120 characters");			
    	}
    }

}
