package um.vum.pojo;

import java.util.regex.Pattern;

/**
 * @author ronny
 * De PERSOON of de afdeling van een ORGANISATIE waarbij een derde, 
 * voor deze in een specifieke situatie relevante, informatie kan verkrijgen. Norminstantie SGR
 */
public class ContactPersoon implements java.io.Serializable{

	private static final long serialVersionUID = -7940293417592521365L;
	private String naamContactpersoonAfdeling;
	private String telefoonnummer;
	private String emailadres;

	public String getNaamContactpersoonAfdeling() {
		return naamContactpersoonAfdeling;
	}

	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	public String getEmailadres() {
		return emailadres;
	}

	/**
	 * @param naamContactpersoonAfdeling
	 * De naam van de CONTACTPERSOON/-AFDELING
	 */
	public void setNaamContactpersoonAfdeling(String naamContactpersoonAfdeling) {
	    if (naamContactpersoonAfdeling.length() < 36) {
			this.naamContactpersoonAfdeling = naamContactpersoonAfdeling;
	    } else {
	    	System.out.println("NaamContactpersoonAfdeling \"" + naamContactpersoonAfdeling + "\" mag niet langer zijn dan 35 characters.");	    	
	    }
	}

	/**
	 * @param telefoonnummer
	 * De gegevens waarmee een webadres geïdentificeerd kan worden.
	 */
	public void setTelefoonnummer(String telefoonnummer) {
	    String regex = "^(?:0|(?:\\+|00) ?31 ?)(?:(?:[1-9] ?(?:[0-9] ?){8})|(?:6 ?-? ?[1-9] ?(?:[0-9] ?){7})|(?:[1,2,3,4,5,7,8,9]\\d ?-? ?[1-9] ?(?:[0-9] ?){6})|(?:[1,2,3,4,5,7,8,9]\\d{2} ?-? ?[1-9] ?(?:[0-9] ?){5}))$";
	    if (Pattern.matches(regex, telefoonnummer)) {
	    	this.telefoonnummer = telefoonnummer;
	    } else {
	    	System.out.println("Telefoonnummer \"" + telefoonnummer + "\" is niet in een geldig formaat.");
	    }
	}

	/**
	 * @param emailadres
	 * De aanduiding van de unieke aansluiting van de elektronische postbus van een PERSOON of ORGANISATIE op het internet.
	 */
	public void setEmailadres(String emailadres) {
	    String regex = "^\\S+@\\S+\\.\\S+$";
		    if (Pattern.matches(regex, emailadres)) {
				this.emailadres = emailadres;
		    } else {
		    	System.out.println("Emailadres \"" + emailadres + "\" is niet in een geldig formaat.");
		    }
	}
}