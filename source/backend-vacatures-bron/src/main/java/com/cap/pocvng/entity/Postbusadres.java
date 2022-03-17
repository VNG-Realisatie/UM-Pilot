package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.Valid;

/**
 * Postbusadres
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Postbusadres extends AdresDetailsNederland {

    @Valid
    @Embedded
    private PostbusadresImpl postbusadres;

}
