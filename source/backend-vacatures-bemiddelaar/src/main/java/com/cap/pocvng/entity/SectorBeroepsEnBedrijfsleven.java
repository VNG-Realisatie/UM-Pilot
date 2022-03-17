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
 * SectorBeroepsEnBedrijfsleven
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "De sector bedroeps- en bedrijfsleven waarin de werkzoekende bemiddeld wenst te worden.")
public class SectorBeroepsEnBedrijfsleven {

    @Min(0)
    @Max(99999)
    @Schema(description = "De code die de indeling van een bedrijf aangeeft volgens de Standaard Bedrijfs Indeling van het Centraal Bureau voor de Statistiek.")
    private Integer codeSbi;

}
