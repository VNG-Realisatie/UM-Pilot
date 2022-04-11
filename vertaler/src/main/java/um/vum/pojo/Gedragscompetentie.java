package um.vum.pojo;

import java.util.regex.Pattern;

/**
 * @author ronny
 * De karaktereigenschappen van een natuurlijk persoon.
 */
public class Gedragscompetentie {
    private Integer codeGedragscompetentie;
    private String omschrijvingGedragscompetentie;
    private Integer codeBeheersingGedragscompetentie;

    public int getCodeGedragscompetentie() { return codeGedragscompetentie; }
    /**
     * @param value
     * De code die de GEDRAGSCOMPETENTIE aangeeft.
     * 24100 = Beslissen en activiteiten initiëren - 24101 = Aansturen
     * 24102 = Begeleiden - 24104 = Aandacht en begrip tonen - 24105 = Samenwerken en overleggen
     * 24106 = Ethisch en integer handelen - 24107 = Relaties bouwen en netwerken
     * 24108 = Overtuigen en beïnvloeden - 24109 = Presenteren - 24110 = Formuleren en rapporteren
     * 24111 = Vakdeskundigheid toepassen - 24112 = Materialen en middelen inzetten
     * 24113 = Analyseren - 24114 = Onderzoeken - 24115 = Creeren en innoveren
     * 24116 = Leren - 24118 = Plannen en organiseren
     * 24119 = Op de behoeften en verwachtingen van de klant richten - 24120 = Kwaliteit leveren
     * 24121 = Instructies en procedures opvolgen - 24122 = Omgaan met verandering en aanpassen
     * 24123 = Met druk en tegenslag omgaan - 24124 = Gedrevenheid en ambitie tonen
     * 24125 = Ondernemend en commercieel handelen - 24126 = Bedrijfsmatig handelen
     */
    public void setCodeGedragscompetentie(int value) {
	    String regex = "^24100$|^24101$|^24102$|^24104$|^24105$|^24106$|^24107$|^24108$|^24109$|^24110$|^24111$|^24112$|^24113$|"
	    		+ "^24114$|^24115$|^24116$|^24118$|^24119$|^24120$|^24121$|^24122$|^24123$|^24124$|^24125$|^24126$";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	codeGedragscompetentie = value; 
	    } else {
    		System.out.println("CodeGedragscompetentie \"" + value + "\" is niet conform VUM specificaties");			
    	}
    }

    public String getOmschrijvingGedragscompetentie() { return omschrijvingGedragscompetentie; }
    /**
     * @param value
     * De omschrijving van de GEDRAGSCOMPETENTIE.
     */
    public void setOmschrijvingGedragscompetentie(String value) {
	    if (value.length() < 121) {
	    	omschrijvingGedragscompetentie = value;
	    } else {
	    	System.out.println("OmschrijvingGedragscompetentie \"" + value + "\" mag niet langer zijn dan 120 characters.");	    	
	    }
    }

    public int getCodeBeheersingGedragscompetentie() { return codeBeheersingGedragscompetentie; }
    /**
     * @param value
     * De code die aangeeft in welke mate de gedragscompetentie beheerst wordt.
     * 1 = Goed - 2 = Voldoende - 3 = Onvoldoende - 4 = Niet - 5 = Onderzoek noodzakelijk - 9 = Niet van toepassing
     */
    public void setCodeBeheersingGedragscompetentie(int value) {
	    String regex = "[123459]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	codeBeheersingGedragscompetentie = value; 
	    } else {
    		System.out.println("CodeBeheersingGedragscompetentie \"" + value + "\" is niet conform VUM specificaties");			
    	}
	}
}
