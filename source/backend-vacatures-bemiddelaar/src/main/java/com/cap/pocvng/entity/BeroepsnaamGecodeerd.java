package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

/**
 * BeroepsnaamGecodeerd
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BeroepsnaamGecodeerd extends Beroepsnaam {
    @Size(max = 10)
    @Schema(description = "De unieke code van de beroepsnaam uit het BO&C register.")
    private String codeBeroepsnaam;

    @Size(max = 120)
    @Schema(description = "De naam van het BEROEP.")
    private String omschrijvingBeroepsnaam;


}
