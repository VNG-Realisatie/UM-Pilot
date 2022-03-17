package um.vum.pojo;

import java.time.LocalDate;

/**
 * @author ronny
 * De cursus die de werkzoekende volgt, heeft gevolgd of heeft ingepland om te gaan volgen.
 */
public class Cursus {
	   private String naamCursus;
	    private LocalDate datumCertificaat;
	    private LocalDate datumAanvangVolgenCursus;
	    private LocalDate datumEindeVolgenCursus;
	    private String naamOpleidingsinstituut;

	    public String getNaamCursus() { return naamCursus; }
	    /**
	     * @param value maxLength: 120
	     * De naam van de cursus zoals deze bekend staat.
	     */
	    public void setNaamCursus(String value) { 
		    if (value.length() < 121) {
		    	naamCursus = value;
		    } else {
		    	System.out.println("NaamCursus \"" + value + "\" mag niet meer dan 120 characters bevatten.");	    	
		    }
	   	}

	    public LocalDate getDatumCertificaat() { return datumCertificaat; }
	    /**
	     * @param value
	     * De datum van de dag die op het certificaat staat.
	     */
	    public void setDatumCertificaat(LocalDate value) {
	    	this.datumCertificaat = value; 
	   	}

	    public LocalDate getDatumAanvangVolgenCursus() { return datumAanvangVolgenCursus; }
	    /**
	     * @param value
	     * De datum van de eerste dag waarop de cursus wordt of is gevolgd.
	     */
	    public void setDatumAanvangVolgenCursus(LocalDate value) {
	    	this.datumAanvangVolgenCursus = value; 
	    }

	    public LocalDate getDatumEindeVolgenCursus() { return datumEindeVolgenCursus; }
	    /**
	     * @param value
	     * De datum van de laatste dag dat de cursus is gevolgd.
	     */
	    public void setDatumEindeVolgenCursus(LocalDate value) {
	    	this.datumEindeVolgenCursus = value; 
	    }

	    public String getNaamOpleidingsinstituut() { return naamOpleidingsinstituut; }
	    /**
	     * @param value maxLength: 500
	     * De naam van het instituut waar de opleiding of cursus is gevolgd.
	     */
	    public void setNaamOpleidingsinstituut(String value) {
		    if (value.length() < 501) {
		    	naamOpleidingsinstituut = value;
		    } else {
		    	System.out.println("NaamOpleidingsinstituut \"" + value + "\" in Cursus \"" + naamCursus + "\" mag niet meer dan 500 characters bevatten.");	    	
		    }
	    }

}
