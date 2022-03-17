package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * Werkervaring
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "De werkzaamheden die een natuurlijk persoon in een bepaalde periode als beroep heeft uitgevoerd.")
public class Werkervaring extends MPWerkervaring {

    @Size(max = 2000)
    @Schema(description = "De toelichting op de WERKERVARING.")
    private String toelichtingWerkervaring;


}
