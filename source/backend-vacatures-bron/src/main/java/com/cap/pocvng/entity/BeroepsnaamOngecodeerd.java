package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

/**
 * BeroepsnaamOngecodeerd
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BeroepsnaamOngecodeerd extends Beroepsnaam {

    @Size(max = 120)
    @Schema(description = "De omschrijving van het BEROEP.")
    private String naamBeroepOngecodeerd;
}
