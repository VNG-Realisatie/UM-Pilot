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
 * Werkervaring
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Werkervaring {
    @Min(0)
    @Max(99)
    @Schema(description = "Het aantal jaren dat de NATUURLIJK PERSOON in het BEROEP werkzaam is geweest.")
    private Integer aantalJarenWerkzaamInBeroep;

}
