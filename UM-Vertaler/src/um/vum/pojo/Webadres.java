package um.vum.pojo;

import java.util.regex.Pattern;

/**
 * @author ronny
 * De gegevens waarmee een webadres geïdentificeerd kan worden.
 */
public class Webadres {
    private Integer codeWebadres;
    private String url;

	/**
	 * Geeft de mogelijke waarden voor IndicatieKantoortijden
	 */
	public enum CodeWebadres { 
		Werkzoekende_Profiel(1), CV(2); //, Werkgever(3), Online_Sollicitatieformulier(4);
				
		public final int label;
		
		private CodeWebadres(int label) {
			this.label = label;
		}
	}
    
    public int getCodeWebadres() { return codeWebadres; }
    /**
     * @param value
     * 1 = Webadres werkzoekende profiel (webadres waarop een profielbeschrijving van de werkzoekende is te vinden)
     * 2 = Webadres CV (webadres waarop het CV van een werkzoekende te vinden is)
     * Zie enum voor mogelijke waarden
     */
    public void setCodeWebadres(int value) {
	    String regex = "[12]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	codeWebadres = value; 
	    } else {
    		System.out.println("VoorcodeWebadres is alleen geldig 1 = Webadres werkzoekende profiel en 2 = Webadres CV");			
    	}

    	this.codeWebadres = value; 
    	}

    public String getUrl() { return url; }
    /**
     * @param value
     * De Uniform Resource Locator (URL) van het (web)adres van een bestand.
     */
    public void setUrl(String value) {
	    String regex = "^((?:https?:\\/\\/)?[^./]+(?:\\.[^./]+)+(?:\\/.*)?)$";
	    if (Pattern.matches(regex, value)) {
	    	url = value;
	    } else {
	    	System.out.println("Url \"" + url + "\" is niet in een geldig formaat.");
	    }
    }
}
