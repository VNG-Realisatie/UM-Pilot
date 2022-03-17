package um.vum.pojo;

import java.util.regex.Pattern;

/**
 * @author ronny
 * Geautoriseerd document om een motorvoertuig over de weg te mogen besturen. Norminstantie SGR
 */
public class Rijbewijs {
    private String codeSoortRijbewijs;

    public String getCodeSoortRijbewijs() { return codeSoortRijbewijs; }
    /**
     * @param value
     * De code die de rijbewijscategorie aangeeft.
     * A = Zware motor met onbeperkt vermogen
     * A1 = Lichte motor, 125 cc en maximaal vermogen van 11 kilowatt (kW)
     * A2 = Middelzware motor, Maximaal vermogen van 35 kilowatt (kW)
     * AM = Bromfiets en snorfiets
     * B = Auto
     * B+ = Auto, eventueel met aanhangwagen van meer dan 750kg met een maximum van 4250 kilo (auto + aanhanger)
     * C1 = Lichte vrachtwagen met een maximaal gewicht van 7500kg, eventueel met aanhangwagen tot 750kg
     * C = Vrachtwagen met een gewicht van meer dan 3500kg, eventueel met aanhangwagen tot 750kg
     * D1 = Bus voor meer dan 8 en maximaal 16 zitplaatsen, exclusief de bestuurder). Maximale lengte bus 8 meter.
     * D = Bus voor meer dan 8 personen, eventueel met aanhangwagen tot 750kg
     * BE = Auto, eventueel met aanhangwagen van meer dan 750kg met een maximum van 3500 kilo (auto + aanhanger)
     * C1E = Lichte vrachtwagen met een maximaal gewicht van 7500kg, eventueel met aanhangwagen van meer dan 750kg
     * CE = Vrachtwagen met een gewicht van meer dan 3500kg, eventueel met aanhangwagen van meer dan 750kg
     * D1E = Bus voor maximaal 16 personen excl. bestuurder, eventueel met aanhangwagen van meer dan 750kg
     * DE = Bus voor meer dan 8 personen, eventueel met aanhangwagen van meer dan 750kg
     * T = Trekker
     */
    public void setCodeSoortRijbewijs(String value) {
	    String regex = "^A$|^A1$|^A2$|^AM$|^B$|^B\\+$|^C1$|^C$|^D1$|^D$|^BE$|^C1E$|^CE$|^D1E$|^DE$|^T$";
	    if (Pattern.matches(regex, value)) {
	    	codeSoortRijbewijs = value; 
	    } else {
    		System.out.println("codeSoortRijbewijs \"" + value + "\" is niet conform SGR specificaties");			
    	}

    }
}
