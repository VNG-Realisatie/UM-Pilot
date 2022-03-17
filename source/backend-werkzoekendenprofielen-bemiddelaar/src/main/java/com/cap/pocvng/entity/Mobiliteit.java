package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * Mobiliteit
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "Gewenste reisafstand woon-werk.")
public class Mobiliteit {

    @Pattern(regexp = "^[1-9][0-9]{3}(?!sa|sd|ss)[a-z]{2}$", flags = {Pattern.Flag.CASE_INSENSITIVE})
    @Schema(description = "De postcode van waaruit de werkzoekende bemiddeld wenst te worden.", pattern = "^[1-9][0-9]{3} (?!sa|sd|ss)[a-z]{2}$/i")
    private String bemiddelingspostcode;

    @Min(0)
    @Max(999)
    @Schema(description = "De maximale afstand, uitgedrukt in kilometers, waarbinnen de werkzoekende wil worden bemiddeld.")
    private Integer maximaleReisafstand;

    @Min(0)
    @Max(999)
    @Schema(description = "De maximale acceptabele reistijd in minuten die de werkzoekende accepteert in verband met het verrichten van werk.")
    private Integer maximaleReistijd;


}
