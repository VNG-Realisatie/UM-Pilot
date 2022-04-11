package um.vum.pojo;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * @author ronny
 * De scholing die de werkzoekende volgt of heeft gevolgd.
 */
public class Opleiding {
    private Integer codeNiveauOpleiding;
    private Integer indicatieDiploma;
    private LocalDate datumDiploma;
    private Opleidingsnaam opleidingsnaam;
    private LocalDate datumAanvangVolgenOpleiding;
    private LocalDate datumEindeVolgenOpleiding;
    private Integer codeStatusOpleiding;
    private String naamOpleidingsinstituut;

    
	/**
	 * Geeft de mogelijke waarden voor Opleidingsniveau
	 */
	public enum codeNiveau { 
		BASISONDERWIJS(1), VMBO(2), HAVO_VWO(3), MBO(4), HBO_BACHELOR(5), WO_MASTER(6), ANDERS(9);
		
		public final int label;
		
		private codeNiveau(int label) {
			this.label = label;
		}
	}

	/**
	 * Geeft de mogelijke waarden voor IndicatieDiplomaBehaald
	 */
	public enum codeDiploma { 
		JA(1), Nee(2), ONBEKEND(0), NIET_VAN_TOEPASSING(8);
		
		public final int label;
		
		private codeDiploma(int label) {
			this.label = label;
		}
	}

	/**
	 * Geeft de mogelijke waarden die aangeeft in welk stadium de OPLEIDING zich bevindt
	 */
	public enum stadiumOpleiding { 
		SUCCESVOL_AFGEROND(1), AFGEBROKEN(2), LOPEND(3), ONBEKEND(0);
	
		public final int label;
		
		private stadiumOpleiding(int label) {
			this.label = label;
		}
	}
    
    public int getCodeNiveauOpleiding() { return codeNiveauOpleiding; }
    /**
     * @param value
     * De code die het niveau van de OPLEIDING aangeeft.
     * 1 = Basisonderwijs - 2 = VMBO - 3 = HAVO/VWO - 4 = MBO - 5 = HBO/Bachelor - 6 = WO/Master - 9 = Anders
     */
    public void setCodeNiveauOpleiding(int value) {
	    String regex = "[1234569]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	codeNiveauOpleiding = value; 
	    } else {
    		System.out.println("Voor codeNiveauOpleiding \"" + value + "\" is ongeldig. Een geldige waarde kent een waarde tussen 1 en 6 en waarde 9.");			
    	}
    }

    public int getIndicatieDiploma() { return indicatieDiploma; }
    /**
     * @param value
     * Indicatie die aangeeft of voor de OPLEIDING een diploma, een getuigschrift of één of meer (deel-)certifica(a)t(en) is behaald.
     * 0 = Onbekend - 1 = Ja - 2 = Nee - 8 = Niet van toepassing
     */
    public void setIndicatieDiploma(int value) {
	    String regex = "[0128]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	indicatieDiploma = value; 
	    } else {
    		System.out.println("Voor indicatieDiploma \"" + value + "\" is ongeldig. Een geldige waarde kent een waarde 0, 1, 2 of 8.");			
    	}
    }

    public LocalDate getDatumDiploma() { return datumDiploma; }
    /**
     * @param value
     * De datum van de dag die op het diploma staat.
     */
    public void setDatumDiploma(LocalDate value) {
    	datumDiploma = value;
    }

    public Opleidingsnaam getOpleidingsnaam() { return opleidingsnaam; }
    /**
     * @param value
     * De naamgegevens van een OPLEIDING. Norminstantie SGR
     */
    public void setOpleidingsnaam(Opleidingsnaam value) {
    	opleidingsnaam = value;
    }

    public LocalDate getDatumAanvangVolgenOpleiding() { return datumAanvangVolgenOpleiding; }
    /**
     * @param value
     * De datum van de eerste dag waarop de opleiding wordt of is gevolgd.
     */
    public void setDatumAanvangVolgenOpleiding(LocalDate value) {
    	datumAanvangVolgenOpleiding = value;
    }

    public LocalDate getDatumEindeVolgenOpleiding() { return datumEindeVolgenOpleiding; }
    /**
     * @param value
     * De datum van de laatste dag dat de opleiding is gevolgd.
     */
    public void setDatumEindeVolgenOpleiding(LocalDate value) {
    	datumEindeVolgenOpleiding = value;
    }

    public int getCodeStatusOpleiding() { return codeStatusOpleiding; }
    /**
     * @param value
     * Code die aangeeft in welk stadium de OPLEIDING zich bevindt.
     * 0 = Onbekend - 1 = Succesvol afgerond - 2 = Afgebroken - 3 = Lopend
     */
    public void setCodeStatusOpleiding(int value) {
	    String regex = "[0123]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	codeStatusOpleiding = value; 
	    } else {
    		System.out.println("Voor codeStatusOpleiding \"" + value + "\" is ongeldig. Een geldige waarde kent een waarde 0, 1, 2 of 3.");			
    	}
    }

    public String getNaamOpleidingsinstituut() { return naamOpleidingsinstituut; }
    /**
     * @param value maxLength: 500
     * De naam van het instituut waar de opleiding of cursus is gevolgd.
     */
    public void setNaamOpleidingsinstituut(String value) {
    	naamOpleidingsinstituut = value; 
    }

}
