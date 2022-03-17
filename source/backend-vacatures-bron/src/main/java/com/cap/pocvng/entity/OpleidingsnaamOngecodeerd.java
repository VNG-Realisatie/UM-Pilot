package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

/**
 * OpleidingsnaamOngecodeerd
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OpleidingsnaamOngecodeerd extends Opleidingsnaam {

    @Size(max = 120)
    @Schema(description = "De omschrijving van de OPLEIDING.")
    private String naamOpleidingOngecodeerd;

    @Size(max = 2000)
    @Schema(description = "De eigen omschrijving van de opleiding.")
    private String omschrijvingOpleiding;
}
