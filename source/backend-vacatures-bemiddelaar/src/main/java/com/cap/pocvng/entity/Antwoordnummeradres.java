package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

/**
 * Antwoordnummeradres
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Antwoordnummeradres implements AdresDetailsNederland {

    @Valid
    private AntwoordnummeradresImpl antwoordnummeradres;

}
