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
 * Werktijden
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "De mate waarin de werkzoekende beschikbaar is voor werk.")
public class Werktijden {

    @Min(0)
    @Max(99)
    @Schema(description = "Het aantal dat de hoeveelheid minimale werkuren per week aangeeft.")
    private Integer aantalWerkurenPerWeekMinimaal;

    @Min(0)
    @Max(99)
    @Schema(description = "Het aantal dat de hoeveelheid maximale werkuren per week aangeeft.")
    private Integer aantalWerkurenPerWeekMaximaal;

    @Min(0)
    @Max(2)
    @Schema(description = "Indicatie die aangeeft of er sprake is van het deel van een dag waarin kantoorwerkzaamheden traditioneel (overdag) worden verricht.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Ja\n" +
            "- 2 = Nee")
    private Integer indicatieKantoortijden;

}
