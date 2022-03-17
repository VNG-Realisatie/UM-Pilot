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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * AdresNederlandAdresNederland
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AdresNederlandImpl {

    @Size(max = 4)
    private String codeGemeente;

    @Size(max = 24)
    private String district;

    @Size(max = 24)
    private String gemeentedeel;

    @Size(max = 40)
    private String gemeentenaam;

    @Size(max = 16)
    private String identificatiecodeNummeraanduiding;

    @Size(max = 16)
    private String identificatiecodeVerblijfplaats;

    @Size(max = 70)
    private String locatieomschrijving;

    @Pattern(regexp = "^[1-9][0-9]{3}(?!sa|sd|ss)[a-z]{2}$", flags = {Pattern.Flag.CASE_INSENSITIVE})
    @Schema(pattern = "^[1-9][0-9]{3}(?!sa|sd|ss)[a-z]{2}$/i")
    private String postcode;

    @Size(max = 80)
    private String woonplaatsnaam;

    @Valid
    @OneToOne(cascade = {CascadeType.ALL})
    private AdresDetailsNederland adresDetails;

}
