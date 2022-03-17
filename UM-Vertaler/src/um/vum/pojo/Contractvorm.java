package um.vum.pojo;

import java.util.regex.Pattern;

/**
 * @author ronny
 * De contractvorm welke van toepassing is.
 */
public class Contractvorm {
    private String codeTypeArbeidscontract;
    private String codeTypeOvereenkomst;
    
	/**
	 * Geeft de mogelijke waarden voor Arbeidscontract
	 */
	public enum CodeArbeidscontract { 
		BEPAALDE_TIJD("B"), ONBEPAALDE_TIJD("O");
		
		public final String label;
		
		private CodeArbeidscontract(String label) {
			this.label = label;
		}
	}
	
	/**
	 * Geeft de mogelijke waarden voor TypeOvereenkomst
	 */
	public enum TypeOvereenkomst { 
		REGULIERE_VACATURE("01"), LEERBAAN("O2"), VRIJWILLIGERSWERK("03"), WERKERVARINGSPLAATS("04");
		
		public final String label;
		
		private TypeOvereenkomst(String label) {
			this.label = label;
		}
	}

	
    public String getCodeTypeArbeidscontract() { return codeTypeArbeidscontract; }
    /**
     * @param value
     * De code die aangeeft of het arbeidscontract voor bepaalde of onbepaalde tijd van toepassing is.
     * Zie enum CodeArbeidscontract voor mogelijke waarden.
     */
    public void setCodeTypeArbeidscontract(String value) { 
	    String regex = "[BO]";
	    if (Pattern.matches(regex, value)) {
	    	codeTypeArbeidscontract = value; 
	    } else {
    		System.out.println("Voor codeTypeArbeidscontract is alleen geldig B = Bepaalde tijd en O = Onbepaalde tijd");			
    	}  	

    }

    public String getCodeTypeOvereenkomst() { return codeTypeOvereenkomst; }
    /**
     * @param value
     * De code die aangeeft welk type overeenkomst van toepassing is.
     * Zie enum TypeOvereenkomst voor mogelijke waarden.
     */
    public void setCodeTypeOvereenkomst(String value) {
	    String regex = "^01$|^02$|^03$|^04$";
	    if (Pattern.matches(regex, value)) {
	       	codeTypeOvereenkomst = value; 
	    } else {
    		System.out.println("Voor setCodeTypeOvereenkomst is alleen geldig 01 = Reguliere vacature - 02 = Leerbaan - 03 = Vrijwilligerswerk - 04 = Werkervaringsplaats");			
    	}  	

    }
}
