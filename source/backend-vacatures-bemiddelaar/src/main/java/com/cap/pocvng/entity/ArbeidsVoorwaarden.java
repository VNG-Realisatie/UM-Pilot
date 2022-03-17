package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * ArbeidsVoorwaarden
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArbeidsVoorwaarden {
    private LocalDate datumAanvangWerkzaamheden;

    private LocalDate datumEindeWerkzaamheden;

    @Size(max = 2000)
    private String omschrijvingArbeidsvoorwaarden;

    @Size(max = 100)
    private String salarisIndicatie;


}
