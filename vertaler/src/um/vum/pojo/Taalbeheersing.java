package um.vum.pojo;

import java.util.regex.Pattern;

/**
 * @author ronny
 * De mate waarin een natuurlijk persoon een taal beheerst.
 */
public class Taalbeheersing {
    private String codeTaal;
    private Integer codeNiveauTaalbeheersingMondeling;
    private Integer codeNiveauTaalbeheersingSchriftelijk;
    private Integer codeNiveauTaalbeheersingLezen;
    private Integer codeNiveauTaalbeheersingLuisteren;

    public String getCodeTaal() { return codeTaal; }
    /**
     * @param value
     * De code die de taal aangeeft. Betreft ISO 639-3 codelijst
     */
    public void setCodeTaal(String value) { 
	    String regex = "[a-z]{3}";
	    if (Pattern.matches(regex, value)) {
	    	codeTaal = value;
	    } else {
    		System.out.println("De codeTaal voor de taalbeheersing is niet conform ISO 639-3");			
    	}
    }

    public int getCodeNiveauTaalbeheersingMondeling() { return codeNiveauTaalbeheersingMondeling; }
    /**
     * @param value
     * Code die de mate aangeeft waarin de natuurlijk persoon de betreffende taal mondeling machtig is.
     * 0 = Onbekend - 1 = Geen beheersing - 2 = Redelijk - 3 = Goed - 4 = Uitstekend - 8 = Niet van toepassing
     */
    public void setCodeNiveauTaalbeheersingMondeling(int value) { 
	    String regex = "[012348]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	codeNiveauTaalbeheersingMondeling = value; 
	    } else {
    		System.out.println("CodeNiveauTaalbeheersingMondeling voor de taalbeheersing \"" +codeTaal+ "\" is niet conform VUM specificaties");			
    	}
    }

    public int getCodeNiveauTaalbeheersingSchriftelijk() { return codeNiveauTaalbeheersingSchriftelijk; }
    /**
     * @param value
     * Code die de mate aangeeft waarin de natuurlijk persoon de betreffende taal schriftelijk machtig is.
     * 0 = Onbekend - 1 = Geen beheersing - 2 = Redelijk - 3 = Goed - 4 = Uitstekend - 8 = Niet van toepassing
     */
    public void setCodeNiveauTaalbeheersingSchriftelijk(int value) { 
	    String regex = "[012348]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	codeNiveauTaalbeheersingSchriftelijk = value; 
	    } else {
    		System.out.println("CodeNiveauTaalbeheersingSchriftelijk voor de taalbeheersing \"" + codeTaal + "\" is niet conform VUM specificaties");			
    	}
   	}

    public int getCodeNiveauTaalbeheersingLezen() { return codeNiveauTaalbeheersingLezen; }
    /**
     * @param value
     * Code die die het niveau van de leesvaardigheid van de natuurlijk persoon in de betreffende taal aangeeft.
     * 0 = Onbekend - 1 = Geen beheersing - 2 = Redelijk - 3 = Goed - 4 = Uitstekend - 8 = Niet van toepassing
     */
    public void setCodeNiveauTaalbeheersingLezen(int value) {
	    String regex = "[012348]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	codeNiveauTaalbeheersingLezen = value; 
	    } else {
    		System.out.println("CodeNiveauTaalbeheersingLezen voor de taalbeheersing \"" + codeTaal + "\" is niet conform VUM specificaties");			
    	}
    }

    public int getCodeNiveauTaalbeheersingLuisteren() { return codeNiveauTaalbeheersingLuisteren; }
    /**
     * @param value
     * Code die het niveau van luistervaardigheid van de natuurlijk persoon in de betreffende taal aangeeft.
     * 0 = Onbekend - 1 = Geen beheersing - 2 = Redelijk - 3 = Goed - 4 = Uitstekend - 8 = Niet van toepassing
     */
    public void setCodeNiveauTaalbeheersingLuisteren(int value) {
	    String regex = "[012348]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	codeNiveauTaalbeheersingLuisteren = value; 
	    } else {
    		System.out.println("CodeNiveauTaalbeheersingLuisteren voor de taalbeheersing \"" + codeTaal + "\" is niet conform VUM specificaties");			
    	}
    }

}
