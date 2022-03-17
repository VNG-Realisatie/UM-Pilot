package com.cap.pocvng.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

/**
 * Flexibiliteit
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "De bereidheid van de werkzoekende om concessies te doen aan de eisen die hij stelt aan het aanvaarden van arbeid.\n\n" +
        "Norminstantie: SGR")
public class Flexibiliteit {

    @Min(0)
    @Max(9)
    @Schema(description = "De code die aangeeft wat de straal is, ten opzichte van het werklocatieadres, waarbinnen de werkzaamheden verricht zullen worden.\n\n" +
            "- 1 = 5 km\n" +
            "- 2 = 10 km\n" +
            "- 3 = 15 km\n" +
            "- 4 = 25 km\n" +
            "- 5 = 50 km\n" +
            "- 6 = 75 km\n" +
            "- 7 = 100 km\n" +
            "- 8 = 125 km\n" +
            "- 9 = 150 km")
    private Integer codeRegiostraal;

    @Min(0)
    @Max(2)
    @Schema(description = "Indicatie die aangeeft of het werk op onregelmatige tijden en/of in ploegendienst plaatsvindt.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Ja\n" +
            "- 2 = Nee")
    private Integer indicatieOnregelmatigWerkOfPloegendienst;

    @Schema(description = "De datum van de eerste werkdag.")
    private LocalDate datumAanvangBeschikbaarVoorWerk;

    @Schema(description = "De datum van de laatste werkdag.")
    private LocalDate datumEindeBeschikbaarVoorWerk;

}
