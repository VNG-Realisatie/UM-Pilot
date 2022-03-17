package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.Valid;

/**
 * AdresNederland
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdresNederland extends Adres {

    @Valid
    @Embedded
    private AdresNederlandImpl adresNederland;

}
