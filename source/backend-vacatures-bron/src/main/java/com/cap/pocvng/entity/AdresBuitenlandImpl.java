package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * AdresBuitenlandAdresBuitenland
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
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
    @OneToOne(cascade = {CascadeType.ALL})
    private AdresDetailsBuitenland adresDetailsBuitenland;


}
