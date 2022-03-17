package com.cap.pocvng.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Arbeidsmarktkwalificatie
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // Has to be entity else all elementcollection are saved as null in db.
public class Arbeidsmarktkwalificatie {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Min(0)
    @Max(7)
    @Schema(description = "Code die het opleidingsniveau aangeeft dat bereikt is op basis van opleiding en/of werkervaring.\n\n" +
            "- 0 = niet van toepassing\n" +
            "- 1 = geen basisopleiding\n" +
            "- 2 = basisniveau\n" +
            "- 3 = vmbo-niveau\n" +
            "- 4 = mbo/havo/vwo-niveau\n" +
            "- 5 = hbo-niveau\n" +
            "- 6 = wetenschappelijk onderwijs\n" +
            "- 7 = (nog) niet bekend")
    private Integer codeWerkEnDenkniveauWerkzoekende;

    @Valid
    @ElementCollection
    @Schema(description = "Het vermogen om een handeling in een bepaalde mate bekwaam uit te voeren.")
    private List<Vakvaardigheid> vakvaardigheid;

    @Valid
    @ElementCollection
    @Schema(description = "De mate waarin een natuurlijk persoon een taal beheerst.")
    private List<Taalbeheersing> taalbeheersing;

    @Valid
    @ElementCollection
    @Schema(description = "De karaktereigenschappen van een natuurlijk persoon.")
    private List<Gedragscompetentie> gedragscompetentie;

    @Valid
    @ElementCollection
    @Schema(description = "Een onderwerp waar de werkzoekende belangstelling voor heeft.")
    private List<Interesse> interesse;

    @Valid
    @ElementCollection
    @Schema(description = "Geautoriseerd document om een motorvoertuig over de weg te mogen besturen.\n\n" +
            "Norminstantie: SGR")
    private List<Rijbewijs> rijbewijs;

    @Valid
    @ElementCollection
    @Schema(description = "De cursus die de werkzoekende volgt, heeft gevolgd of heeft ingepland om te gaan volgen.")
    private List<Cursus> cursus;

    @Valid
    @ElementCollection
    @Schema(description = "De scholing die de werkzoekende volgt of heeft gevolgd.")
    private List<Opleiding> opleiding;

    @Valid
    @ElementCollection
    @Schema(description = "De werkzaamheden die een natuurlijk persoon in een bepaalde periode als beroep heeft uitgevoerd.")
    private List<Werkervaring> werkervaring;


}
