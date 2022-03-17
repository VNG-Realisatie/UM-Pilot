package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * MPArbeidsVoorwaarden
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MPArbeidsVoorwaarden {
    @Valid
    private LocalDate datumAanvangWerkzaamheden;

    @Valid
    private LocalDate datumEindeWerkzaamheden;

    @Size(max = 100)
    private String salarisIndicatie;

}
