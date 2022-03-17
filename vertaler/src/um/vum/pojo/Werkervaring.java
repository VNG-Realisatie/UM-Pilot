package um.vum.pojo;

import java.time.LocalDate;

/**
 * @author ronny
 * De werkzaamheden die een natuurlijk persoon in een bepaalde periode als beroep heeft uitgevoerd.
 *
 */
public class Werkervaring {
    private LocalDate datumAanvangWerkzaamheden;
    private LocalDate datumEindeWerkzaamheden;
    private String naamOrganisatie;
    private Beroep beroep;
    private String toelichtingWerkervaring;

    public LocalDate getDatumAanvangWerkzaamheden() { return datumAanvangWerkzaamheden; }
    /**
     * @param value
     * De datum van de eerste dag van de werkzaamheden.
     */
    public void setDatumAanvangWerkzaamheden(LocalDate value) { this.datumAanvangWerkzaamheden = value; }

    public LocalDate getDatumEindeWerkzaamheden() { return datumEindeWerkzaamheden; }
    /**
     * @param value
     * De datum van de laatste dag van de werkzaamheden.
     */
    public void setDatumEindeWerkzaamheden(LocalDate value) { this.datumEindeWerkzaamheden = value; }

    public String getNaamOrganisatie() { return naamOrganisatie; }
    /**
     * @param value maxLength: 70
     * De naam waaronder de organisatie bekend is.
     */
    public void setNaamOrganisatie(String value) {
    	this.naamOrganisatie = value;
    }

    public Beroep getBeroep() { return beroep; }
    public void setBeroep(Beroep value) {
    	this.beroep = value;
    }

    public String getToelichtingWerkervaring() { return toelichtingWerkervaring; }
    /**
     * @param value maxLength: 2000
     * De toelichting op de WERKERVARING.
     */
    public void setToelichtingWerkervaring(String value) { this.toelichtingWerkervaring = value; }

}
