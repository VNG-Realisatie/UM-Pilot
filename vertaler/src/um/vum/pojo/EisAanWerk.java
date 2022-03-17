package um.vum.pojo;

import java.util.regex.Pattern;

/**
 * @author ronny
 * De werkomstandigheden en/of de inhoud van het werk die aanwezig moeten zijn, zodat de werkzoekende het werk uit kan voeren.
 */
public class EisAanWerk {
    private Integer indicatieAanpassingWerkomgeving;
    private Integer indicatieBegeleiding;
    private Integer indicatieWerkvariatie;

    public int getIndicatieAanpassingWerkomgeving() { return indicatieAanpassingWerkomgeving; }
    /**
     * @param value
     * Indicatie die aangeeft of de werkzoekende een vorm van aanpassing aan de werkomgeving nodig heeft om te kunnen functioneren.
     */
    public void setIndicatieAanpassingWerkomgeving(int value) {
	    String regex = "[012]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	this.indicatieAanpassingWerkomgeving = value; 
	            } else {
    		System.out.println("Voor indicatieAanpassingWerkomgeving is alleen geldig 0 = onbekend, 1 = Ja en 2 = Nee");			
    	}

    	}

    public int getIndicatieBegeleiding() { return indicatieBegeleiding; }
    /**
     * @param value
     * Indicatie die aangeeft of de werkzoekende een vorm van begeleiding nodig heeft om te kunnen functioneren.
     */
    public void setIndicatieBegeleiding(int value) { 
	    String regex = "[012]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	       	this.indicatieBegeleiding = value;
	        	    } else {
    		System.out.println("Voor indicatieBegeleiding is alleen geldig 0 = onbekend, 1 = Ja en 2 = Nee");			
    	}
    	}

    public int getIndicatieWerkvariatie() { return indicatieWerkvariatie; }
    /**
     * @param value
     * Indicatie die aangeeft of de werkzoekende een vorm van werkvariatie nodig heeft om te kunnen functioneren.
     */
    public void setIndicatieWerkvariatie(int value) { 
	    String regex = "[012]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	       	this.indicatieWerkvariatie = value; 
	        	    } else {
    		System.out.println("Voor indicatieWerkvariatie is alleen geldig 0 = onbekend, 1 = Ja en 2 = Nee");			
    	}
    	}
}
