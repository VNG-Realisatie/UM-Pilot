package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * MPWerkzoekende
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MPWerkzoekende {

    @Min(1)
    @Max(2)
    private Integer indicatieLdrRegistratie;

    @Min(1)
    @Max(2)
    private Integer indicatieBeschikbaarheidContactgegevens;

    @Valid
    private List<Voorkeursland> voorkeursland;

    @Valid
    private List<MPVervoermiddel> vervoermiddel;

    @Valid
    private Mobiliteit mobiliteit;

    @Valid
    private Flexibiliteit flexibiliteit;

    @Valid
    private Werktijden werktijden;

    @Valid
    private List<Contractvorm> contractvorm;

    @Valid
    private MPArbeidsmarktkwalificatie arbeidsmarktkwalificatie;

    @Valid
    private List<SectorBeroepsEnBedrijfsleven> sector;

    @Valid
    private List<Beroepsnaam> bemiddelingsberoep;

}
