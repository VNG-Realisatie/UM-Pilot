package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * AdresHouding
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdresHouding {

    @Size(max = 1)
    private String codeFunctieAdres;

    private LocalDate datumAanvangAdres;

    private LocalDate datumEindeAdres;

    @Valid
    private Adres adres;

}
