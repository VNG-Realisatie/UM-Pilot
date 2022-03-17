package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * ArbeidsVoorwaarden
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ArbeidsVoorwaarden {
    @Schema(description = "De datum van de eerste dag van de werkzaamheden.")
    private LocalDate datumAanvangWerkzaamheden;

    @Schema(description = "De datum van de laatste dag waarop het adres gerelateerd is aan de PERSOON of de ONDERNEMING/INSTELLING.")
    private LocalDate datumEindeWerkzaamheden;

    @Size(max = 2000)
    @Schema(description = "De omschrijving van de voorwaarden waaronder werk wordt verricht.")
    private String omschrijvingArbeidsvoorwaarden;

    @Size(max = 100)
    @Schema(description = "De omschrijving die een indicatie van de hoogte van het salaris aangeeft.")
    private String salarisIndicatie;


}
