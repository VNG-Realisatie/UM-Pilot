package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * EisAanWerk
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "De werkomstandigheden en/of de inhoud van het werk die aanwezig moeten zijn, zodat de werkzoekende het werk uit kan voeren.")
public class EisAanWerk {

    @Min(0)
    @Max(2)
    @Schema(description = "Indicatie die aangeeft of de werkzoekende een vorm van aanpassing aan de werkomgeving nodig heeft om te kunnen functioneren.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Ja\n" +
            "- 2 = Nee")
    private Integer indicatieAanpassingWerkomgeving;

    @Min(0)
    @Max(2)
    @Schema(description = "Indicatie die aangeeft of de werkzoekende een vorm van begeleiding nodig heeft om te kunnen functioneren.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Ja\n" +
            "- 2 = Nee")
    private Integer indicatieBegeleiding;

    @Min(0)
    @Max(2)
    @Schema(description = "Indicatie die aangeeft of de werkzoekende een vorm van werkvariatie nodig heeft om te kunnen functioneren.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Ja\n" +
            "- 2 = Nee")
    private Integer indicatieWerkvariatie;


}
