package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

/**
 * AdresNederland
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdresNederland implements Adres {

    @Valid
    private AdresNederlandImpl adresNederland;

}
