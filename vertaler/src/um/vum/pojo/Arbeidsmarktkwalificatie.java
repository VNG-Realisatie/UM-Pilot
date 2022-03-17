package um.vum.pojo;

import java.util.regex.Pattern;

/** description:
 *  De set van eigenschappen, kennis en kunde die een werkzoekende bezit en
 *  relevant zijn voor het vinden van werk.
*/
/**
 * @author ronny
 * De set van eigenschappen, kennis en kunde die een werkzoekende bezit en relevant zijn voor het vinden van werk.
 */
public class Arbeidsmarktkwalificatie {
    private Integer codeWerkEnDenkniveauWerkzoekende; //OK
    private Vakvaardigheid[] vakvaardigheden; //OK
    private Taalbeheersing[] taalbeheersing; //OK
    private Gedragscompetentie[] gedragscompetenties; //OK
    private Interesse[] interesses; //OK
    private Rijbewijs[] rijbewijs; //OK
    private Cursus[] cursussen; //OK
    private Opleiding[] opleidingen;
    private Werkervaring[] werkervaringen;

    public int getCodeWerkEnDenkniveauWerkzoekende() { return codeWerkEnDenkniveauWerkzoekende; }
    /**
     * @param value
     * Code die het opleidingsniveau aangeeft dat bereikt is op basis van opleiding en/of werkervaring.
     * 0 = niet van toepassing - 1 = geen basisopleiding - 2 = basisniveau - 3 = vmbo-niveau
     * 4 = mbo/havo/vwo-niveau - 5 = hbo-niveau - 6 = wetenschappelijk onderwijs - 7 = (nog) niet bekend
     */
    public void setCodeWerkEnDenkniveauWerkzoekende(int value) {
	    String regex = "[01234567]";
	    if (Pattern.matches(regex, Integer.toString(value))) {
	    	codeWerkEnDenkniveauWerkzoekende = value; 
	    } else {
    		System.out.println("Voor codeWerkEnDenkniveauWerkzoekende kent een waarde tussen 0 en 7. 0 = niet van toepassing, 7 = niet bekend");			
    	}
    }

    public Vakvaardigheid[] getVakvaardigheid() { return vakvaardigheden; }
    /**
     * @param value
     * Het vermogen om een handeling in een bepaalde mate bekwaam uit te voeren.
     */
    public void setVakvaardigheid(Vakvaardigheid[] value) { this.vakvaardigheden = value; }

    public Taalbeheersing[] getTaalbeheersing() { return taalbeheersing; }
    /**
     * @param value
     * De mate waarin een natuurlijk persoon een taal beheerst.
     */
    public void setTaalbeheersing(Taalbeheersing[] value) { this.taalbeheersing = value; }

    public Gedragscompetentie[] getGedragscompetentie() { return gedragscompetenties; }
    /**
     * @param value
     * De karaktereigenschappen van een natuurlijk persoon.
     */
    public void setGedragscompetentie(Gedragscompetentie[] value) { this.gedragscompetenties = value; }

    public Interesse[] getInteresse() { return interesses; }
    /**
     * @param value
     * Een onderwerp waar de werkzoekende belangstelling voor heeft.
     */
    public void setInteresse(Interesse[] value) { this.interesses = value; }

    public Rijbewijs[] getRijbewijs() { return rijbewijs; }
    /**
     * @param value
     * Geautoriseerd document om een motorvoertuig over de weg te mogen besturen. Norminstantie SGR
     */
    public void setRijbewijs(Rijbewijs[] value) { this.rijbewijs = value; }

    public Cursus[] getCursus() { return cursussen; }
    /**
     * @param value
     * De cursus die de werkzoekende volgt, heeft gevolgd of heeft ingepland om te gaan volgen.
     */
    public void setCursus(Cursus[] value) { this.cursussen = value; }

    public Opleiding[] getOpleiding() { return opleidingen; }
    /**
     * @param value
     * De scholing die de werkzoekende volgt of heeft gevolgd.
     */
    public void setOpleiding(Opleiding[] value) { this.opleidingen = value; }

    public Werkervaring[] getWerkervaring() { return werkervaringen; }
    /**
     * @param value
     * De werkzaamheden die een natuurlijk persoon in een bepaalde periode als beroep heeft uitgevoerd.
     */
    public void setWerkervaring(Werkervaring[] value) { this.werkervaringen = value; }
	
}
