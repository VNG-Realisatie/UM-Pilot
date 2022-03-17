package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

/**
 * Postbusadres
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Postbusadres implements AdresDetailsNederland {

    @Valid
    private PostbusadresImpl postbusadres;

}
