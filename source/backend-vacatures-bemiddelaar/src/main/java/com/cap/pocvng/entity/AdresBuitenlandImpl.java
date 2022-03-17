package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * AdresBuitenlandAdresBuitenland
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdresBuitenlandImpl {

    @Size(max = 2)
    private String landencodeIso;

    @Size(max = 40)
    private String landsnaam;

    @Size(max = 70)
    private String locatieomschrijvingBuitenland;

    @Size(max = 9)
    private String postcodeBuitenland;

    @Size(max = 24)
    private String regionaamBuitenland;

    @Size(max = 40)
    private String woonplaatsnaamBuitenland;

    @Valid
    private AdresDetailsBuitenland adresDetailsBuitenland;


}
