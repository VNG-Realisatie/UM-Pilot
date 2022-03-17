package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * MPVervoermiddel
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Embeddable
@Schema(description = "Een object dat bedoeld is om personen of goederen te vervoeren.")
public class MPVervoermiddel {
    @Min(0)
    @Max(2)
    @Schema(description = "Indicatie die aangeeft of er een vervoermiddel beschikbaar is voor het uitvoeren van de functie.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Ja\n" +
            "- 2 = Nee"
    )
    private Integer indicatieBeschikbaarVoorUitvoeringWerk;

    @Min(0)
    @Max(2)
    @Schema(description = "Indicatie die aangeeft of de werkzoekende het vervoermiddel ter beschikking heeft voor woon-werkverkeer.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Ja\n" +
            "- 2 = Nee"
    )
    private Integer indicatieBeschikbaarVoorWoonWerkverkeer;
}
