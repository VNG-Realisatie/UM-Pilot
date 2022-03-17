package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * OpleidingsnaamGecodeerd
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OpleidingsnaamGecodeerd extends MPOpleidingsnaam implements Opleidingsnaam {

    @Min(0)
    @Max(9999999999L)
    @Schema(description = "De unieke code van een OPLEIDINGSNAAM.")
    private Long codeOpleidingsnaam;

    @Size(max = 120)
    @Schema(description = "De naam van de OPLEIDING.")
    private String omschrijvingOpleidingsnaam;


}
