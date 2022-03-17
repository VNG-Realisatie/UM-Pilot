package um.vum.pojo;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class WerkzoekendeProfiel implements java.io.Serializable {

	private static final long serialVersionUID = 4695480349391509300L;
	private String idWerkzoekende;
	private Integer indicatieLdrRegistratie; 
	private Integer indicatieBeschikbaarheidContactgegevens;
	private String persoonlijkePresentatie;
	private Voorkeursland[] voorkeurslanden;
	private EisAanWerk eisAanWerk;
	private Vervoermiddel[] vervoermiddelen;
	private Mobiliteit mobiliteit;
	private Flexibiliteit flexibiliteit;
	private Werktijden werktijden;
	private Contractvorm[] contractvormen;
	private Arbeidsmarktkwalificatie arbeidsmarktkwalificatie;
	private String[] emailadressen;
	private Webadres[] webadressen;
    private String[] telefoonnummer;
	private ContactPersoon[] contactPersonen;
	private Sector[] sectors;
	private Bemiddelingsberoep[] bemiddelingsberoepen;
	
	/**
	 * Geeft de mogelijke waarden voor Indicatie
	 */
	public enum Indicatie { 
		JA(1), NEE(2);
		
		public final int label;
		
		private Indicatie(int label) {
			this.label = label;
		}
	}


	public String getIdWerkzoekende() {
		return idWerkzoekende;
	}
	/**
	 * @param idWerkzoekende
	 * verplicht: unieke code voor de werkzoekende binnen de aangeleverde gegevensset maxLength: 200	
	 */
	
	public void setIdWerkzoekende(String idWerkzoekende) { //throws WerkzoekendeProfielException {
		if (idWerkzoekende == null || idWerkzoekende.length() > 200) {
			System.out.println("idWerkzoekende niet aanwezig of langer dan 200 characters");
			//throw new WerkzoekendeProfielException("idWerkzoekende niet aanwezig of langer dan 200 characters");
		}
			this.idWerkzoekende = idWerkzoekende;
	}

	public int getIndicatieLdrRegistratie() {
		return indicatieLdrRegistratie;
	}

	/**
	 * @param indicatieLdrRegistratie
	 * De indicatie die aangeeft of voor de natuurlijk persoon in het Landelijk Doelgroepregister een grondslag met betrekking tot arbeidsbeperktheid geregistreerd is.
	 * Zie enum Indicatie voor mogelijke waarden. (1 = Ja - 2 = Nee)
	 */
	public void setIndicatieLdrRegistratie(int indicatieLdrRegistratie) {
	    String regex = "[12]";
		if (Pattern.matches(regex, Integer.toString(indicatieLdrRegistratie))) {
			this.indicatieLdrRegistratie = indicatieLdrRegistratie;
		} else {
			System.out.println("Voor indicatieLdrRegistratie is alleen geldig 1 = Ja en 2 = Nee");			
		}
	}

	public int getIndicatieBeschikbaarheidContactgegevens() {
		return indicatieBeschikbaarheidContactgegevens;
	}

	/**
	 * @param indicatieBeschikbaarheidContactgegevens
	 * Indicatie die aangeeft of er contactgegevens, van de werkzoekende en/of de contactpersoon, in het profiel aanwezig zijn.
	 * Zie enum Indicatie voor mogelijke waarden. (1 = Ja - 2 = Nee)
	 */
	public void setIndicatieBeschikbaarheidContactgegevens(int indicatieBeschikbaarheidContactgegevens) {
		
	    String regex = "[12]";
		if (Pattern.matches(regex, Integer.toString(indicatieBeschikbaarheidContactgegevens))) {
			this.indicatieBeschikbaarheidContactgegevens = indicatieBeschikbaarheidContactgegevens;
		} else {
			System.out.println("Voor indicatieBeschikbaarheidContactgegevens is alleen geldig 1 = Ja en 2 = Nee");			
		}
	}

	public String getPersoonlijkePresentatie() {
		return persoonlijkePresentatie;
	}

	/**
	 * @param persoonlijkePresentatie
	 * De persoonlijke presentatie van de werkzoekende.
	 */
	public void setPersoonlijkePresentatie(String persoonlijkePresentatie) {
		if (persoonlijkePresentatie.length() > 2000) {
			System.out.println("persoonlijkePresentatie mag niet langer zijn dan 2000 characters");
		} else {
			this.persoonlijkePresentatie = persoonlijkePresentatie;
		}
	}

	public Voorkeursland[] getVoorkeursland() {
		return voorkeurslanden;
	}

	/**
	 * @param voorkeurslanden
	 * Het land waarin de werkzoekende bij voorkeur wil gaan werken.
	 */
	public void setVoorkeursland(Voorkeursland[] voorkeurslanden) {
		this.voorkeurslanden = voorkeurslanden;
	}

	public EisAanWerk getEisAanWerk() {
		return eisAanWerk;
	}

	/**
	 * @param eisAanWerk
	 * De werkomstandigheden en/of de inhoud van het werk die aanwezig moeten zijn, zodat de werkzoekende het werk uit kan voeren.
	 */
	public void setEisAanWerk(EisAanWerk eisAanWerk) {
		this.eisAanWerk = eisAanWerk;
	}

	public Vervoermiddel[] getVervoermiddel() {
		return vervoermiddelen;
	}

	/**
	 * @param vervoermiddelen
	 * Een object dat bedoeld is om personen of goederen te vervoeren.
	 */
	public void setVervoermiddel(Vervoermiddel[] vervoermiddelen) {
		this.vervoermiddelen = vervoermiddelen;
	}

	public Mobiliteit getMobiliteit() {
		return mobiliteit;
	}

	/**
	 * @param mobiliteit
	 * Gewenste reisafstand woon-werk.
	 */
	public void setMobiliteit(Mobiliteit mobiliteit) {
		this.mobiliteit = mobiliteit;
	}

	public Flexibiliteit getFlexibiliteit() {
		return flexibiliteit;
	}

	/**
	 * @param flexibiliteit
	 * De bereidheid van de werkzoekende om concessies te doen aan de eisen die hij stelt aan het aanvaarden van arbeid. Norminstantie SGR
	 */
	public void setFlexibiliteit(Flexibiliteit flexibiliteit) {
		this.flexibiliteit = flexibiliteit;
	}

	public Werktijden getWerktijden() {
		return werktijden;
	}

	/**
	 * @param werktijden
	 * De mate waarin de werkzoekende beschikbaar is voor werk.
	 */
	public void setWerktijden(Werktijden werktijden) {
		this.werktijden = werktijden;
	}

	public Contractvorm[] getContractvorm() {
		return contractvormen;
	}

	/**
	 * @param contractvormen
	 * De contractvorm welke van toepassing is.
	 */
	public void setContractvorm(Contractvorm[] contractvormen) {
		this.contractvormen = contractvormen;
	}

	public Arbeidsmarktkwalificatie getArbeidsmarktkwalificatie() {
		return arbeidsmarktkwalificatie;
	}

	/**
	 * @param arbeidsmarktkwalificatie
	 * De set van eigenschappen, kennis en kunde die een werkzoekende bezit en relevant zijn voor het vinden van werk.
	 */
	public void setArbeidsmarktkwalificatie(Arbeidsmarktkwalificatie arbeidsmarktkwalificatie) {
		this.arbeidsmarktkwalificatie = arbeidsmarktkwalificatie;
	}

	public String[] getEmailadres() {
		return emailadressen;
	}

	/**
	 * @param emailadressen
	 * De aanduiding van de unieke aansluiting van de elektronische postbus van een PERSOON of ORGANISATIE op het internet.
	 */
	public void setEmailadres(String[] emailadressen) {
		ArrayList<String> ea = new ArrayList<String>(); 
	    String regex = "^\\S+@\\S+\\.\\S+$";
		for (String emailadres : emailadressen){
		    if (Pattern.matches(regex, emailadres)) {
				ea.add(emailadres);
		    } else {
		    	System.out.println("Emailadres \"" + emailadres + "\" is niet in een geldig formaat.");
		    }

		}
		this.emailadressen = ea.toArray(new String[0]);
	}

	public Webadres[] getWebadres() {
		return webadressen;
	}

	/**
	 * @param webadressen
	 * De gegevens waarmee een webadres geïdentificeerd kan worden.
	 */
	public void setWebadres(Webadres[] webadressen) {
		this.webadressen = webadressen;
	}

	public String[] getTelefoonnummer() {
		return telefoonnummer;
	}

	/**
	 * @param telefoonnummer
	 * De gegevens waarmee een webadres geïdentificeerd kan worden.
	 */
	public void setTelefoonnummer(String[] telefoonnummer) {
		ArrayList<String> tel = new ArrayList<String>(); 
	    String regex = "^(?:0|(?:\\+|00) ?31 ?)(?:(?:[1-9] ?(?:[0-9] ?){8})|(?:6 ?-? ?[1-9] ?(?:[0-9] ?){7})|(?:[1,2,3,4,5,7,8,9]\\d ?-? ?[1-9] ?(?:[0-9] ?){6})|(?:[1,2,3,4,5,7,8,9]\\d{2} ?-? ?[1-9] ?(?:[0-9] ?){5}))$";
		for (String telefoon : telefoonnummer){
		    if (Pattern.matches(regex, telefoon)) {
				tel.add(telefoon);
		    } else {
		    	System.out.println("Telefoonnummer \"" + telefoon + "\" is niet in een geldig formaat.");
		    }
		}
		this.telefoonnummer = tel.toArray(new String[0]);
	}

	public ContactPersoon[] getContactpersoon() {
		return contactPersonen;
	}

	/**
	 * @param contactPersonen
	 * De PERSOON of de afdeling van een ORGANISATIE waarbij een derde, 
	 * voor deze in een specifieke situatie relevante, informatie kan verkrijgen. Norminstantie SGR
	 */
	public void setContactpersoon(ContactPersoon[] contactPersonen) {
		this.contactPersonen = contactPersonen;
	}
	
	public Sector[] getSector() {
		return sectors;
	}

	/**
	 * @param sectors
	 * De sector bedroeps- en bedrijfsleven waarin de werkzoekende bemiddeld wenst te worden.
	 */
	public void setSector(Sector[] sectors) {
		this.sectors = sectors;
	}

	public Bemiddelingsberoep[] getBemiddelingsberoep() {
		return bemiddelingsberoepen;
	}

	/**
	 * @param bemiddelingsberoepen
	 * TODO klopt dit object??
	 */
	public void setBemiddelingsberoep(Bemiddelingsberoep[] bemiddelingsberoepen) {
		this.bemiddelingsberoepen = bemiddelingsberoepen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
