package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Werkgever
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Werkgever {

    @Size(max = 500)
    private String handelsnaamOrganisatie;

    @Valid
    private List<Webadres> webadres;

    @Valid
    private List<AdresHouding> adresHouding;

    @Valid
    private List<ContactpersoonAfdeling> contactpersoon;

    @Valid
    private List<SectorBeroepsEnBedrijfsleven> sector;

}
