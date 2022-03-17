package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

/**
 * Straatadres
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Straatadres implements AdresDetailsNederland {

    @Valid
    private StraatadresImpl straatadres;

}
