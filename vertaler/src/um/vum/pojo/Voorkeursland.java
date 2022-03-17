package um.vum.pojo;

import java.util.regex.Pattern;

/**
 * Het land waarin de werkzoekende bij voorkeur wil gaan werken.
 */
public class Voorkeursland {
	
	private String landencodeISO;

	public String getLandencodeIso() { return landencodeISO; }

	/**
	 * @param landencodeISO
	 * De code van een huidig land of gebiedsdeel conform ISO 3166. Norminstantie ISO 3166 (Entity, Alpha-2 code)
	 */
	public void setLandencodeIso(String landencodeISO) {
	    String regex = "^[A-Z]{2}$";
	    if (Pattern.matches(regex, landencodeISO)) {
			this.landencodeISO = landencodeISO;
	    } else {
	    	System.out.println("LandencodeISO \"" + landencodeISO + "\" is niet in een geldig formaat.");
	    }
	}
}
